package com.dev.filmlog.Controller;

import com.dev.filmlog.Model.Genre;
import com.dev.filmlog.Model.User;
import com.dev.filmlog.Service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/add")
    public ResponseEntity<?> addGenre(@AuthenticationPrincipal User user,@Valid @RequestBody Genre genre) {
        genreService.addGenre(genre);
        return ResponseEntity.status(200).body("Genre added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllGenre(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(genreService.getAllGenre());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getGenreById(@AuthenticationPrincipal User user,@PathVariable Integer id) {
        return ResponseEntity.status(200).body(genreService.getGenreById(id));
    }

    @PutMapping("/update/{genreId}")
    public ResponseEntity<?> updateGenre(@AuthenticationPrincipal User user,@PathVariable Integer genreId,@Valid @RequestBody Genre genre) {
        genreService.updateGenre(genreId, genre);
        return ResponseEntity.status(200).body("Genre updated successfully");
    }

    @DeleteMapping("/delete/{genreId}")
    public ResponseEntity<?> deleteGenre(@AuthenticationPrincipal User user,@PathVariable Integer genreId) {
        genreService.deleteGenre(genreId);
        return ResponseEntity.status(200).body("Genre deleted successfully");
    }
}
