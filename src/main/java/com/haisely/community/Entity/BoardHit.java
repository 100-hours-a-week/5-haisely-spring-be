package com.haisely.community.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board_hits")
public class BoardHit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_hit_id")
    private int id;

    @OneToOne
    @JoinColumn(name="board_id")
    private Board board;

    @With
    @Column(name = "hit", nullable = false)
    private int hit = 0;

    @Builder
    public BoardHit(Board board){
        this.board = board;
    }
}
