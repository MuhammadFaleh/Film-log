package com.dev.filmlog.Service;

import com.dev.filmlog.Api.ApiException;
import com.dev.filmlog.Model.Genre;
import com.dev.filmlog.Repository.GenreRepository;
import com.dev.filmlog.Repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public void addGenre(Genre genre){
        genreRepository.save(genre);
    }

    public List<Genre> getAllGenre(){
        return genreRepository.findAll();
    }

    public Genre getGenreById(Integer id){
        return genreRepository.findGenreById(id);
    }

    public void updateGenre(Integer genreId, Genre genre){
        Genre oldGenre=genreRepository.findGenreById(genreId);
        if (oldGenre==null){
            throw new ApiException("Genre not found");
        }

        oldGenre.setName(genre.getName());
        oldGenre.setDescription(genre.getDescription());
        genreRepository.save(oldGenre);
    }

    public void deleteGenre(Integer genreId){
        Genre genre =genreRepository.findGenreById(genreId);
        if (genre==null){
            throw new ApiException("Genre not found");
        }
        genreRepository.delete(genre);
    }

}
