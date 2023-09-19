package com.ezinne.services;

import com.ezinne.dtos.AppUserRequest;
import com.ezinne.entities.AppUser;
import com.ezinne.entities.Role;
import com.ezinne.repositories.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    AppUser appUser;

    @BeforeEach
    void setUp() {

    }

    @Test
    void listOfUsers() {
        AppUser admin = new AppUser(
                1L,"Joy","Joy","joy@gmail.com","123abc",Role.ADMIN);
        appUserRepository.save(admin);

        AppUser user = new AppUser(
                2L,"Jay","Jay","jay@gmail.com","123abc",Role.USER);
        appUserRepository.save(user);
        when(appUserRepository.findAll()).thenReturn(List.of(user, admin));

        List<AppUser> appUsers = appUserService.listOfUsers();

        assertThat(appUsers).hasSize(2);
        verify(appUserRepository).findAll();
    }

    @Test
    void createUser() {
        AppUserRequest request = new AppUserRequest("Joy","Joy","joy@gmail.com","123abc",Role.ADMIN);
        AppUser admin = new AppUser();
        admin.setFirstName(request.firstName());
        admin.setLastName(request.lastName());
        admin.setEmail(request.email());
        admin.setPassword(request.password());
        admin.setRole(request.role());
        appUserRepository.save(admin);

        when(appUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(appUserRepository.save(any())).thenReturn(admin);

        String createdMessage = appUserService.createUser(request);

        assertEquals("User account successfully created", createdMessage);
        verify(appUserRepository, times(2)).save(any());
        verify(appUserRepository).findByEmail(anyString());

    }
}