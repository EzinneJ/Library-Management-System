package com.ezinne.controllers;

import com.ezinne.dtos.BookRequest;
import com.ezinne.entities.Book;
import com.ezinne.entities.Category;
import com.ezinne.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('client_user')")
    public ResponseEntity<List<Book>> findByCategory(@RequestParam Category category){
        List<Book> bookByCategory = bookService.findByCategory(category);
        return ResponseEntity.ok(bookByCategory);
    }

    @GetMapping("/find")
    @PreAuthorize("hasRole('client_user')")
    public ResponseEntity<List<Book>> findByName(@RequestParam String name){
        List<Book> bookByName = bookService.findByName(name);
        return ResponseEntity.ok(bookByName);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('client_user')")
    public ResponseEntity<List<Book>> allBooks(){
        List<Book> books = bookService.listOfBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/add-book")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<String> addBook(@RequestBody BookRequest request){
        String book = bookService.addBook(request);
            return ResponseEntity.ok(book);
        }


    @PutMapping("/edit")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<String> editBook(@RequestParam Long bookId,
                                         @RequestBody Book newBookDetails){
        String response = bookService.editBook(bookId, newBookDetails);
            return ResponseEntity.ok(response);
        }


    @DeleteMapping("delete")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<String> deleteBook(@RequestParam Long bookId){
        String response = bookService.deleteBook(bookId);
            return ResponseEntity.ok(response);
        }

}
