package com.ezinne.dtos;

import com.ezinne.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BookRequest implements Serializable {
    private String name;
    private String isbn;
    private String authors;
    private Category category;

}
