PGDMP     3    %                y            LibraryDatabase    13.2    13.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16903    LibraryDatabase    DATABASE     r   CREATE DATABASE "LibraryDatabase" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Vietnamese_Vietnam.1258';
 !   DROP DATABASE "LibraryDatabase";
                postgres    false            �            1259    16904    books    TABLE     �  CREATE TABLE public.books (
    book_id integer NOT NULL,
    title character varying(80) NOT NULL,
    author character varying(30) NOT NULL,
    category character varying(20) NOT NULL,
    publisher character varying(30) NOT NULL,
    book_status character varying(30) NOT NULL,
    quantity integer,
    CONSTRAINT books_book_status_check CHECK ((((book_status)::text = 'Available'::text) OR ((book_status)::text = 'Reserved'::text) OR ((book_status)::text = 'Rented'::text)))
);
    DROP TABLE public.books;
       public         heap    postgres    false            �            1259    16908    books_book_id_seq    SEQUENCE     �   CREATE SEQUENCE public.books_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.books_book_id_seq;
       public          postgres    false    200            �           0    0    books_book_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.books_book_id_seq OWNED BY public.books.book_id;
          public          postgres    false    201            �            1259    17021    renting    TABLE     �   CREATE TABLE public.renting (
    transac_id integer NOT NULL,
    book_id integer,
    username character varying(30),
    rented_date date,
    due_date date,
    return_date date
);
    DROP TABLE public.renting;
       public         heap    postgres    false            �            1259    16910    users    TABLE     �  CREATE TABLE public.users (
    username character varying(32) NOT NULL,
    password character varying(32),
    name character varying(30) DEFAULT NULL::character varying,
    email character varying(100) DEFAULT NULL::character varying,
    phone numeric(15,0) DEFAULT NULL::numeric,
    address character varying(100) DEFAULT NULL::character varying,
    CONSTRAINT users_email_check CHECK (((email)::text ~~ '%@%'::text))
);
    DROP TABLE public.users;
       public         heap    postgres    false            *           2604    16918    books book_id    DEFAULT     n   ALTER TABLE ONLY public.books ALTER COLUMN book_id SET DEFAULT nextval('public.books_book_id_seq'::regclass);
 <   ALTER TABLE public.books ALTER COLUMN book_id DROP DEFAULT;
       public          postgres    false    201    200            �          0    16904    books 
   TABLE DATA           c   COPY public.books (book_id, title, author, category, publisher, book_status, quantity) FROM stdin;
    public          postgres    false    200   �       �          0    17021    renting 
   TABLE DATA           d   COPY public.renting (transac_id, book_id, username, rented_date, due_date, return_date) FROM stdin;
    public          postgres    false    203          �          0    16910    users 
   TABLE DATA           P   COPY public.users (username, password, name, email, phone, address) FROM stdin;
    public          postgres    false    202   -       �           0    0    books_book_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.books_book_id_seq', 1, false);
          public          postgres    false    201            2           2606    16920    books books_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);
 :   ALTER TABLE ONLY public.books DROP CONSTRAINT books_pkey;
       public            postgres    false    200            6           2606    17026    renting renting_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.renting
    ADD CONSTRAINT renting_pkey PRIMARY KEY (transac_id);
 >   ALTER TABLE ONLY public.renting DROP CONSTRAINT renting_pkey;
       public            postgres    false    203            4           2606    16922    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    202            7           2606    17027    renting renting_book_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.renting
    ADD CONSTRAINT renting_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books(book_id);
 F   ALTER TABLE ONLY public.renting DROP CONSTRAINT renting_book_id_fkey;
       public          postgres    false    203    2866    200            8           2606    17032    renting renting_username_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.renting
    ADD CONSTRAINT renting_username_fkey FOREIGN KEY (username) REFERENCES public.users(username);
 G   ALTER TABLE ONLY public.renting DROP CONSTRAINT renting_username_fkey;
       public          postgres    false    202    2868    203            �   &  x����n�@��������Q�O�^c0mL�T�z3���ލfw���y�.VZ�IZ����f�9�}5�d+�|��U�E��`�EEƩ�ݑV32�����\�Rv�W��E�T���ApC5vׄb�l�(T�{���b�*-C���Q�ա^1­��c��Lȹ�L%�5�f���g�QcK+0��ۆv�j޴��J����0�X"��ʬ��� %���@�F��nu�2�uTg�kXl�"wm�
��>��潃q����O��+k`��(�Q��+�2���"w��r0N;���:���{|��'���1�,l�n1�o��Iv�X���R�VVM[��Hb����E;���_w�E�mM���k�����{�X�f���?���A������d��4�H���r�
�{d�Z�]^�%oi�N�oH^��q��;��LLх���T�/���yM�<���(Gvg-b,���ݪQ��Ea����aC����V2�\M���?o	�mg���̒��o
��M������F���i�jόP�b�yȨ��s��[���I�����g�      �      x������ � �      �      x�KL����L�1~p����� �E�     