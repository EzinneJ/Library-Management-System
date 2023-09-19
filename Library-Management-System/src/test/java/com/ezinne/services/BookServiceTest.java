package com.ezinne.services;

import com.ezinne.dtos.BookRequest;
import com.ezinne.entities.AppUser;
import com.ezinne.entities.Book;
import com.ezinne.entities.Category;
import com.ezinne.entities.Role;
import com.ezinne.repositories.AppUserRepository;
import com.ezinne.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AppUserRepository appUserRepository;
    @InjectMocks
    private BookService bookService;
    @Mock
    Book book;
    @Mock
    BookRequest bookRequest;
    @Mock
    AppUser admin;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setBookId(1L);
        book.setName("Value");
        book.setIsbn("12345");
        book.setAuthors("John Doe");
        book.setCategory(Category.EDUCATIONAL);
        bookRepository.save(book);

         admin = new AppUser();
        admin.setUserId(1L);
        admin.setFirstName("Joy");
        admin.setLastName("Joy");
        admin.setEmail("joy@gmail.com");
        admin.setPassword("123abc");
        admin.setRole(Role.ADMIN);
        appUserRepository.save(admin);
    }

    @Test
    void listOfBooks() {
        Book book2 = new Book(
                "Love", "125123", "Jane Doe", Category.ROMANCE);
        bookRepository.save(book2);
        when(bookRepository.findAll()).thenReturn(List.of(book, book2));

        List<Book> books = bookService.listOfBooks();

        assertThat(books).hasSize(2);
        verify(bookRepository).findAll();
    }

    @Test
    void findByCategory(){
        when(bookRepository.findByCategory(Category.EDUCATIONAL))
                .thenReturn(Optional.of(book));

        List<Book> bookCategory = bookService.findByCategory(
                Category.EDUCATIONAL, admin.getEmail());

        assertThat(bookCategory).hasSize(1);
        assertEquals("EDUCATIONAL", String.valueOf(book.getCategory()));
        verify(bookRepository).findByCategory(any());
    }

    @Test
    void addBook() {
        when(bookRepository.save(any())).thenReturn(book);

        bookService.addBook(admin.getEmail(), bookRequest);

        assertEquals(Role.valueOf("ADMIN"), admin.getRole());
        verify(bookRepository, times(2)).save(any());
    }

    /*@Test
    void editBook(){
        Book newBookDetails = new Book();
        newBookDetails.setName("Valuable");
        book.setName(newBookDetails.getName());
        bookRepository.save(book);

        when(validations.validateUserRole(any())).thenReturn(anyString());
        lenient().when(validations.validateBookExists(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenReturn(book);

        Book book1 = bookService.editBook(book.getBookId(), admin.getEmail(), book);

        assertEquals("Valuable", book.getName());
    }*/


}