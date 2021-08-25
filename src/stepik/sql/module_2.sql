CREATE TABLE book
(
    book_id   INT PRIMARY KEY AUTO_INCREMENT,
    title     VARCHAR(50),
    author_id INT NOT NULL,
    genre_id  INT,
    price     DECIMAL(8, 2),
    amount    INT,
    FOREIGN KEY (author_id) REFERENCES author (author_id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre (genre_id) ON DELETE SET NULL
);

/*
 Вывести все жанры, которые не представлены в книгах на складе.
 */
SELECT name_genre
FROM genre
         LEFT JOIN book
                   ON genre.genre_id = book.genre_id
WHERE title IS NULL;

/*
 Посчитать количество экземпляров  книг каждого автора из таблицы author.
 Вывести тех авторов,  количество книг которых меньше 10,
 в отсортированном по возрастанию количества виде. Последний столбец назвать Количество.
 */
SELECT name_author, SUM(amount) AS Количество
FROM author
         LEFT JOIN book
                   on author.author_id = book.author_id
GROUP BY name_author
HAVING Количество < 10
    OR Количество IS NULL
ORDER BY 2;


/*
 Вывести в алфавитном порядке всех авторов, которые пишут только в одном жанре. П
 оскольку у нас в таблицах так занесены данные, что у каждого автора книги только в одном жанре,
 для этого запроса внесем изменения в таблицу book
 */
SELECT name_author
FROM author
         INNER JOIN book ON book.author_id = author.author_id
         INNER JOIN genre ON genre.genre_id = book.genre_id
GROUP BY name_author
HAVING COUNT(DISTINCT name_genre) = 1;

/*
 Вывести информацию о книгах (название книги, фамилию и инициалы автора,
 название жанра, цену и количество экземпляров книги),
 написанных в самых популярных жанрах, в отсортированном в алфавитном
 порядке по названию книг виде.
 Самым популярным считать жанр, общее количество
 экземпляров книг которого на складе максимально.
 */
SELECT title, name_author, name_genre, price, amount
FROM author
         INNER JOIN book ON book.author_id = author.author_id
         INNER JOIN genre ON genre.genre_id = book.genre_id
WHERE book.genre_id IN (
    SELECT q_1.genre_id
    FROM (
             SELECT genre_id, SUM(amount) AS s_m
             FROM book
             GROUP BY genre_id
         ) q_1
             INNER JOIN
         (
             SELECT genre_id, SUM(amount) AS s_m
             FROM book
             GROUP BY genre_id
             ORDER BY s_m DESC
             LIMIT 1
         ) q_2
         ON q_1.s_m = q_2.s_m
)
ORDER BY 1;

/*
 Если в таблицах supply  и book есть одинаковые книги, которые имеют равную цену,
 вывести их название и автора, а также посчитать общее количество
 экземпляров книг в таблицах supply и book,  столбцы назвать Название, Автор  и Количество.
 */
SELECT supply.title AS Название, supply.author AS Автор, (supply.amount + book.amount) AS Количество
FROM
    author
        INNER JOIN book USING(author_id)
        INNER JOIN supply ON
                supply.author = author.name_author AND
                supply.title = book.title AND
                supply.price = book.price;

/*
Для книг, которые уже есть на складе (в таблице book), но по другой цене, чем в поставке (supply),
необходимо в таблице book увеличить количество на значение, указанное в поставке,  и пересчитать цену.
А в таблице  supply обнулить количество этих книг. Формула для пересчета цены
 */
UPDATE book
    INNER JOIN author ON author.author_id = book.author_id
    INNER JOIN supply ON book.title = supply.title
    AND supply.author = author.name_author
SET book.price = (book.price * book.amount + supply.price * supply.amount)
    / (book.amount + supply.amount),
    book.amount = book.amount + supply.amount,
    supply.amount = 0
WHERE book.price != supply.price;

/*
 Включить новых авторов в таблицу author с помощью
 запроса на добавление, а затем вывести все данные из таблицы author.
 Новыми считаются авторы, которые есть в таблице supply, но нет в таблице author.
 */
INSERT INTO author(name_author)
    (SELECT supply.author
     FROM supply
              LEFT JOIN author ON supply.author = author.name_author
     WHERE author.name_author IS NULL);


/*
 Добавить новые книги из
 таблицы supply в таблицу book на основе сформированного выше запроса.
 Затем вывести для просмотра таблицу book.
 */
INSERT INTO book(title, author_id, price, amount)
    (SELECT title, author_id, price, amount
     FROM supply
              LEFT JOIN author ON supply.author = author.name_author
     WHERE amount != 0);


/*
 Удалить всех авторов и все их книги, общее количество книг которых меньше 20.
 Нельзя удалять если в IN совершается подзапрос к таблице author
 */
DELETE FROM author
WHERE author.author_id IN
      (
          SELECT book.author_id
          FROM book
          GROUP BY book.author_id
          HAVING SUM(amount) < 20
      );

/*
Удалить всех авторов, которые пишут в жанре "Поэзия". Из таблицы book удалить все книги этих авторов.
В запросе для отбора авторов использовать полное название жанра, а не его id.
 */

DELETE FROM author
    USING author
        INNER JOIN book ON author.author_id = book.author_id
        INNER JOIN genre ON genre.genre_id = book.genre_id
WHERE name_genre = 'Поэзия';