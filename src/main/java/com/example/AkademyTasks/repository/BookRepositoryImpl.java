package com.example.AkademyTasks.repository;

import com.example.AkademyTasks.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveBook(Book book) {
        String sql = "insert into books (title, author, publication_year) values (?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    @Override
    public void deleteBook(Long id) {
        String sql = "delete from books where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "Update books set title = ?, author = ?, publication_year = ? where id = ? ";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getId());
    }

    @Override
    public Optional<Book> findBook(Long id) {
        String sql = "select * from books where id = ?";
        Book book = jdbcTemplate.queryForObject(sql, new BookRowMapper(), id);
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findAllBooks() {
        String sql = "select * from books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPublicationYear(rs.getShort("publication_year"));
            return book;
        }
    }


}
