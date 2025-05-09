package com.example.AkademyTasks.repository;

import com.example.AkademyTasks.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void saveBook(Book book);

    void deleteBook(Long id);

    void updateBook(Book book);

    Optional<Book> findBook(Long id);

    List<Book> findAllBooks();
}
