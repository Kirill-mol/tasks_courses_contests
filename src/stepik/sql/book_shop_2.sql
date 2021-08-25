/*
 Включить нового человека в таблицу с клиентами.
 Его имя Попов Илья, его email popov@test, проживает он в Москве.
 */
INSERT INTO stepik.client(name_client, city_id, email)
SELECT 'Попов Илья', city_id, 'popov@test'
FROM stepik.city
WHERE name_city = 'Москва';

/*
 Создать новый заказ для Попова Ильи.
 Его комментарий для заказа: «Связаться со мной по вопросу доставки».
Важно! В решении нельзя использоваться VALUES и делать отбор по client_id.
 */
INSERT INTO stepik.buy(buy_description, client_id)
SELECT 'Связаться со мной по вопросу доставки',
       ( SELECT client_id FROM stepik.client where name_client = 'Попов Илья');

/*
 В таблицу buy_book добавить заказ с номером 5. Этот заказ должен содержать книгу Пастернака
 «Лирика» в количестве двух экземпляров и книгу Булгакова «Белая гвардия» в одном экземпляре.
 */
INSERT INTO stepik.buy_book(buy_id, book_id, amount)
SELECT 5 AS buy_id, book_id, 2 AS amount
FROM stepik.author
    INNER JOIN stepik.book USING(author_id)
WHERE name_author LIKE 'Пастернак%' AND title = 'Лирика'
UNION ALL
SELECT 5, book_id, 1
FROM stepik.author
         INNER JOIN stepik.book USING(author_id)
WHERE name_author LIKE 'Булгаков%' AND title = 'Белая гвардия'

/*
 Уменьшить количество тех книг на складе, которые были включены в заказ с номером 5.
 */
SELECT * FROM stepik.book;

UPDATE stepik.book AS b
SET amount = b.amount - bb.amount
FROM stepik.buy_book AS bb
WHERE b.book_id = bb.book_id AND bb.buy_id = 5;

SELECT * FROM stepik.book;

/*
 Создать счет (таблицу buy_pay) на оплату заказа с номером 5,
 в который включить название книг, их автора, цену,
 количество заказанных книг и  стоимость. Последний столбец назвать Стоимость.
 Информацию в таблицу занести в отсортированном по названиям книг виде.
 */
CREATE TABLE stepik.buy_pay AS
SELECT title, name_author, price, buy_book.amount, buy_book.amount * price AS Стоимость
FROM stepik.author
    INNER JOIN stepik.book USING(author_id)
    INNER JOIN stepik.buy_book USING(book_id)
WHERE buy_id = 5
ORDER BY title;

/*
 Создать общий счет (таблицу buy_pay) на оплату заказа с номером 5.
 Куда включить номер заказа, количество книг в заказе (название столбца Количество)
 и его общую стоимость (название столбца Итого).  Для решения используйте ОДИН запрос.
 */
CREATE TABLE stepik.buy_pay AS
SELECT 5, SUM(amount), SUM(summ)
FROM (
         SELECT buy_book.amount, buy_book.amount * price AS summ
         FROM stepik.author
                  INNER JOIN stepik.book USING (author_id)
                  INNER JOIN stepik.buy_book USING (book_id)
         WHERE buy_id = 5
         ORDER BY title
     ) as "as"