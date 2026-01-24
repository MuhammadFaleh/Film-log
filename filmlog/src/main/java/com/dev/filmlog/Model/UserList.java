package com.dev.filmlog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_list")
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(255) not null")
    private String name;

    @Column(columnDefinition = "text not null check(length(description) <=350)")
    private String description;

    @Column(name = "created_at", columnDefinition = "timestamp not null")
    private LocalDateTime createdAt;

    @Column(name = "list_type", columnDefinition = "varchar(50) not null check(list_type='WATCHLIST' or list_type='LIKED' or list_type='WATCHED' or list_type='CUSTOM')")
    private String listType;

    // --- relations ---
    @ManyToOne
    @JsonIgnore
    private Profile profile;

    @OneToMany(mappedBy = "userList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ListMedia> listMedia;

}
