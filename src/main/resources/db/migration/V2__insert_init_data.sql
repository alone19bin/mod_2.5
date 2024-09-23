INSERT INTO users (id, user_name, email, password, created, updated, role, status)
VALUES (1, 'User', 'user@mail.ru', 'user', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'USER', 'ACTIVE');

INSERT INTO users (id, user_name, email, password, created, updated, role, status)
VALUES (2, 'Moderator', 'moderator@mail.ru', 'moderator', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'MODERATOR', 'ACTIVE');

INSERT INTO users (id, user_name, email, password, created, updated, role, status)
VALUES (3, 'Admin', 'admin@mail.ru', 'admin', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ADMIN', 'ACTIVE');