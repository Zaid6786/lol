package com.example.demo.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class PythonServerRunner {

    private Process pythonProcess;

    @PostConstruct
    public void startPythonServer() {
        try {
            System.out.println("Starting internal Python FastAPI server for GenAI...");
            
            // Determine paths (handles both local dev and Docker container paths)
            File genaiDir = new File("../genai_service");
            if (!genaiDir.exists()) {
                genaiDir = new File("/app/genai_service");
            }

            if (!genaiDir.exists()) {
                System.err.println("Warning: genai_service directory not found. Python AI Server will not start.");
                return;
            }

            List<String> command = new ArrayList<>();
            // Use 'python' or 'python3' depending on the environment
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("win")) {
                command.add("python");
                command.add("-m");
                command.add("uvicorn");
                command.add("app.main:app");
                command.add("--host");
                command.add("0.0.0.0");
                command.add("--port");
                command.add("8000");
            } else {
                // Assuming Linux (Docker)
                command.add("python3");
                command.add("-m");
                command.add("uvicorn");
                command.add("app.main:app");
                command.add("--host");
                command.add("0.0.0.0");
                command.add("--port");
                command.add("8000");
            }

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(genaiDir);
            pb.redirectErrorStream(true);

            pythonProcess = pb.start();
            System.out.println("Python FastAPI server launched successfully.");

            // Consume output so process doesn't block
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(pythonProcess.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Print Python logs prefixed with [PYTHON]
                        System.out.println("[PYTHON] " + line);
                    }
                } catch (Exception e) {
                    System.err.println("Error reading Python server output: " + e.getMessage());
                }
            }).start();

        } catch (Exception e) {
            System.err.println("Failed to start internal Python FastAPI server: " + e.getMessage());
        }
    }

    @PreDestroy
    public void stopPythonServer() {
        if (pythonProcess != null && pythonProcess.isAlive()) {
            System.out.println("Shutting down internal Python FastAPI server...");
            pythonProcess.destroy();
        }
    }
}
