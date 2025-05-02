package com.example.AkademyTasks.service;

import com.example.AkademyTasks.exception.NotFoundException;
import com.example.AkademyTasks.model.Author;
import com.example.AkademyTasks.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthorByName(String name) {
        return authorRepository.getAuthorByName(name)
                .orElseThrow(()-> new NotFoundException("Указанного автора не существует"));
    }

    public Author createNewAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }


}
