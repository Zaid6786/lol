package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.StudentChatRequestDTO;
import com.example.demo.dto.StudentChatResponseDTO;
import com.example.demo.service.ChatbotService;

@RestController
@RequestMapping("/api/v1/chat")
@CrossOrigin(origins = "*")
public class ChatbotController {

	@Autowired
	private ChatbotService chatbotService;

	@PostMapping
	public StudentChatResponseDTO processChat(@RequestBody StudentChatRequestDTO request) {
		if (request == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is null.");
		}
		if (request.getSessionId() == null || request.getSessionId().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Session ID is required.");
		}
		if (request.getStudent() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student details are required.");
		}
		if (request.getStudent().getStudentId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student ID is required.");
		}
		if (request.getQuestion() == null || request.getQuestion().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question cannot be blank.");
		}

		return chatbotService.processChat(request);
	}
}
