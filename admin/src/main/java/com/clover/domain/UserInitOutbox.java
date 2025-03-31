package com.clover.domain;

import com.clover.domain.type.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_init_outbox")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserInitOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @Column(name = "image_uri")
    private String imageUri;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "status", nullable = false)
    private Status status;

    @Builder
    public UserInitOutbox(String phoneNumber, String password, String userType, String imageUri, String name, String email, Status status) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
        this.imageUri = imageUri;
        this.name = name;
        this.email = email;
        this.status = status;
    }
}
