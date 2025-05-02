package com.example.AkademyTasks.dto;

import jakarta.validation.constraints.NotBlank;

public class BookAddRequest {

    @NotBlank
    private String bookTitle;
    @NotBlank
    private String authorName;

    public BookAddRequest(String bookTitle, String authorName) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
    }

    public BookAddRequest() {
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
