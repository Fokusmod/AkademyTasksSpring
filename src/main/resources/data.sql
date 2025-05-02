DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

INSERT INTO authors (name) VALUES ('John Doe');
INSERT INTO authors (name) VALUES ('Jane Smith');

INSERT INTO books (title, author_id) VALUES ('kids', 1);
INSERT INTO books (title, author_id) VALUES ('kids 2', 1);
INSERT INTO books (title, author_id) VALUES ('kids 3', 1);
INSERT INTO books (title, author_id) VALUES ('Java', 2);
INSERT INTO books (title, author_id) VALUES ('Kotlin', 2);

