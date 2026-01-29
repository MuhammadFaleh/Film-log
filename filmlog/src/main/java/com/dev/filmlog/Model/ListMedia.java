package com.dev.filmlog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "list_media")

public class ListMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date", columnDefinition = "date not null")
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "date")
    private LocalDate endDate;

    @Column(columnDefinition = "varchar(50) not null check(progress='NOT STARTED' or progress='STARTED' or progress='COMPLETED')")
    private String progress;

    // --- relations ---
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_list_id", nullable = false)
    private UserList userList;

    @ManyToOne
    private Media media;
    }
