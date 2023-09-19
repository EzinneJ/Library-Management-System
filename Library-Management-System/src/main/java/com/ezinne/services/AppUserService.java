package com.ezinne.services;

import com.ezinne.dtos.AppUserRequest;
import com.ezinne.entities.AppUser;
import com.ezinne.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {
   private final AppUserRepository appUserRepository;

   private final PasswordEncoder passwordEncoder;

    public List<AppUser> listOfUsers() {
            return appUserRepository.findAll();
    }
    public String createUser(AppUserRequest userRequest){

        String email = userRequest.email();

        Optional<AppUser> userExists = appUserRepository.findByEmail(email);
        if (userExists.isEmpty()) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(userRequest.firstName());
        appUser.setLastName(userRequest.lastName());
        appUser.setEmail(userRequest.email());
        appUser.setPassword(passwordEncoder.encode(userRequest.password()));
        appUser.setRole(userRequest.role());
        appUserRepository.save(appUser);
        return "User account successfully created";
    }
        return "email is taken";
    }
}
