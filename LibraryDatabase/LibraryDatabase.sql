PGDMP         4                y            Library    13.2    13.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16395    Library    DATABASE     e   CREATE DATABASE "Library" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_World.1252';
    DROP DATABASE "Library";
                postgres    false            �            1259    16396    books    TABLE       CREATE TABLE public.books (
    book_id integer NOT NULL,
    title character varying(80) NOT NULL,
    author character varying(30) NOT NULL,
    category character varying(20) NOT NULL,
    publisher character varying(30) NOT NULL,
    book_status character varying(30) NOT NULL
);
    DROP TABLE public.books;
       public         heap    postgres    false            �            1259    16400    books_book_id_seq    SEQUENCE     �   CREATE SEQUENCE public.books_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.books_book_id_seq;
       public          postgres    false    200            �           0    0    books_book_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.books_book_id_seq OWNED BY public.books.book_id;
          public          postgres    false    201            �            1259    16532    history    TABLE     �   CREATE TABLE public.history (
    username character varying(30),
    book_id integer,
    rented_date date,
    return_date date
);
    DROP TABLE public.history;
       public         heap    postgres    false            �            1259    16402    renting    TABLE     �   CREATE TABLE public.renting (
    book_id integer,
    username character varying(30),
    rented_date date,
    due_date date
);
    DROP TABLE public.renting;
       public         heap    postgres    false            �            1259    16405    users    TABLE     �  CREATE TABLE public.users (
    username character varying(32) NOT NULL,
    password character varying(32),
    name character varying(30) DEFAULT NULL::character varying,
    email character varying(100) DEFAULT NULL::character varying,
    address character varying(100) DEFAULT NULL::character varying,
    phone character varying(15),
    CONSTRAINT phone_number_check CHECK (((phone)::text !~~ '%[^0-9]%'::text)),
    CONSTRAINT users_email_check CHECK (((email)::text ~~ '%@%'::text))
);
    DROP TABLE public.users;
       public         heap    postgres    false            /           2604    16413    books book_id    DEFAULT     n   ALTER TABLE ONLY public.books ALTER COLUMN book_id SET DEFAULT nextval('public.books_book_id_seq'::regclass);
 <   ALTER TABLE public.books ALTER COLUMN book_id DROP DEFAULT;
       public          postgres    false    201    200            �          0    16396    books 
   TABLE DATA           Y   COPY public.books (book_id, title, author, category, publisher, book_status) FROM stdin;
    public          postgres    false    200   �       �          0    16532    history 
   TABLE DATA           N   COPY public.history (username, book_id, rented_date, return_date) FROM stdin;
    public          postgres    false    204   �       �          0    16402    renting 
   TABLE DATA           K   COPY public.renting (book_id, username, rented_date, due_date) FROM stdin;
    public          postgres    false    202   �       �          0    16405    users 
   TABLE DATA           P   COPY public.users (username, password, name, email, address, phone) FROM stdin;
    public          postgres    false    203          �           0    0    books_book_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.books_book_id_seq', 16, true);
          public          postgres    false    201            6           2606    16415    books books_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);
 :   ALTER TABLE ONLY public.books DROP CONSTRAINT books_pkey;
       public            postgres    false    200            8           2606    16419    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    203            9           2606    16420    renting renting_book_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.renting
    ADD CONSTRAINT renting_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books(book_id);
 F   ALTER TABLE ONLY public.renting DROP CONSTRAINT renting_book_id_fkey;
       public          postgres    false    200    2870    202            :           2606    16425    renting renting_username_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.renting
    ADD CONSTRAINT renting_username_fkey FOREIGN KEY (username) REFERENCES public.users(username);
 G   ALTER TABLE ONLY public.renting DROP CONSTRAINT renting_username_fkey;
       public          postgres    false    203    202    2872            �   /  x��SKn�0]ӧ�U�jP��v�(��6r�IQ���4���`H�Un�s�b���U���@�y�a_ �;_���j�lP49��"���nI�)�u`�V�|�[d�KM�c50����5�d<�\�a�V�P�{�!|	Vi
�l�J���noI��B�u�OT"\�)a��a�6����y���U�(=UiX��$�W�5V�P\����	M��9.���T����Ϭ5c�V��aR��5F��v�y�`��.�\}�U�`,6�6��]�5C�czq;�,��~��b79�������� ������8���+�'��|��:��*��l�rUF����6�G�@����jfk�m����.�Hb��M���V<�m�0%E�iI"���T��!��*�����-lԃ���9�,j��3��]��*����]_^��#}3��]���`$�F�ݲQãya������u���d6����L��ŏc�e�[G�SK^�������g-�Z���i�i��P�b�yȨ��p�Ϳ���V�[���ϯ�_�z��w4xr      �      x������ � �      �      x������ � �      �   d   x�KL����L�1~pĕ�KI-.��K/�|��5O!��؆����+8�q����'���:��&f��%��rz$��g�(�e���%�̊���� ��$     