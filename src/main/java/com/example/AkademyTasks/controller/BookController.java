package com.example.AkademyTasks.controller;


import com.example.AkademyTasks.model.Book;
import com.example.AkademyTasks.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findBook(id);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/book")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @PostMapping("/book")
    public void saveBook(@RequestBody Book book) {
        bookService.saveBooks(book);
    }
}
