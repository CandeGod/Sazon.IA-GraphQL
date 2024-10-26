package com.proyecto.SazonIA.dto;

import java.time.LocalDate;

public class OpenAIRequestDTO {
    private Integer id;
    private Integer userId;
    private String prompt;
    private String recommendations;
    private LocalDate requestDate;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    public String getRecommendations() {
        return recommendations;
    }
    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
    public LocalDate getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
    public OpenAIRequestDTO(Integer id, Integer userId, String prompt, String recommendations, LocalDate requestDate) {
        this.id = id;
        this.userId = userId;
        this.prompt = prompt;
        this.recommendations = recommendations;
        this.requestDate = requestDate;
    }

    
}
