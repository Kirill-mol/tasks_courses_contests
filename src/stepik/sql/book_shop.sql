/*Создаем схему*/
DROP SCHEMA IF EXISTS stepik CASCADE;
CREATE SCHEMA IF NOT EXISTS stepik;

---AUTHOR
DROP TABLE IF EXISTS stepik.author CASCADE;
CREATE TABLE IF NOT EXISTS stepik.author
(
    author_id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_author TEXT
);
INSERT INTO stepik.author(name_author)
VALUES ('Булгаков М.А.'),
       ('Достоевский Ф.М.'),
       ('Есенин С.А.'),
       ('Пастернак Б.Л.'),
       ('Лермонтов М.Ю.');

---GENRE
DROP TABLE IF EXISTS stepik.genre CASCADE;
CREATE TABLE IF NOT EXISTS stepik.genre
(
    genre_id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_genre TEXT
);
INSERT INTO stepik.genre(name_genre)
VALUES ('Роман'),
       ('Поэзия'),
       ('Приключения');


---BOOK
DROP TABLE IF EXISTS stepik.book CASCADE;
CREATE TABLE IF NOT EXISTS stepik.book
(
    book_id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title     TEXT,
    author_id BIGINT NOT NULL,
    genre_id  BIGINT,
    price     DECIMAL(8, 2),
    amount    INT,
    CONSTRAINT "FK_book_author"
        FOREIGN KEY (author_id) REFERENCES stepik.author (author_id) ON DELETE CASCADE,
    CONSTRAINT "FK_book_genre"
        FOREIGN KEY (genre_id) REFERENCES stepik.genre (genre_id) ON DELETE SET NULL
);
INSERT INTO stepik.book(title, author_id, genre_id, price, amount)
VALUES ('Мастер и Маргарита', 1, 1, 670.99, 3),
       ('Белая гвардия', 1, 1, 540.50, 5),
       ('Идиот', 2, 1, 460.00, 10),
       ('Братья Карамазовы', 2, 1, 799.01, 2),
       ('Игрок', 2, 1, 480.50, 10),
       ('Стихотворения и поэмы', 3, 2, 650.00, 15),
       ('Черный человек', 3, 2, 570.20, 6),
       ('Лирика', 4, 2, 518.99, 2);


---CITY
DROP TABLE IF EXISTS stepik.city CASCADE;
CREATE TABLE IF NOT EXISTS stepik.city
(
    city_id       BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_city     TEXT,
    days_delivery INT
);
INSERT INTO stepik.city(name_city, days_delivery)
VALUES ('Москва', 5),
       ('Санкт-Петербург', 3),
       ('Владивосток', 12);


---CLIENT
CREATE EXTENSION IF NOT EXISTS citext;
DROP DOMAIN IF EXISTS email;
CREATE DOMAIN email AS citext
    CHECK ( value ~
            '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' );

DROP TABLE IF EXISTS stepik.client CASCADE;
CREATE TABLE IF NOT EXISTS stepik.client
(
    client_id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_client TEXT,
    city_id     BIGINT,
    email       email,
    CONSTRAINT "FK_client_city"
        FOREIGN KEY (city_id) REFERENCES stepik.city (city_id)
);
INSERT INTO stepik.client(name_client, city_id, email)
VALUES ('Баранов Павел', 3, 'baranov@test'),
       ('Абрамова Катя', 1, 'abramova@test'),
       ('Семенонов Иван', 2, 'semenov@test'),
       ('Яковлева Галина', 1, 'yakovleva@test');


---BUY
DROP TABLE IF EXISTS stepik.buy CASCADE;
CREATE TABLE IF NOT EXISTS stepik.buy
(
    buy_id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    buy_description TEXT,
    client_id       BIGINT DEFAULT (NULL),
    CONSTRAINT "FK_buy_client"
        FOREIGN KEY (client_id) REFERENCES stepik.client (client_id)
);
INSERT INTO stepik.buy (buy_description, client_id)
VALUES ('Доставка только вечером', 1),
       (NULL, 3),
       ('Упаковать каждую книгу по отдельности', 2),
       (NULL, 1);


---BUY_BOOK
DROP TABLE IF EXISTS stepik.buy_book CASCADE;
CREATE TABLE IF NOT EXISTS stepik.buy_book
(
    buy_book_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    buy_id      BIGINT,
    book_id     BIGINT,
    amount      INT,
    CONSTRAINT "FK_buy_book_buy"
        FOREIGN KEY (buy_id) REFERENCES stepik.buy (buy_id),
    CONSTRAINT "FK_buy_book_book"
        FOREIGN KEY (book_id) REFERENCES stepik.book (book_id)
);
INSERT INTO stepik.buy_book(buy_id, book_id, amount)
VALUES (1, 1, 1),
       (1, 7, 2),
       (2, 8, 2),
       (3, 3, 2),
       (3, 2, 1),
       (3, 1, 1),
       (4, 5, 1);


