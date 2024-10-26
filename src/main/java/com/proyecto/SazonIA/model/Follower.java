package com.proyecto.SazonIA.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(FollowerPK.class)
public class Follower {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "followed_id", nullable = false)
    private User followed;

    @Override
    public String toString() {
        return "Follower [user=" + user + ", followed=" + followed + "]";
    }
}
