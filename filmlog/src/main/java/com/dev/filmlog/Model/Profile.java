package com.dev.filmlog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Profile {
    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(255) not null")
    private String name;

    @Column(columnDefinition = "text check(length(description) <=350)")
    private String description;

    // --- relations ---

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    private Set<UserList> userLists;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "profile")
    @JsonIgnore
    private Set<Review> reviews;

}
