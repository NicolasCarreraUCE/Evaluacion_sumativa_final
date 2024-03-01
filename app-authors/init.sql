CREATE TABLE IF NOT EXISTS public.authors (
     id SERIAL PRIMARY KEY,
     first_name VARCHAR(255),
     last_name VARCHAR(255)
);

INSERT INTO authors (id, first_name, last_name)
VALUES (1, 'Nombre 1', 'Apellido 1'), (2, 'Nombre 2', 'Apellido 2'), (3, 'Nombre 3', 'Apellido 3') ON CONFLICT (id) DO NOTHING;
