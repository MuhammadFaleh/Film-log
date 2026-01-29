package com.dev.filmlog.Controller;

import com.dev.filmlog.Api.ApiResponse;
import com.dev.filmlog.DTO.In.ProfileDTOIn;
import com.dev.filmlog.DTO.In.UserDTOIn;
import com.dev.filmlog.Model.User;
import com.dev.filmlog.Service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/get-profile")
    public ResponseEntity<?> getProfiles(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(profileService.getProfiles());
    }

    @PostMapping("/update-profile")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal User user, @Valid @RequestBody ProfileDTOIn profileDTOIn){
        profileService.updateProfile(profileDTOIn, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("profile updated successfully"));
    }


    @GetMapping("/get-profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(profileService.getMyProfile(user.getId()));
    }
}
