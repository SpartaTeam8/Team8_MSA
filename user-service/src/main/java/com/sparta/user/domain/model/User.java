package com.sparta.user.domain.model;

import com.sparta.user.domain.enumtype.Role;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Table(name = "p_user")
@Getter
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    public static User createUser(String username, String email, String password, Role role) {
        User user = new User();
        user.username = username;
        user.email = email;
        user.password = password;
        user.role = role.toString();
        return user;
    }

    @PrePersist
    public void prePersist() {
        if (userId == null) {
            userId = UUID.randomUUID();
        }
    }
}
