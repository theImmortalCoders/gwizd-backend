CREATE DATABASE "data";
CREATE TABLE IF NOT EXISTS "animal" (
                                      id serial PRIMARY KEY,
                                      description text,
                                      name text,
                                      photo text
);
CREATE TABLE IF NOT EXISTS "achievement" (
                                        id serial PRIMARY KEY,
                                        name text,
                                        image text,
                                        description text
);
INSERT INTO "animal" (id, description, name, photo)
VALUES
    (1, '', 'Łoś', ''),
    (2, '', 'Sarna', ''),
    (3, '', 'Byk', ''),
    (4, '', 'Dzik', '');
INSERT INTO "achievement" (id, description, name, image)
VALUES
    (1, 'Zgłoś 5 zwierząt', 'Zoolog', ''),
    (2, 'Zgłoś sarnę', 'Sarna', ''),
    (3, 'Zgłoś byka', 'Byk', ''),
    (4, 'Zgłoś dzika', 'Dzik', '');
