package com.example.demo.config;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.example.demo.repository.*;

@Configuration
@Profile("chatbot-test")
@SuppressWarnings("unchecked")
public class MockRepositoryConfig {

	private <T> T createMock(Class<T> interfaceType) {
		return (T) Proxy.newProxyInstance(
			interfaceType.getClassLoader(),
			new Class<?>[] { interfaceType },
			(proxy, method, args) -> {
				String methodName = method.getName();
				if ("equals".equals(methodName)) {
					return proxy == args[0];
				}
				if ("hashCode".equals(methodName)) {
					return System.identityHashCode(proxy);
				}
				if ("toString".equals(methodName)) {
					return interfaceType.getName() + "@MockProxy";
				}
				if (method.getReturnType().equals(Optional.class)) {
					return Optional.empty();
				}
				if (method.getReturnType().equals(java.util.List.class)) {
					return Collections.emptyList();
				}
				return null;
			}
		);
	}

	@Bean
	public AdminRepository adminRepository() { return createMock(AdminRepository.class); }

	@Bean
	public AttendanceRepository attendanceRepository() { return createMock(AttendanceRepository.class); }

	@Bean
	public BusDelayRepository busDelayRepository() { return createMock(BusDelayRepository.class); }

	@Bean
	public BusLocationRepository busLocationRepository() { return createMock(BusLocationRepository.class); }

	@Bean
	public BusOccupancyRepository busOccupancyRepository() { return createMock(BusOccupancyRepository.class); }

	@Bean
	public BusRepository busRepository() { return createMock(BusRepository.class); }

	@Bean
	public ComplaintRepository complaintRepository() { return createMock(ComplaintRepository.class); }

	@Bean
	public DriverRepository driverRepository() { return createMock(DriverRepository.class); }

	@Bean
	public FleetRoutingRepository fleetRoutingRepository() { return createMock(FleetRoutingRepository.class); }

	@Bean
	public NotificationRepository notificationRepository() { return createMock(NotificationRepository.class); }

	@Bean
	public OperationsEngagementRepository operationsEngagementRepository() { return createMock(OperationsEngagementRepository.class); }

	@Bean
	public RealTimeTrackingRepository realTimeTrackingRepository() { return createMock(RealTimeTrackingRepository.class); }

	@Bean
	public RouteRepository routeRepository() { return createMock(RouteRepository.class); }

	@Bean
	public StopsRepository stopsRepository() { return createMock(StopsRepository.class); }

	@Bean
	public StudentBusAssignmentRepository studentBusAssignmentRepository() { return createMock(StudentBusAssignmentRepository.class); }

	@Bean
	public StudentRepository studentRepository() { return createMock(StudentRepository.class); }
}
