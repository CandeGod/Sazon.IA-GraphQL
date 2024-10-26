package com.proyecto.SazonIA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.proyecto.SazonIA.model.Follower;
import com.proyecto.SazonIA.model.User;
import com.proyecto.SazonIA.dto.FollowerDTO;
import com.proyecto.SazonIA.service.FollowerService;

import java.util.List;

@Controller
public class FollowerController {

    @Autowired
    private FollowerService service;

    @QueryMapping
    public List<Follower> getFollowers(@Argument int userId) {
        User followed = service.findUserById(userId);
        return service.getFollowers(followed);
    }

    @QueryMapping
    public List<Follower> getFollowing(@Argument int userId) {
        User follower = service.findUserById(userId);
        return service.getFollowing(follower);
    }

    @MutationMapping
    public String followUser(@Argument(value = "follower") FollowerDTO followerDTO) {
        if (followerDTO == null) {
            return "FollowerDTO is null";
        }
        User follower = service.findUserById(followerDTO.getUserId());
        User followed = service.findUserById(followerDTO.getFollowedId());

        if (follower == null || followed == null)
            return "User not found";
        if (service.isFollowing(follower, followed))
            return "Already following";
        if (follower.equals(followed))
            return "You cannot follow yourself";

        service.followUser(follower, followed);
        return "User followed successfully";
    }

    @MutationMapping
    public String unfollowUser(@Argument(value = "follower") FollowerDTO followerDTO) {
        User follower = service.findUserById(followerDTO.getUserId());
        User followed = service.findUserById(followerDTO.getFollowedId());

        if (follower == null || followed == null)
            return "User not found";
        if (follower.equals(followed))
            return "You cannot unfollow yourself";
        if (!service.isFollowing(follower, followed))
            return "Not following";

        service.unfollowUser(follower, followed);
        return "User unfollowed successfully";
    }
}
