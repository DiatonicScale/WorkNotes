DELETE FROM users;
DELETE FROM users_roles;
DELETE FROM categories;
DELETE FROM notes;
ALTER SEQUENCE global_seq RESTART WITH 10000;

INSERT INTO users (name, email, password) VALUES
  ('User0', 'user0@gmail.com', 'password0'),
  ('User1', 'user1@yandex.ru', 'password1');

INSERT INTO users_roles (user_id, role) VALUES
  (10000, 'ROLE_USER'),
  (10001, 'ROLE_ADMIN');

INSERT INTO categories (user_id, name) VALUES
  (10000, 'Java'),
  (10000, 'Git'),
  (10000, 'Maven'),
  (10001, 'Python'),
  (10001, '.NET');

INSERT INTO notes (category_id, name, value) VALUES
  (10002, 'Final keyword', 'value final'),
  (10003, 'Clone command', 'value clone'),
  (10003, 'Commit', 'value commit'),
  (10003, 'Push', 'value push'),
  (10002, 'Remote debug', 'value remote'),
  (10004, 'Dependencies', 'value depend'),
  (10002, 'Servlets', 'value servlet'),
  (10004, 'Skip tests mode', 'value skip test'),
  (10005, 'Python basics', 'value python basics'),
  (10006, 'Interfaces in C#', 'value c# interfaces');