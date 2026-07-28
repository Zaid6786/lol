package com.example.demo.dto;

public class StudentChatRequestDTO {
	private String sessionId;
	private StudentContextDTO student;
	private String question;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public StudentContextDTO getStudent() {
		return student;
	}

	public void setStudent(StudentContextDTO student) {
		this.student = student;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
