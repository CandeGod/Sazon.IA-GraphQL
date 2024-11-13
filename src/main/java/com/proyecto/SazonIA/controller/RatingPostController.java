package com.proyecto.SazonIA.controller;

import com.proyecto.SazonIA.model.RatingPost;
import com.proyecto.SazonIA.service.RatingPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

@Controller
public class RatingPostController {

    @Autowired
    private RatingPostService ratingPostService;

    // Query para obtener una valoración por su ID
    @QueryMapping
    public RatingPost getRatingPostById(@Argument String ratingId) {
        return ratingPostService.getRatingById(ratingId).orElse(null);
    }

    // Mutation para crear una nueva valoración
    @MutationMapping
    public RatingPost createRatingPost(@Argument String postId, @Argument Integer userId, @Argument Integer value) {
        return ratingPostService.createRatingPost(postId, userId, value);
    }

    // Mutation para actualizar una valoración existente
    @MutationMapping
    public RatingPost updateRatingPost(@Argument String ratingId, @Argument Integer value) {
        return ratingPostService.updateRating(ratingId, value);
    }

    // Mutation para eliminar una valoración
    @MutationMapping
    public Boolean deleteRatingPost(@Argument String ratingId) {
        return ratingPostService.deleteRating(ratingId);
    }
}
