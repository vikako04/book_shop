-- Database: book_shop

-- DROP DATABASE IF EXISTS book_shop;

CREATE DATABASE book_shop
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Kazakhstan.1251'
    LC_CTYPE = 'Russian_Kazakhstan.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    avatar character varying COLLATE pg_catalog."default",
    role character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.authors
(
    name character varying COLLATE pg_catalog."default" NOT NULL,
    biography text COLLATE pg_catalog."default",
    photo character varying COLLATE pg_catalog."default",
    CONSTRAINT authors_pkey PRIMARY KEY (name)
)

CREATE TABLE IF NOT EXISTS public.categories
(
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (name)
)

CREATE TABLE IF NOT EXISTS public.books
(
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    price integer NOT NULL,
    availability integer,
    author character varying COLLATE pg_catalog."default",
    rating double precision,
    category character varying COLLATE pg_catalog."default",
    image character varying COLLATE pg_catalog."default",
    CONSTRAINT books_pkey PRIMARY KEY (name),
    CONSTRAINT books_author_fkey FOREIGN KEY (author)
        REFERENCES public.authors (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT books_category_fkey FOREIGN KEY (category)
        REFERENCES public.categories (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.reviews
(
    id integer NOT NULL,
    user_id integer,
    book_name character varying COLLATE pg_catalog."default",
    content text COLLATE pg_catalog."default",
    CONSTRAINT " reviews_pkey" PRIMARY KEY (id),
    CONSTRAINT reviews_book_name_fkey FOREIGN KEY (book_name)
        REFERENCES public.books (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT reviews_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.favorites
(
    user_id integer NOT NULL,
    book_name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT favorites_pkey PRIMARY KEY (user_id, book_name),
    CONSTRAINT favorites_book_name_fkey FOREIGN KEY (book_name)
        REFERENCES public.books (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT favorites_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
