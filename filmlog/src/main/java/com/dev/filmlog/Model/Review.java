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
    @NotEmpty(message = "Sorry, review title can't be empty, please try again")
    @Size(max = 60, message = "Sorry, review title can't be more than 60 characters, please try again")
    @Column(columnDefinition = "varchar(60)")
    private String title;
    @NotEmpty(message = "Sorry, review description can't be empty, please try again")
    @Size(max = 350, message = "Sorry, review description can't be more than 350 characters, please try again")
    @Column(columnDefinition = "text check(length(description) <=350)")
    private String description;
    @NotNull(message = "Sorry, review rating can't be empty, please try again")
    @PositiveOrZero(message = "Sorry, review rating can't be negative, please try again")
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
