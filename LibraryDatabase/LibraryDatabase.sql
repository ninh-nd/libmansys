PGDMP     !    
                y            Library    13.2    13.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24851    Library    DATABASE     e   CREATE DATABASE "Library" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_World.1252';
    DROP DATABASE "Library";
                postgres    false            �            1259    24864    books    TABLE     8  CREATE TABLE public.books (
    book_id integer NOT NULL,
    title character varying(30) NOT NULL,
    author character varying(30) NOT NULL,
    category character varying(20) NOT NULL,
    publisher character varying(30) NOT NULL,
    book_status character varying(30) NOT NULL,
    rented_date date,
    due_date date,
    return_date date,
    renter_username character varying(32),
    CONSTRAINT books_book_status_check CHECK ((((book_status)::text = 'Available'::text) OR ((book_status)::text = 'Reserved'::text) OR ((book_status)::text = 'Rented'::text)))
);
    DROP TABLE public.books;
       public         heap    postgres    false            �            1259    24862    books_book_id_seq    SEQUENCE     �   CREATE SEQUENCE public.books_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.books_book_id_seq;
       public          postgres    false    202            �           0    0    books_book_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.books_book_id_seq OWNED BY public.books.book_id;
          public          postgres    false    201            �            1259    24852    users    TABLE     �  CREATE TABLE public.users (
    username character varying(32) NOT NULL,
    password character varying(32),
    name character varying(30) DEFAULT NULL::character varying,
    email character varying(100) DEFAULT NULL::character varying,
    phone numeric(15,0) DEFAULT NULL::numeric,
    address character varying(100) DEFAULT NULL::character varying,
    CONSTRAINT users_email_check CHECK (((email)::text ~~ '%@%'::text))
);
    DROP TABLE public.users;
       public         heap    postgres    false            +           2604    24867    books book_id    DEFAULT     n   ALTER TABLE ONLY public.books ALTER COLUMN book_id SET DEFAULT nextval('public.books_book_id_seq'::regclass);
 <   ALTER TABLE public.books ALTER COLUMN book_id DROP DEFAULT;
       public          postgres    false    202    201    202            �          0    24864    books 
   TABLE DATA           �   COPY public.books (book_id, title, author, category, publisher, book_status, rented_date, due_date, return_date, renter_username) FROM stdin;
    public          postgres    false    202   T       �          0    24852    users 
   TABLE DATA           P   COPY public.users (username, password, name, email, phone, address) FROM stdin;
    public          postgres    false    200   q       �           0    0    books_book_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.books_book_id_seq', 1, false);
          public          postgres    false    201            0           2606    24870    books books_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);
 :   ALTER TABLE ONLY public.books DROP CONSTRAINT books_pkey;
       public            postgres    false    202            .           2606    24857    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    200            1           2606    24871     books books_renter_username_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_renter_username_fkey FOREIGN KEY (renter_username) REFERENCES public.users(username);
 J   ALTER TABLE ONLY public.books DROP CONSTRAINT books_renter_username_fkey;
       public          postgres    false    2862    202    200            �      x������ � �      �      x�KL����L�1~p����� �E�     