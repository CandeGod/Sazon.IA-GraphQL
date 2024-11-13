package com.proyecto.SazonIA.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String postId) {
        super("Post with ID " + postId + " not found.");
    }
}
