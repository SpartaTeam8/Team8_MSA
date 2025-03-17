package com.sparta.user.domain.model;

import com.sparta.user.domain.enumtype.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    public static User createUser(String username, String email, String password, Role role){
        User user = new User();
        user.username = username;
        user.email = email;
        user.password = password;
        user.role = role.toString();

        return user;
    }
}
