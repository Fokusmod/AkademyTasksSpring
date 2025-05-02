package com.example.AkademyTasks.repository;

import com.example.AkademyTasks.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> getAuthorByName(String name);
}
