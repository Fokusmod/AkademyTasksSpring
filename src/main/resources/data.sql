DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

CREATE TABLE departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE employees (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   first_name VARCHAR(100),
   last_name VARCHAR(100),
   position VARCHAR(50),
   salary DECIMAL(10,2),
   department_id BIGINT,
   FOREIGN KEY (department_id) REFERENCES departments (id)
);


INSERT INTO departments (name) VALUES ('develop');
INSERT INTO departments (name) VALUES ('test');
INSERT INTO departments (name) VALUES ('production');


INSERT INTO employees (first_name, last_name, position, salary, department_id) VALUES ('Vasya', 'Petrov', 'junior', 50000.00, 1);
INSERT INTO employees (first_name, last_name, position, salary, department_id) VALUES ('Senya', 'Smirnov', 'junior+', 70000.00, 1);
INSERT INTO employees (first_name, last_name, position, salary, department_id) VALUES ('Olga', 'Vetrova', 'trainee', 30000.00, 1);