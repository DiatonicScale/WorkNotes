DELETE FROM users;
DELETE FROM users_roles;
DELETE FROM categories;
DELETE FROM notes;
ALTER SEQUENCE global_seq RESTART WITH 10000;

INSERT INTO users (name, email, password, registered) VALUES
  ('User0', 'user0@gmail.com', 'password0', '2018-08-20 10:00:00'),
  ('User1', 'user1@yandex.ru', 'password1', '2018-08-21 15:00:00');

INSERT INTO users_roles (user_id, role) VALUES
  (10000, 'ROLE_USER'),
  (10001, 'ROLE_ADMIN');

INSERT INTO categories (user_id, name, creation_time, last_edit_time) VALUES
  (10000, 'Java', '2018-08-30 10:00:00', '2018-08-30 10:00:00'),
  (10000, 'Git', '2018-08-29 20:00:00', '2018-08-29 20:00:00'),
  (10000, 'Maven', '2018-08-31 13:00:00', '2018-08-31 13:00:00'),
  (10001, 'Python', '2018-08-25 14:00:00', '2018-08-25 14:00:00'),
  (10001, '.NET', '2018-09-02 21:00:00', '2018-09-02 21:00:00');

INSERT INTO notes (category_id, name, value, creation_time, last_edit_time) VALUES
  (10002, 'Final keyword', 'value final', '2018-08-30 11:30:00', '2018-08-30 11:30:00'),
  (10003, 'Clone command', 'value clone', '2018-08-31 23:12:00', '2018-08-31 23:12:00'),
  (10003, 'Commit', 'value commit', '2018-09-01 11:00:00', '2018-09-01 11:00:00'),
  (10003, 'Push', 'value push', '2018-09-02 20:00:00', '2018-09-02 20:00:00'),
  (10002, 'Remote debug', 'value remote', '2018-08-30 10:00:00', '2018-08-30 10:00:00'),
  (10004, 'Dependencies', 'value depend', '2018-08-31 07:20:00', '2018-08-31 07:20:00'),
  (10002, 'Servlets', 'value servlet', '2018-08-30 01:02:00', '2018-08-30 01:02:00'),
  (10004, 'Skip tests mode', 'value skip test', '2018-08-31 17:00:00', '2018-08-31 17:00:00'),
  (10005, 'Python basics', 'value python basics', '2018-08-25 09:00:00', '2018-08-25 09:00:00'),
  (10006, 'Interfaces in C#', 'value c# interfaces', '2018-09-02 15:41:00', '2018-09-02 15:41:00');