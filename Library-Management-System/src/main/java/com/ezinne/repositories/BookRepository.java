package com.ezinne.repositories;

import com.ezinne.entities.Book;
import com.ezinne.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByCategory(Category category);

    Optional<Book> findByIsbn(String isbn);
}
