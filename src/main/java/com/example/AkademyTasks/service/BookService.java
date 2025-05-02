package com.example.AkademyTasks.service;

import com.example.AkademyTasks.dto.BookAddRequest;
import com.example.AkademyTasks.dto.BookResponse;
import com.example.AkademyTasks.dto.BookUpdateRequest;
import com.example.AkademyTasks.exception.NotFoundException;
import com.example.AkademyTasks.model.Author;
import com.example.AkademyTasks.model.Book;
import com.example.AkademyTasks.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public PagedModel<BookResponse> getAllBooks(Pageable pageable, String sortType, String sortBy) {
        String type = sortType.toLowerCase(Locale.ROOT);
        String by = sortBy.toLowerCase(Locale.ROOT);
        Sort sortOrder = Sort.by(by);
        if (type.equals("deck")) sortOrder.descending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortOrder);
        Page<BookResponse> result = bookRepository.findAll(pageable).map(BookResponse::new);
        return new PagedModel<>(result);
    }

    public BookResponse getCurrentBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Указанной книги не существует"));
        return new BookResponse(book);
    }

    public Book getBookByTitle(String title) {
        return bookRepository.getBooksByTitle(title)
                .orElseThrow(()-> new NotFoundException("Книги с таким названием не существует"));
    }

    public void addBook(BookAddRequest bookAddRequest) {
        Book book = new Book();
        Author author = authorService.createNewAuthor(bookAddRequest.getAuthorName());


        book.setTitle(bookAddRequest.getBookTitle());
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public void updateBook(BookUpdateRequest bookUpdateRequest) {
        Book book = getBookByTitle(bookUpdateRequest.getTarget());
        Author author = authorService.getAuthorByName(bookUpdateRequest.getNewAuthorName());
        book.setAuthor(author);
        book.setTitle(bookUpdateRequest.getNewTitle());
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
