package com.dev.filmlog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry, crew name can't be empty, please try again")
    @Size(max = 30, message = "Sorry, crew name can't be more than 30 characters, please try again")
    @Column(columnDefinition = "varchar(30)")
    private String name;
    @NotEmpty(message = "Sorry, crew description can't be empty, please try again")
    @Size(max = 350, message = "Sorry, crew description can't be more than 350 characters, please try again")
    @Column(columnDefinition = "text check(length(description) <=350)")
    private String description;
    private enum role {DIRECTOR, ACTOR}
    //    @NotEmpty(message = "Sorry, crew role can't be empty, please try again")
//    @Pattern(regexp = "DIRECTOR|ACTOR")
//    @Column()
//    private String role;
    @NotEmpty(message = "Sorry, media image url can't be empty, please try again")
    @Column(columnDefinition = "text check(length(image_url) <=300)")
    private String imageUrl;

    //  --- Relation ---
    @ManyToMany
    @JsonIgnore
    private Set<Media> medias;
}
