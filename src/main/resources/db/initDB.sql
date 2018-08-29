DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS users_roles CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS notes;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 10000;

CREATE TABLE users
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now()
);
CREATE UNIQUE INDEX unique_email ON users (email);
CREATE UNIQUE INDEX unique_name ON users (name);

CREATE TABLE users_roles
(
  user_id INTEGER NOT NULL,
  role VARCHAR NOT NULL,
  CONSTRAINT users_roles_con UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE categories
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id INTEGER NOT NULL,
  name VARCHAR NOT NULL,
  creation_time TIMESTAMP DEFAULT now(),
  last_edit_time TIMESTAMP DEFAULT now(),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE notes
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  category_id INTEGER NOT NULL,
  name VARCHAR,
  value VARCHAR NOT NULL,
  creation_time TIMESTAMP DEFAULT now(),
  last_edit_time TIMESTAMP DEFAULT now(),
  FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);