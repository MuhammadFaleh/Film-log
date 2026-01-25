package com.dev.filmlog.Controller;

import com.dev.filmlog.Model.Crew;
import com.dev.filmlog.Service.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crew")
@RequiredArgsConstructor
public class CrewController {

    private final CrewService crewService;

    @PostMapping("/add")
    public ResponseEntity<?> addCrew(@RequestBody Crew crew) {
        crewService.addCrew(crew);
        return ResponseEntity.status(200).body("Crew added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllCrew() {
        return ResponseEntity.status(200).body(crewService.getAllCrew());
    }

    @GetMapping("/get/{crewId}")
    public ResponseEntity<?> getCrewById(@PathVariable Integer crewId) {
        return ResponseEntity.status(200).body(crewService.getCrewById(crewId));
    }

    @PutMapping("/update/{crewId}")
    public ResponseEntity<?> updateCrew(@PathVariable Integer crewId, @RequestBody Crew crew) {
        crewService.updateCrew(crewId, crew);
        return ResponseEntity.status(200).body("Crew updated successfully");
    }

    @DeleteMapping("/delete/{crewId}")
    public ResponseEntity<?> deleteCrew(@PathVariable Integer crewId) {
        crewService.deleteCrew(crewId);
        return ResponseEntity.status(200).body("Crew deleted successfully");
    }
}
