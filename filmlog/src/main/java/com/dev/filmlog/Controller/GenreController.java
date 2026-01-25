package com.dev.filmlog.Controller;

import com.dev.filmlog.Model.Genre;
import com.dev.filmlog.Service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/add")
    public ResponseEntity<?> addGenre(@RequestBody Genre genre) {
        genreService.addGenre(genre);
        return ResponseEntity.status(200).body("Genre added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllGenre() {
        return ResponseEntity.status(200).body(genreService.getAllGenre());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(genreService.getGenreById(id));
    }

    @PutMapping("/update/{genreId}")
    public ResponseEntity<?> updateGenre(@PathVariable Integer genreId, @RequestBody Genre genre) {
        genreService.updateGenre(genreId, genre);
        return ResponseEntity.status(200).body("Genre updated successfully");
    }

    @DeleteMapping("/delete/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable Integer genreId) {
        genreService.deleteGenre(genreId);
        return ResponseEntity.status(200).body("Genre deleted successfully");
    }
}
