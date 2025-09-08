package com.peachgarden.forum.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private boolean emailVerified = false;

    private String avatar;

    @Column(nullable = false)
    private String roles = "user"; // comma-separated roles

    @Column(nullable = false)
    private String status = "active"; // active|muted|banned

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}

