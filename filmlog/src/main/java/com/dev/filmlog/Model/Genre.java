package com.dev.filmlog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry, genre name can't be empty, please try again")
    @Size(max = 30, message = "Sorry genre name can't be more than 30 characters, please try again")
    @Column(columnDefinition = "varchar(30)")
    private String name;
    @NotEmpty(message = "Sorry, genre description can't be empty, please try again")
    @Size(max = 350, message = "Sorry genre description can't be more than 350 characters, please try again")
    @Column(columnDefinition = "text check(length(description) <=350)")
    private String description;

    //  --- Relation ---
    @ManyToMany()
    @JsonIgnore
    private Set<Media> medias;

}
