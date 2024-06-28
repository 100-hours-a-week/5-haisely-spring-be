package com.haisely.community.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private int id;

    @With
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @With
    @OneToOne
    @JoinColumn(name="image_id")
    private Image image;

    @OneToOne(mappedBy = "board")
    private BoardHit boardHit;

    @With
    @Column(name = "title", nullable = false)
    private String title;

    @With
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Builder
    public Board(User user, Image image, String title, String content){
        this.user = user;
        this.image = image;
        this.title = title;
        this.content = content;
    }
}
