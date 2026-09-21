package com.mcp.mcpexample.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final ChatClient chatClient;

    public AIController(ChatClient.Builder chatClientBuilder,
            ToolCallbackProvider toolCallbackProvider) {

        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(toolCallbackProvider)
                .build();
    }

    @GetMapping("/seat")
    public ResponseEntity<String> seatBooking(@RequestParam("query") String query) {

        String systemPrompt = """
        You are a seat booking assistant.

        Rules:
        - Seats are stored in a file called seats.txt
        - Each line: SEAT_NAME=true/false
        - false = available, true = booked

        Tasks:
        - If user asks availability → read file and answer
        - If user asks to book:
            - If seat is false → mark true and confirm booking
            - If seat is true → say already booked
        - Always use filesystem MCP tools to read/write the file
        - Reply in simple English
        """;

        String response = chatClient
                .prompt()
                .system(systemPrompt)
                .user(query)
                .call()
                .content();

        return ResponseEntity.ok(response);
    }
}
