package com.example.demo.service;

import com.example.demo.dto.StudentChatRequestDTO;
import com.example.demo.dto.StudentChatResponseDTO;

public interface ChatbotService {

	StudentChatResponseDTO processChat(StudentChatRequestDTO request);

}