---STEP
DROP TABLE IF EXISTS stepik.step CASCADE;
CREATE TABLE IF NOT EXISTS stepik.step
(
    step_id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_step TEXT
);
INSERT INTO stepik.step(name_step)
VALUES ('Оплата'),
       ('Упаковка'),
       ('Транспортировка'),
       ('Доставка');


---BUY_STEP
DROP TABLE IF EXISTS stepik.buy_step CASCADE;
CREATE TABLE IF NOT EXISTS stepik.buy_step
(
    buy_step_id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    buy_id        INT,
    step_id       INT,
    date_step_beg DATE,
    date_step_end DATE,
    CONSTRAINT "FK_buy_step_buy"
        FOREIGN KEY (buy_id) REFERENCES stepik.buy (buy_id),
    CONSTRAINT "FK_buy_step_step"
        FOREIGN KEY (step_id) REFERENCES stepik.step (step_id)
);
INSERT INTO stepik.buy_step(buy_id, step_id, date_step_beg, date_step_end)
VALUES (1, 1, '2020-02-20', '2020-02-20'),
       (1, 2, '2020-02-20', '2020-02-21'),
       (1, 3, '2020-02-22', '2020-03-07'),
       (1, 4, '2020-03-08', '2020-03-08'),
       (2, 1, '2020-02-28', '2020-02-28'),
       (2, 2, '2020-02-29', '2020-03-01'),
       (2, 3, '2020-03-02', NULL),
       (2, 4, NULL, NULL),
       (3, 1, '2020-03-05', '2020-03-05'),
       (3, 2, '2020-03-05', '2020-03-06'),
       (3, 3, '2020-03-06', '2020-03-10'),
       (3, 4, '2020-03-11', NULL),
       (4, 1, '2020-03-20', NULL),
       (4, 2, NULL, NULL),
       (4, 3, NULL, NULL),
       (4, 4, NULL, NULL);

/*
 Вывести все заказы Баранова Павла (id заказа, какие книги, по какой цене
 и в каком количестве он заказал)
 в отсортированном по номеру заказа и названиям книг виде.
 */

SELECT buy_id, title, price, buy_book.amount
FROM stepik.client
         INNER JOIN stepik.buy USING (client_id)
         INNER JOIN stepik.buy_book USING (buy_id)
         INNER JOIN stepik.book USING (book_id)
WHERE client.name_client = 'Баранов Павел'
ORDER BY 1, 2;

/*
 Посчитать, сколько раз была заказана каждая книга, для книги вывести ее автора
 (нужно посчитать, в каком количестве заказов фигурирует каждая книга).
 Вывести фамилию и инициалы автора, название книги, последний столбец назвать Количество.
 Результат отсортировать сначала  по фамилиям авторов, а потом по названиям книг.
 */
SELECT name_author, title, COUNT(buy_book.amount) AS Количество
FROM stepik.author
         INNER JOIN stepik.book USING (author_id)
         LEFT JOIN stepik.buy_book USING (book_id)
GROUP BY name_author, title
ORDER BY 1, 2;

/*
 Вывести города, в которых живут клиенты, оформлявшие заказы в интернет-магазине.
 Указать количество заказов в каждый город, этот столбец назвать Количество.
 Информацию вывести по убыванию количества заказов,
 а затем в алфавитном порядке по названию городов.
 */
SELECT name_city, COUNT(*) AS Количество
FROM stepik.city
         INNER JOIN stepik.client c on city.city_id = c.city_id
         INNER JOIN stepik.buy USING (client_id)
GROUP BY name_city
ORDER BY 2 DESC, 1;

/*
 Вывести номера всех оплаченных заказов и даты, когда они были оплачены.
 */

SELECT buy_id, date_step_end
FROM stepik.step
         RIGHT JOIN stepik.buy_step USING (step_id)
WHERE step_id = 1
  AND date_step_end IS NOT NULL;

SELECT *
FROM stepik.step;

/*
 Вывести информацию о каждом заказе: его номер, кто его сформировал
 (фамилия пользователя) и его стоимость (сумма произведений количества заказанных книг и их цены),
 в отсортированном по номеру заказа виде. Последний столбец назвать Стоимость.
 */

SELECT buy_id, name_client, SUM(buy_book.amount * price) AS Стоимость
FROM stepik.client
         INNER JOIN stepik.buy USING (client_id)
         INNER JOIN stepik.buy_book USING (buy_id)
         INNER JOIN stepik.book USING (book_id)
GROUP BY buy_id, name_client
ORDER BY buy_id;

