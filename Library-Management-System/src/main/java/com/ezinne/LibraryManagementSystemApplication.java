package com.ezinne;

import com.ezinne.dtos.BookRequest;
import com.ezinne.entities.Category;
import com.ezinne.repositories.BookRepository;
import com.ezinne.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class LibraryManagementSystemApplication implements CommandLineRunner {

    private final BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bookService.addBook(new BookRequest(
                "Queen primer", "1234321", "Susan", Category.EDUCATIONAL));

        bookService.addBook(new BookRequest(
                "Glorious", "1237891", "Samuel", Category.MOTIVATIONAL));

        bookService.addBook(new BookRequest(
                "The Chase", "12367321", "Susan", Category.THRILLER));

        bookService.addBook(new BookRequest(
                "Fresh love", "62367321", "Ody", Category.ROMANCE));
    }


}
