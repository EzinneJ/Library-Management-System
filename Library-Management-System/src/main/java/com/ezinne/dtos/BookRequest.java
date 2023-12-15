package com.ezinne.dtos;

import com.ezinne.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookRequest implements Serializable {
    private String name;
    private String isbn;
    private String authors;
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
