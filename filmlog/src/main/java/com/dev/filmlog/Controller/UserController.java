package com.dev.filmlog.Controller;

import com.dev.filmlog.Api.ApiResponse;
import com.dev.filmlog.DTO.In.UserDTOIn;
import com.dev.filmlog.DTO.Out.UserDTOOut;
import com.dev.filmlog.Model.User;
import com.dev.filmlog.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity<?> getUsers(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTOIn userDTOIn){
        userService.createUser(userDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("user created successfully"));
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTOIn userDTOIn, @AuthenticationPrincipal User user){
        userService.updateUser(userDTOIn, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal User user){
        userService.deleteUser(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(userService.getMyUser(user.getId()));
    }
}
