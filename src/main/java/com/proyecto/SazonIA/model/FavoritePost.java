package com.proyecto.SazonIA.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "FavoritePost")
public class FavoritePost {

    @EmbeddedId
    private FavoritePostId favoritePostId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public FavoritePost() {
        this.createdAt = LocalDateTime.now();
    }

    public FavoritePost(FavoritePostId id) {
        this.favoritePostId = id;
        this.createdAt = LocalDateTime.now();
    }

    public FavoritePostId getId() {
        return favoritePostId;
    }

    public void setId(FavoritePostId id) {
        this.favoritePostId = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
