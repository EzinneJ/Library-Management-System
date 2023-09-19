package com.ezinne.dtos;

import com.ezinne.entities.Category;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookRequest implements Serializable {
    private String name;
    private String isbn;
    private String authors;
    private Category category;

}
