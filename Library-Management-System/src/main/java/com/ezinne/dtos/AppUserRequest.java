package com.ezinne.dtos;

import com.ezinne.entities.AppUser;
import com.ezinne.entities.Role;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * A DTO for the {@link AppUser} entity
 */
public record AppUserRequest(String firstName, String lastName,
                             String email,  String password,
                             Role role) implements Serializable {
}