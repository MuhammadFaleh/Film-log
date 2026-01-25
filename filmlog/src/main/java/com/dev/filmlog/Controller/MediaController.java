package com.dev.filmlog.Controller;

import com.dev.filmlog.Model.Media;
import com.dev.filmlog.Service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/add")
    public ResponseEntity<?> addMedia(@RequestBody Media media) {
        mediaService.addMedia(media);
        return ResponseEntity.status(200).body("Media added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllMedia() {
        return ResponseEntity.status(200).body(mediaService.getAllMedia());
    }

    @GetMapping("/get/{mediaId}")
    public ResponseEntity<?> getMediaById(@PathVariable Integer mediaId) {
        return ResponseEntity.status(200).body(mediaService.getMediaById(mediaId));
    }

    @PutMapping("/update/{mediaId}")
    public ResponseEntity<?> updateMedia(@PathVariable Integer mediaId, @RequestBody Media media) {
        mediaService.updateMedia(mediaId, media);
        return ResponseEntity.status(200).body("Media updated successfully");
    }

    @DeleteMapping("/delete/{mediaId}")
    public ResponseEntity<?> deleteMedia(@PathVariable Integer mediaId) {
        mediaService.deleteMedia(mediaId);
        return ResponseEntity.status(200).body("Media deleted successfully");
    }

    @PutMapping("/assign-genre/{genreId}/{mediaId}")
    public ResponseEntity<?> assignGenreToMedia(@PathVariable Integer genreId, @PathVariable Integer mediaId) {
        mediaService.assignGenreToMedia(genreId, mediaId);
        return ResponseEntity.status(200).body("Genre assigned to media successfully");
    }

    @PutMapping("/unassign-genre/{genreId}/{mediaId}")
    public ResponseEntity<?> unAssignGenreToMedia(@PathVariable Integer genreId, @PathVariable Integer mediaId) {
        mediaService.unAssignGenreToMedia(genreId, mediaId);
        return ResponseEntity.status(200).body("Genre unassigned from media successfully");
    }

    @PutMapping("/assign-crew/{crewId}/{mediaId}")
    public ResponseEntity<?> assignCrewToMedia(@PathVariable Integer crewId, @PathVariable Integer mediaId) {
        mediaService.assignCrewToMedia(crewId, mediaId);
        return ResponseEntity.status(200).body("Crew assigned to media successfully");
    }

    @PutMapping("/unassign-crew/{crewId}/{mediaId}")
    public ResponseEntity<?> unAssignCrewToMedia(@PathVariable Integer crewId, @PathVariable Integer mediaId) {
        mediaService.unAssignCrewToMedia(crewId, mediaId);
        return ResponseEntity.status(200).body("Crew unassigned from media successfully");
    }
}