/*
 Вывести номера заказов (buy_id) и названия этапов, на которых они в данный момент находятся.
 Если заказ доставлен –  информацию о нем не выводить. Информацию отсортировать по возрастанию buy_id.
 */

SELECT buy_id, name_step
FROM stepik.step
         INNER JOIN stepik.buy_step USING (step_id)
WHERE (date_step_beg IS NOT NULL)
  AND (date_step_end IS NULL)
ORDER BY 1;

/*
В таблице city для каждого города указано количество дней,
за которые заказ может быть доставлен в этот город
(рассматривается только этап "Транспортировка").
Для тех заказов, которые прошли этап транспортировки,
вывести количество дней за которое заказ реально доставлен в город.
А также, если заказ доставлен с опозданием, указать количество дней задержки,
в противном случае вывести 0. В результат включить номер заказа (buy_id),
а также вычисляемые столбцы Количество_дней и Опоздание.
Информацию вывести в отсортированном по номеру заказа виде.
 */

SELECT buy_id,
       date_step_end - date_step_beg                 AS Количество_дней,
       date_step_end - date_step_beg - days_delivery AS Опоздание
FROM stepik.city
         INNER JOIN stepik.client USING (city_id)
         INNER JOIN stepik.buy USING (client_id)
         INNER JOIN stepik.buy_step USING (buy_id)
         INNER JOIN stepik.step USING (step_id)
WHERE step_id = 3
  AND date_step_end IS NOT NULL
ORDER BY 1;

/*
 Выбрать всех клиентов, которые заказывали книги Достоевского,
 информацию вывести в отсортированном по алфавиту виде.
 */

SELECT DISTINCT name_client
FROM stepik.client
         INNER JOIN stepik.buy USING (client_id)
         INNER JOIN stepik.buy_book USING (buy_id)
         INNER JOIN stepik.book USING (book_id)
         INNER JOIN stepik.author USING (author_id)
WHERE author.name_author LIKE 'Достоевский%'
ORDER BY 1;

/*
 Вывести жанр (или жанры), в котором было заказано больше всего экземпляров книг,
 указать это количество.
 Последний столбец назвать Количество.
 */

SELECT name_genre, SUM(buy_book.amount) AS Количество
FROM stepik.genre
         INNER JOIN stepik.book USING (genre_id)
         INNER JOIN stepik.buy_book USING (book_id)
GROUP BY name_genre
HAVING SUM(buy_book.amount) >= ALL (SELECT MAX(sum_amount)
                                    FROM (SELECT SUM(buy_book.amount) AS sum_amount
                                          FROM stepik.genre
                                                   INNER JOIN stepik.book USING (genre_id)
                                                   INNER JOIN stepik.buy_book USING (book_id)
                                          GROUP BY name_genre) as gbbbsa);

/**
  Сравнить ежемесячную выручку от продажи книг за текущий и предыдущий годы.
  Для этого вывести год, месяц, сумму выручки в отсортированном
  сначала по возрастанию месяцев, затем по возрастанию лет виде. Название столбцов: Год, Месяц, Сумма.
 */
SELECT date_part('year', date_step_end::date)  AS Год,
       date_part('month', date_step_end::date) AS Месяц,
       SUM(buy_book.amount * book.price)
FROM stepik.book
         INNER JOIN stepik.buy_book USING (book_id)
         INNER JOIN stepik.buy USING (buy_id)
         INNER JOIN stepik.buy_step USING (buy_id)
         INNER JOIN stepik.step USING (step_id)
WHERE name_step = 'Оплата'
  AND NOT date_step_end IS NULL
GROUP BY Год, Месяц;

/*
  SELECT YEAR(date_step_end) AS Год,
        MONTH(date_step_end) AS Месяц,
        SUM(buy_book.amount * book.price) AS Сумма
FROM book
    INNER JOIN buy_book USING(book_id)
    INNER JOIN buy USING(buy_id)
    INNER JOIN buy_step USING(buy_id)
    INNER JOIN step USING(step_id)
WHERE name_step = 'Оплата'
GROUP BY Год, Месяц;
 */

/*
 Для каждой отдельной книги необходимо вывести информацию
 о количестве проданных экземпляров и их стоимости за текущий и предыдущий год .
 Вычисляемые столбцы назвать Количество и Сумма. Информацию отсортировать по убыванию стоимости.
 */
SELECT title, SUM(buy_book.amount), SUM(buy_book.amount * book.price)
FROM stepik.book
         INNER JOIN stepik.buy_book USING (book_id)
         INNER JOIN stepik.buy USING (buy_id)
         INNER JOIN stepik.buy_step USING (buy_id)
         INNER JOIN stepik.step USING (step_id)
WHERE name_step = 'Оплата'
  AND NOT date_step_end IS NULL
GROUP BY book.title
ORDER BY 3;
