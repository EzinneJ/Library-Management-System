package com.ezinne.services;

import com.ezinne.dtos.BookRequest;
import com.ezinne.entities.Book;
import com.ezinne.entities.Category;
import com.ezinne.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public List<Book> listOfBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findByCategory(Category category) {
        Optional<Book> bookByCategory = bookRepository.findByCategory(category);
        if (bookByCategory.isEmpty()) {
            return List.of();
        }
        Book books = bookByCategory.get();
        return List.of(books);
    }

    public List<Book> findByName(String name) {
        Optional<Book> bookByName = bookRepository.findByName(name);
        if (bookByName.isEmpty()) {
            return List.of();
        }
        Book books = bookByName.get();
        return List.of(books);
    }

    public String addBook(BookRequest request) {
            Book book = new Book();
            book.setName(request.getName());
            book.setIsbn(request.getIsbn());
            book.setAuthors(request.getAuthors());
            book.setCategory(request.getCategory());
            bookRepository.save(book);
            return "Book added successfully";
    }

    public String editBook(Long bookId,  Book newBookDetails) {
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

    public String deleteBook(Long bookId) {
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            if (optionalBook.isEmpty()) {
                return "Book not found";
            }
            bookRepository.deleteById(bookId);
            return "Book deleted successfully";

    }
}
