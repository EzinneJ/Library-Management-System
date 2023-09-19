package com.ezinne.services;

import com.ezinne.dtos.BookRequest;
import com.ezinne.entities.AppUser;
import com.ezinne.entities.Book;
import com.ezinne.entities.Category;
import com.ezinne.repositories.AppUserRepository;
import com.ezinne.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AppUserRepository appUserRepository;

    public List<Book> listOfBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findByCategory(Category category, String email) {
        validateEmail(email);
        Optional<Book> bookByCategory = bookRepository.findByCategory(category);
        if (bookByCategory.isEmpty()) {
            return List.of();
        }
        Book books = bookByCategory.get();
        return List.of(books);
    }

    public String addBook(String email, BookRequest request) {
        validateEmail(email);
            Book book = new Book();
            book.setName(request.getName());
            book.setIsbn(request.getIsbn());
            book.setAuthors(request.getAuthors());
            book.setCategory(request.getCategory());
            bookRepository.save(book);
            return "Book added successfully";
    }

    public String editBook(Long bookId, String email, Book newBookDetails) {
        validateEmail(email);
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            if (optionalBook.isEmpty()) {
                return "Book not found";
            }
            Book book = optionalBook.get();

            newBookDetails.setBookId(book.getBookId());
            newBookDetails.setName(book.getName());
            newBookDetails.setIsbn(book.getIsbn());
            newBookDetails.setAuthors(book.getAuthors());
            newBookDetails.setCategory(book.getCategory());
            bookRepository.save(newBookDetails);
            return "Book edited successfully";
    }

    public String deleteBook(String email, Long bookId) {
        validateEmail(email);
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            if (optionalBook.isEmpty()) {
                return "Book not found";
            }
            bookRepository.deleteById(bookId);
            return "Book deleted successfully";

    }
    private String validateEmail(String email) {
        Optional<AppUser> emailExist = appUserRepository.findByEmail(email);
        if (emailExist.isPresent()) {
            return email;
        }
        return "User does not exist";
    }
}
