package com.example.AkademyTasks.service;

import com.example.AkademyTasks.model.Book;
import com.example.AkademyTasks.repository.BookRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepositoryImpl bookRepository;

    public BookService(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBooks(Book book) {
        bookRepository.saveBook(book);
    }

    public Book findBook(Long id) {
        return bookRepository.findBook(id).orElse(new Book());
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }

    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }

    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }


}
