CREATE TABLE IF NOT EXISTS customers (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    contact_number VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS products (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT,
    price DECIMAL(10, 2),
    quantity_in_stock INT
);

CREATE TABLE IF NOT EXISTS orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    shipping_address VARCHAR(255),
    total_price DECIMAL(10, 2),
    order_status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE IF NOT EXISTS order_products (
    order_id BIGINT,
    product_id BIGINT,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

ALTER TABLE orders ALTER COLUMN order_date SET DEFAULT CURRENT_TIMESTAMP;

INSERT INTO customers (first_name, last_name, email, contact_number) VALUES
('John', 'Doe', 'john.doe@example.com', '1234567890'),
('Jane', 'Smith', 'jane.smith@example.com', '0987654321');

INSERT INTO products (name, description, price, quantity_in_stock) VALUES
('Laptop', 'High-performance laptop', 1200.00, 10),
('Smartphone', 'Latest model smartphone', 800.00, 20),
('Headphones', 'Noise-cancelling headphones', 150.00, 30);

INSERT INTO orders (customer_id, shipping_address, total_price, order_status) VALUES
(1, '123 Elm St, Springfield', 1200.00, 'Shipped'),
(2, '456 Oak St, Springfield', 950.00, 'Processing');

INSERT INTO order_products (order_id, product_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 1),
(2, 2),
(2, 3);