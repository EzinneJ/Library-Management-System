package com.ezinne.controllers;

import com.ezinne.dtos.BookRequest;
import com.ezinne.entities.Book;
import com.ezinne.entities.Category;
import com.ezinne.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@Validated
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> findByCategory(@RequestParam Category category){
        List<Book> bookByCategory = bookService.findByCategory(category);
        return ResponseEntity.ok(bookByCategory);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Book>> findByName(@RequestParam String name){
        List<Book> bookByName = bookService.findByName(name);
        return ResponseEntity.ok(bookByName);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> allBooks(){
        List<Book> books = bookService.listOfBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/add-book")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addBook(@RequestBody BookRequest request){
        String book = bookService.addBook(request);
        if (book.contains("Book added successfully")) {
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This is an unauthorised user");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> editBook(@RequestParam Long bookId,
                                         @RequestBody Book newBookDetails){
        String response = bookService.editBook(bookId, newBookDetails);
        if (response.contains("Book edited successfully")){
            return ResponseEntity.ok(response);
        }
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This is an unauthorised user");
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteBook(@RequestParam Long bookId){
        String response = bookService.deleteBook(bookId);
        if (response.contains("Book deleted successfully")){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This is an unauthorised user");
    }

}
