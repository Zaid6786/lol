package com.example.demo.serviceimple;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.StudentChatRequestDTO;
import com.example.demo.dto.StudentChatResponseDTO;
import com.example.demo.dto.fastapi.FastApiChatRequest;
import com.example.demo.dto.fastapi.FastApiChatResponse;
import com.example.demo.models.Bus;
import com.example.demo.models.BusDelay;
import com.example.demo.models.Driver;
import com.example.demo.models.RealTimeTracking;
import com.example.demo.models.Route;
import com.example.demo.models.StudentBusAssignment;
import com.example.demo.models.student;
import com.example.demo.repository.BusDelayRepository;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.RealTimeTrackingRepository;
import com.example.demo.repository.RouteRepository;
import com.example.demo.repository.StudentBusAssignmentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ChatbotService;

@Service
public class ChatbotServiceImple implements ChatbotService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentBusAssignmentRepository studentBusAssignmentRepository;

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private RealTimeTrackingRepository realTimeTrackingRepository;

	@Autowired
	private BusDelayRepository busDelayRepository;

	@Autowired
	private Environment env;

	@Value("${fastapi.chatbot.url}")
	private String fastApiUrl;

	@Value("${fastapi.chatbot.connectTimeoutMs:5000}")
	private int connectTimeoutMs;

	@Value("${fastapi.chatbot.readTimeoutMs:5000}")
	private int readTimeoutMs;

	@Override
	public StudentChatResponseDTO processChat(StudentChatRequestDTO request) {
		if (request == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is null.");
		}
		if (request.getStudent() == null || request.getStudent().getStudentId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student information is missing.");
		}
		if (request.getSessionId() == null || request.getSessionId().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Session ID is missing.");
		}
		if (request.getQuestion() == null || request.getQuestion().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question is missing.");
		}

		boolean isTestProfile = false;
		if (env != null && env.getActiveProfiles() != null) {
			for (String profile : env.getActiveProfiles()) {
				if ("chatbot-test".equals(profile)) {
					isTestProfile = true;
					break;
				}
			}
		}

		FastApiChatRequest apiRequest;

		if (isTestProfile) {
			// TEMPORARY CHATBOT INTEGRATION TEST DATA — REPLACE WITH DATABASE DATA
			apiRequest = new FastApiChatRequest();
			apiRequest.setSessionId(request.getSessionId());
			apiRequest.setQuestion(request.getQuestion());

			FastApiChatRequest.StudentContext studentContext = new FastApiChatRequest.StudentContext();
			studentContext.setStudentId(request.getStudent().getStudentId());
			studentContext.setStudentName(request.getStudent().getStudentName());
			studentContext.setRegistrationNumber(request.getStudent().getRegistrationNumber());
			apiRequest.setStudent(studentContext);

			apiRequest.setPickupStop(null);
			apiRequest.setFee(null);

			FastApiChatRequest.DelayInformation delayInfo = new FastApiChatRequest.DelayInformation();
			delayInfo.setDelayed(false);
			delayInfo.setDelayMinutes(0);
			delayInfo.setReasonCode(null);
			delayInfo.setReasonDescription(null);
			apiRequest.setDelay(delayInfo);

			FastApiChatRequest.AssignedBus assignedBus = new FastApiChatRequest.AssignedBus();
			assignedBus.setBusId(101L);
			assignedBus.setBusNumber("BUS-101");
			assignedBus.setRegistrationNumber("TS09AB1234");
			assignedBus.setRouteName("College Route A");
			assignedBus.setDriverName("Test Driver");
			assignedBus.setDriverPhoneMasked("******9999");
			assignedBus.setStatus("ON_TIME");
			apiRequest.setAssignedBus(assignedBus);

			FastApiChatRequest.LiveTracking liveTracking = new FastApiChatRequest.LiveTracking();
			liveTracking.setCurrentLocationName("Main Junction");
			liveTracking.setLatitude(17.4483);
			liveTracking.setLongitude(78.3741);
			liveTracking.setDistanceToStopKm(null);
			liveTracking.setEstimatedArrivalMinutes(10);
			liveTracking.setEstimatedArrivalTime("10 minutes");
			liveTracking.setLastUpdatedAt(LocalDateTime.now().toString());
			apiRequest.setLiveTracking(liveTracking);

		} else {
			Long studentId = request.getStudent().getStudentId();

			// 1. Retrieve authoritative student info from DB
			student studentEntity = studentRepository.findById(studentId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found."));

			// 2. Build FastAPI Request
			apiRequest = new FastApiChatRequest();
			apiRequest.setSessionId(request.getSessionId());
			apiRequest.setQuestion(request.getQuestion());

			// Map Student Context from DB fields directly (authoritative)
			FastApiChatRequest.StudentContext studentContext = new FastApiChatRequest.StudentContext();
			studentContext.setStudentId(studentEntity.getStudentId());
			studentContext.setStudentName(studentEntity.getName());
			studentContext.setRegistrationNumber(studentEntity.getRollNo());
			apiRequest.setStudent(studentContext);

			// Strict Data Rules: Omit / Set as null
			apiRequest.setPickupStop(null);
			apiRequest.setFee(null);

			// Resolve Bus Assignment
			Optional<StudentBusAssignment> assignmentOpt = studentBusAssignmentRepository
					.findTopByStudentIdOrderByAssignmentIdDesc(studentId);

			if (assignmentOpt.isPresent()) {
				StudentBusAssignment assignment = assignmentOpt.get();
				Long busId = assignment.getBusId();
				Long routeId = assignment.getRouteId();

				Bus busEntity = busRepository.findById(busId).orElse(null);
				Route routeEntity = routeRepository.findById(routeId).orElse(null);
				Driver driverEntity = null;
				if (busEntity != null && busEntity.getDriverId() != null) {
					driverEntity = driverRepository.findById(busEntity.getDriverId()).orElse(null);
				}

				BusDelay activeDelay = null;
				if (busId != null) {
					activeDelay = busDelayRepository
							.findTopByBusIdAndStatusOrderByCreatedAtDesc(busId, BusDelay.DelayStatus.PENDING)
							.orElse(null);
				}

				FastApiChatRequest.DelayInformation delayInfo = new FastApiChatRequest.DelayInformation();
				if (activeDelay != null) {
					delayInfo.setDelayed(true);
					delayInfo.setDelayMinutes(activeDelay.getDelayMinutes() != null ? activeDelay.getDelayMinutes() : 0);
					delayInfo.setReasonCode(null);
					delayInfo.setReasonDescription(activeDelay.getReason());
				} else {
					delayInfo.setDelayed(false);
					delayInfo.setDelayMinutes(0);
					delayInfo.setReasonCode(null);
					delayInfo.setReasonDescription(null);
				}
				apiRequest.setDelay(delayInfo);

				if (busEntity != null) {
					FastApiChatRequest.AssignedBus assignedBus = new FastApiChatRequest.AssignedBus();
					assignedBus.setBusId(busEntity.getBusId());
					assignedBus.setBusNumber(busEntity.getBusNo());
					assignedBus.setRegistrationNumber(busEntity.getRegistrationNumber());
					assignedBus.setRouteName(routeEntity != null ? routeEntity.getRouteName() : null);

					if (driverEntity != null) {
						assignedBus.setDriverName(driverEntity.getName());
						assignedBus.setDriverPhoneMasked(maskPhone(driverEntity.getPhone()));
					} else {
						assignedBus.setDriverName(null);
						assignedBus.setDriverPhoneMasked(null);
					}

					if (busEntity.getStatus() == Bus.BusStatus.INACTIVE) {
						assignedBus.setStatus("STOPPED");
					} else if (delayInfo.isDelayed()) {
						assignedBus.setStatus("DELAYED");
					} else {
						assignedBus.setStatus("ON_TIME");
					}
					apiRequest.setAssignedBus(assignedBus);
				} else {
					apiRequest.setAssignedBus(null);
				}

				RealTimeTracking trackingEntity = null;
				if (busId != null) {
					trackingEntity = realTimeTrackingRepository.findTopByBusIdOrderByTrackingTimeDesc(busId).orElse(null);
				}

				if (trackingEntity != null) {
					FastApiChatRequest.LiveTracking liveTracking = new FastApiChatRequest.LiveTracking();
					liveTracking.setCurrentLocationName(trackingEntity.getCurrentStop());
					liveTracking.setLatitude(trackingEntity.getLatitude() != null ? trackingEntity.getLatitude().doubleValue() : null);
					liveTracking.setLongitude(trackingEntity.getLongitude() != null ? trackingEntity.getLongitude().doubleValue() : null);
					liveTracking.setDistanceToStopKm(null);
					liveTracking.setEstimatedArrivalMinutes(trackingEntity.getEtaMinutes());

					if (trackingEntity.getTrackingTime() != null && trackingEntity.getEtaMinutes() != null) {
						LocalDateTime etaTime = trackingEntity.getTrackingTime().plusMinutes(trackingEntity.getEtaMinutes());
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
						liveTracking.setEstimatedArrivalTime(etaTime.format(formatter));
					} else {
						liveTracking.setEstimatedArrivalTime(null);
					}

					if (trackingEntity.getTrackingTime() != null) {
						liveTracking.setLastUpdatedAt(trackingEntity.getTrackingTime().toString());
					} else {
						liveTracking.setLastUpdatedAt(null);
					}
					apiRequest.setLiveTracking(liveTracking);
				} else {
					apiRequest.setLiveTracking(null);
				}

			} else {
				apiRequest.setAssignedBus(null);
				apiRequest.setDelay(null);
				apiRequest.setLiveTracking(null);
			}
		}

		// 3. Call FastAPI with RestTemplate and timeouts
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(connectTimeoutMs);
		requestFactory.setReadTimeout(readTimeoutMs);
		RestTemplate restTemplate = new RestTemplate(requestFactory);

		try {
			FastApiChatResponse apiResponse = restTemplate.postForObject(fastApiUrl, apiRequest, FastApiChatResponse.class);

			if (apiResponse == null || apiResponse.getAnswer() == null) {
				throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Invalid response from GenAI service.");
			}

			StudentChatResponseDTO responseDTO = new StudentChatResponseDTO();
			responseDTO.setAnswer(apiResponse.getAnswer());
			responseDTO.setIntent(apiResponse.getIntent());
			return responseDTO;

		} catch (ResourceAccessException e) {
			// FastAPI unavailable or timed out (503 Service Unavailable)
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "The bus assistant is temporarily unavailable. Please try again shortly.", e);
		} catch (RestClientResponseException e) {
			// FastAPI returned non-2xx response (502 Bad Gateway)
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "GenAI service returned error status: " + e.getStatusCode().value(), e);
		} catch (Exception e) {
			if (e instanceof ResponseStatusException) {
				throw (ResponseStatusException) e;
			}
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to communicate with the chatbot service.", e);
		}
	}

	private String maskPhone(String phone) {
		if (phone == null || phone.trim().isEmpty()) {
			return null;
		}
		String clean = phone.trim();
		if (clean.length() <= 4) {
			return "****";
		}
		return "*".repeat(clean.length() - 4) + clean.substring(clean.length() - 4);
	}
}
