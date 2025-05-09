package com.example.AkademyTasks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("books")
public class Book {

    @Id
    private Long id;

    private String title;

    private String author;

    private short publicationYear;

    public Book(Long id, String title, String author, short publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public short getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(short publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publicationYear == book.publicationYear && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publicationYear);
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", publicationYear=" + publicationYear +
               '}';
    }
}
