CREATE TABLE IF NOT EXISTS public.books (
    id SERIAL PRIMARY KEY,
    isbn VARCHAR(255),
    title VARCHAR(255),
    price NUMERIC(10, 2),
    author_id INTEGER
);

INSERT INTO books (id, isbn, title, price, author_id)
VALUES (1, 'ISBN-001', 'Libro 1', 10.00, 1), (2, 'ISBN-002', 'Libro 2', 20.00, 2), (3, 'ISBN-001', 'Libro 3', 30.00, 3) ON CONFLICT (id) DO NOTHING;
