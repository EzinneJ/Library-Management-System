package com.ezinne.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Entity(name = "books")
@Table
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookId", nullable = false)
    private Long bookId;
   @NotBlank
   private String name;
    @NotBlank
    @Column(unique = true)
    private String isbn;

    @NotBlank
    private String authors;
    @NotBlank
    private Category category;

    public Book(String name, String isbn, String authors, Category category) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.category = category;
    }


}
