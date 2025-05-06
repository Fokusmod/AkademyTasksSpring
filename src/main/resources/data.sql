DROP TABLE users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_role VARCHAR(255) NOT NULL,
    is_account_non_locked BOOLEAN NOT NULL DEFAULT true
);

-- Вставка данных в таблицу users
INSERT INTO users (username, password, user_role) VALUES
('user1', '$2a$12$RZI60TjpdviV4MtgLn2qzOWcUG3zEAisLuenvA7LTob0YgA00HAEu', 'USER'),
('user2', '$2a$12$siLva5v1KXdMPLSus2k0POEv5Gd7xVoYbx67bCVcn3pmQks0IdkEm', 'SUPER_ADMIN'),
('user3', '$2a$12$0xqIi6fGmZDGXiylsTdz1ufmd7rkayW6ytuV1jRHgaaeneUSGaITu', 'USER'),
('user4', '$2a$12$Bej1Hu0a542pV5Zp4LODE.ZtUeIfRev0Of6MjnhbGgTU3x3WC/f4G', 'MODERATOR'),
('user5', '$2a$12$oVZNWzSWtvEwLjnngPwLcuPAiAJGqzsEIoBFp42wB4xFEVKYFegy6', 'USER');