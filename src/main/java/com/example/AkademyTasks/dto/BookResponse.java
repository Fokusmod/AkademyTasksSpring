package com.example.AkademyTasks.dto;

import com.example.AkademyTasks.model.Book;

public class BookResponse {

    private String authorName;

    private String title;

    public BookResponse(String authorName, String title) {
        this.authorName = authorName;
        this.title = title;
    }

    public BookResponse() {
    }

    public BookResponse(Book book) {
        this.title = book.getTitle();
        this.authorName = book.getAuthor().getName();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
