package com.haisely.community.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @With
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @With
    @Column(name = "password", nullable = false)
    private String password;

    @With
    @ManyToOne
    @JoinColumn(name="image_id")
    private Image image;

    @Column(name = "created_at")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());;

    @Column(name = "updated_at")
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Builder
    public User(Image image, String nickname, String email, String password){
        this.image = image;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
