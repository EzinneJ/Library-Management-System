package com.ezinne.controllers;

import com.ezinne.dtos.AppUserRequest;
import com.ezinne.entities.AppUser;
import com.ezinne.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/users")
public class AppUserController {
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody AppUserRequest appUserRequest) {
        String result = appUserService.createUser(appUserRequest);
        String emailIsTaken = "email is taken";
        if (result.contains(emailIsTaken)){
            return ResponseEntity.badRequest().body(emailIsTaken);
        }
        return ResponseEntity.ok("User account successfully created");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AppUser>> listOfUsers(){
        List<AppUser> appUsers = appUserService.listOfUsers();
        return ResponseEntity.ok(appUsers);
    }
}
