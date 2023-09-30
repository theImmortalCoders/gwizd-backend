CREATE DATABASE "data";
CREATE TABLE IF NOT EXISTS "rank" (
    id serial PRIMARY KEY,
    description text,
    name text
);

INSERT INTO "rank" (id, description, name)
VALUES
    (1, '', 'beginner'),
    (2, '', 'intermediate'),
    (3, '', 'advanced'),
    (4, '', 'boss');
