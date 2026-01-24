package com.dev.filmlog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Profile {
    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(255) not null")
    private String name;

    @Column(columnDefinition = "text not null check(length(description) <=350)")
    private String description;

    // --- relations ---

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    private Set<UserList> userLists;

}
