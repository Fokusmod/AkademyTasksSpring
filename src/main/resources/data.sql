DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS order_statuses;

CREATE TABLE order_statuses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255)
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_cost DECIMAL(10, 2),
    user_id BIGINT,
    order_status_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (order_status_id) REFERENCES order_statuses(id)
);

INSERT INTO order_statuses (title) VALUES ('Pending');
INSERT INTO order_statuses (title) VALUES ('Shipped');
INSERT INTO order_statuses (title) VALUES ('Delivered');

INSERT INTO users (name, email) VALUES ('John Doe', 'john@example.com');
INSERT INTO users (name, email) VALUES ('Jane Smith', 'jane@example.com');

INSERT INTO orders (order_cost, user_id, order_status_id) VALUES (99.99, 1, 1);
INSERT INTO orders (order_cost, user_id, order_status_id) VALUES (49.49, 2, 2);
INSERT INTO orders (order_cost, user_id, order_status_id) VALUES (19.99, 1, 3);