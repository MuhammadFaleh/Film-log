package com.dev.filmlog.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(60)")
    private String title;
    @Column(columnDefinition = "text check(length(description) <=350)")
    private String description;
    @Column()
    private Double rating;
    @Column()
    private LocalDateTime createAt;

    //  --- Relation ---
    @ManyToOne
    private Media media;
    @ManyToOne
    private Profile profile;
}
