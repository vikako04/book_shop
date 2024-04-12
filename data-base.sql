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

-- Table: public.authors

-- DROP TABLE IF EXISTS public.authors;

CREATE TABLE IF NOT EXISTS public.authors
(
    author_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    full_name text COLLATE pg_catalog."default" NOT NULL,
    "biography " text COLLATE pg_catalog."default" NOT NULL,
    photo character varying COLLATE pg_catalog."default",
    years_of_life text COLLATE pg_catalog."default" NOT NULL,
    years_of_creativity text COLLATE pg_catalog."default" NOT NULL,
    biography character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT authors_pkey PRIMARY KEY (author_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.authors
    OWNER to postgres;

-- Table: public.categories

-- DROP TABLE IF EXISTS public.categories;

CREATE TABLE IF NOT EXISTS public.categories
(
    category_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    category_name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_id PRIMARY KEY (category_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.categories
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    username character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    role character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_id PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

-- Table: public.author_category

-- DROP TABLE IF EXISTS public.author_category;

CREATE TABLE IF NOT EXISTS public.author_category
(
    a_c_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    author_id integer NOT NULL,
    category_id integer NOT NULL,
    CONSTRAINT a_c_id PRIMARY KEY (a_c_id),
    CONSTRAINT author_id FOREIGN KEY (author_id)
        REFERENCES public.authors (author_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT category_id FOREIGN KEY (category_id)
        REFERENCES public.categories (category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.author_category
    OWNER to postgres;

-- Table: public.books

-- DROP TABLE IF EXISTS public.books;

CREATE TABLE IF NOT EXISTS public.books
(
    book_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    book_name character varying COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    image character varying COLLATE pg_catalog."default" NOT NULL,
    author_id integer NOT NULL,
    price integer NOT NULL,
    availability integer NOT NULL,
    rating integer NOT NULL,
    number_of_pages integer NOT NULL,
    CONSTRAINT book_id PRIMARY KEY (book_id),
    CONSTRAINT author_id FOREIGN KEY (author_id)
        REFERENCES public.authors (author_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.books
    OWNER to postgres;

-- Table: public.book_category

-- DROP TABLE IF EXISTS public.book_category;

CREATE TABLE IF NOT EXISTS public.book_category
(
    b_c_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    book_id integer NOT NULL,
    category_id integer NOT NULL,
    CONSTRAINT b_c_id PRIMARY KEY (b_c_id),
    CONSTRAINT book_id FOREIGN KEY (book_id)
        REFERENCES public.books (book_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT category_id FOREIGN KEY (category_id)
        REFERENCES public.categories (category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book_category
    OWNER to postgres;

-- Table: public.cart

-- DROP TABLE IF EXISTS public.cart;

CREATE TABLE IF NOT EXISTS public.cart
(
    cart_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    book_id integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT cart_id PRIMARY KEY (cart_id),
    CONSTRAINT book_id FOREIGN KEY (book_id)
        REFERENCES public.books (book_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cart
    OWNER to postgres;

-- Table: public.favorites

-- DROP TABLE IF EXISTS public.favorites;

CREATE TABLE IF NOT EXISTS public.favorites
(
    f_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    book_id integer NOT NULL,
    CONSTRAINT f_id PRIMARY KEY (f_id),
    CONSTRAINT book_id FOREIGN KEY (book_id)
        REFERENCES public.books (book_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.favorites
    OWNER to postgres;

-- Table: public.reviews

-- DROP TABLE IF EXISTS public.reviews;

CREATE TABLE IF NOT EXISTS public.reviews
(
    review_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    book_id integer NOT NULL,
    rating integer NOT NULL,
    content text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT review_id PRIMARY KEY (review_id),
    CONSTRAINT book_id FOREIGN KEY (book_id)
        REFERENCES public.books (book_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.reviews
    OWNER to postgres;