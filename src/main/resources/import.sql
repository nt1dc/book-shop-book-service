INSERT INTO "user" (email) values ('test@test.test');
INSERT INTO book  (height, length, name, price, weight, width) VALUES (40, 100, 'ЛЯГУШКИ И ЕЕ ПОДВИДЫ', 350, 500, 60);
INSERT INTO location (latitude, longitude) VALUES (59.95718399052891, 30.308317588334006);
INSERT INTO stock (location_id) VALUES (1);
INSERT INTO "item" (available, book_id, order_id, stock_id) VALUES (true,1,null,1);