package com.proyecto.SazonIA.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.proyecto.SazonIA.dto.OpenAIRequestDTO;
import com.proyecto.SazonIA.service.OpenAIRequestService;

@Controller
public class OpenAIRequestController {

    private final OpenAIRequestService openAIRequestService;

    @Autowired
    public OpenAIRequestController(OpenAIRequestService openAIRequestService) {
        this.openAIRequestService = openAIRequestService;
    }

    @MutationMapping
    public String generateRecommendationChatBot(@Argument Integer userId, @Argument String prompt) {
        if (!openAIRequestService.userExists(userId)) {
            throw new RuntimeException("User not found");
        }
        return openAIRequestService.getRecommendations(userId, prompt);
    }

    @QueryMapping
    public List<OpenAIRequestDTO> getAllRecommendationsChatBot() {
        return openAIRequestService.getAll().stream()
                .map(request -> new OpenAIRequestDTO(request.getId(), request.getUser().getUserId(),
                        request.getPrompt(), request.getRecommendations(), request.getRequestDate()))
                .toList();
    }

    @QueryMapping
    public List<OpenAIRequestDTO> getRecomendationsChatBotByUserId(@Argument Integer userId) {
        if (!openAIRequestService.userExists(userId)) {
            throw new RuntimeException("User not found");
        }
        return openAIRequestService.getHistoryById(userId).stream()
                .map(request -> new OpenAIRequestDTO(request.getId(), request.getUser().getUserId(),
                        request.getPrompt(), request.getRecommendations(), request.getRequestDate()))
                .toList();
    }

    @MutationMapping
    public String deleteHistoryChatBotByUserId(@Argument Integer userId) {
        if (!openAIRequestService.userExists(userId)) {
            throw new RuntimeException("User not found");
        }
        openAIRequestService.delete(userId);
        return "History deleted successfully";
    }
}
