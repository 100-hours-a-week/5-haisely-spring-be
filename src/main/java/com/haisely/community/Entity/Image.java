package com.haisely.community.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Builder
    public Image(String fileUrl){
        this.fileUrl = fileUrl;
    }
}
