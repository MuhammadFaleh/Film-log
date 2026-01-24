package com.dev.filmlog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry, media name can't be empty, please try again")
    @Size(max = 30, message = "Sorry, media name can't be more than 30 characters, please try again")
    @Column(columnDefinition = "varchar(30)")
    private String name;
    @NotEmpty(message = "Sorry, media description can't be empty, please try again")
    @Size(max = 350, message = "Sorry, media description can't be more than 350 characters, please try again")
    @Column(columnDefinition = "text check(length(description) <=350)")
    private String description;
    @NotEmpty(message = "Sorry, media run time can't be empty, please try again")
    @Column(columnDefinition = "varchar(255)")
    private String runtime;
    private Double rating;
    private enum ageRating{ G,PG, PG_13,R,NC_17,};
    private enum type{MOVIE,TV_SERIES};
//    @NotEmpty(message = "Sorry, media age rating can't be empty, please try again")
//    @Pattern(regexp = "G|PG|PG_13|R|NC_17")
//    @Column()
//    private String ageRating;
//    @NotEmpty(message = "Sorry, media type can't be empty, please try again")
//    @Pattern(regexp = "MOVIE|TV_SERIES")
//    @Column()
    private String type;
    @NotNull(message = "Sorry, media release date can't be empty, please try again")
    @Column(columnDefinition = "date")
    private LocalDate releaseDate;
    @NotEmpty(message = "Sorry, media image url can't be empty, please try again")
    @Column(columnDefinition = "text check(length(image_url) <=300)")
    private String imageUrl;

    //  --- Relation ---
    @ManyToMany(mappedBy = "medias")
    @JsonIgnore
    private Set<Genre> genres;

    @ManyToMany(mappedBy = "medias")
    @JsonIgnore
    private Set<Crew> crews;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "media")
    private Set<Review> reviews;

    @OneToMany(orphanRemoval = true, mappedBy = "media")
    private Set<ListMedia> listMedia;
}
