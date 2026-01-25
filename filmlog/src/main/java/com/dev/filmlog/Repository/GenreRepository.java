package com.dev.filmlog.Repository;

import com.dev.filmlog.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {
    
    Genre findGenreById(Integer id);
}
