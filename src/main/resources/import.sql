INSERT INTO "user" (login) values ('test@test.test');
INSERT INTO book  (name) VALUES ( 'ЛЯГУШКИ И ЕЕ ПОДВИДЫ');
INSERT INTO "digital_book" (download_link, price, book_id) VALUES ('https://drive.google.com/uc?export=download&id=1XNUWq09UMsUJEpu0zhpWiVqEz2yuFevO',666.66,1);
INSERT INTO location (latitude, longitude) VALUES (59.95718399052891, 30.308317588334006);
INSERT INTO stock (location_id) VALUES (1);
INSERT INTO physical_book (height, length, price, weight, width, book_id) VALUES (100,100,228,300,40,1);
INSERT INTO item (available, physical_book_id, stock_id) VALUES (true,1,1)
