package com.example.AkademyTasks.controller;

import com.example.AkademyTasks.dto.BookAddRequest;
import com.example.AkademyTasks.dto.BookResponse;
import com.example.AkademyTasks.dto.BookUpdateRequest;
import com.example.AkademyTasks.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public PagedModel<BookResponse> getAllBook(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false, defaultValue = "asc") @NotBlank @Size(min = 3, max = 4) String sortType,
            @RequestParam @NotBlank @Size(min = 2) String sortBy) {
        return bookService.getAllBooks(pageable, sortType,sortBy);
    }

    @GetMapping("book/{id}")
    public BookResponse getCurrentBook(@PathVariable @NotBlank Long id) {
        return bookService.getCurrentBook(id);
    }

    @PostMapping("/book")
    public void addBook(@RequestBody @Valid BookAddRequest bookAddRequest) {
        bookService.addBook(bookAddRequest);
    }

    @PutMapping("/book")
    public void updateBookInfo(@RequestBody @Valid BookUpdateRequest bookUpdateRequest) {
        bookService.updateBook(bookUpdateRequest);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable @NotBlank Long id) {
        bookService.deleteBook(id);
    }


}
