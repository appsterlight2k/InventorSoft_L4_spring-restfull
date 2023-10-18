
DROP SCHEMA IF EXISTS store;
CREATE SCHEMA store;

use store;

CREATE TABLE items(
	id INT UNIQUE NOT NULL , #AUTO_INCREMENT
	name VARCHAR(500) NOT NULL,
    count INT NOT NULL,
    price DOUBLE NOT NULL,
    article_num VARCHAR(100) NOT NULL,
    description VARCHAR(5000) NOT NULL
);

CREATE TABLE orders(
	id INT UNIQUE NOT NULL AUTO_INCREMENT,
    item_id INT NOT NULL,
    customer_id INT,
	customer_firstname VARCHAR(500) NOT NULL,
	customer_lastname VARCHAR(500) NOT NULL,
	phone VARCHAR(100) NOT NULL,
	is_paid BOOLEAN DEFAULT FALSE
);

CREATE TABLE customers(
	id INT UNIQUE NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(500) NOT NULL,
    lastname VARCHAR(500) NOT NULL,
    email VARCHAR(300) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    password VARCHAR(300) NOT NULL,
    comments VARCHAR(5000)
);

INSERT INTO items(id, name, count, price, article_num, description)
VALUES
	(1, 'Colgate Medium Toothbrash', 300, 12.99, 'LT-1501-73', 'Colgate Medium Toothbrash, color white'),
    (2, 'Sword of the Jedi, blue ray', 5, 1500000.0, 'SJ-01B', 'Jedi laser sword, ray color: blue'),
    (3, 'Sword of the Jedi, red ray', 5, 1490000.0, 'SJ-01R', 'Jedi laser sword, ray color: red'),
    (4, 'Lenovo Legion 5 15ACH6H Dark Blue', 12, 2500.99, '82JU01C3RA',  'Lenovo Legion Gaming Series, Generation 5, CPU: Core i9-13430 3.2Ghz, Matrix: IPS 1920x1080, Video: 16Gb NVIDIA GeForce RTX 3070, RAM: 8GB, SSD: 1Gb');


INSERT INTO orders(item_id, customer_id, customer_firstname, customer_lastname, phone, is_paid)
VALUES
	(1, 3, '', '', '', true),
    (3, NULL, 'Din', 'Djarin the Mandalorian', '0-000-000-000', false),
    (4, 1, '', '', '', true),
    (2, 2, '', '', '', false);

INSERT INTO customers(firstname, lastname, email, phone, password, comments)
VALUES
	('John', 'Rembo', 'john_rembo@gmail.com', '+1-534-215-94-16', 'One blood', 'Very shy guy'),
    ('Sarah', 'Connor', 'sarrah_o_connor@yahoo.com', '+1-243-452-34-34', 'SkynetIsComing#@553', 'Calm person'),
    ('Obi-Wan', 'Kenobi', 'obi.one@mail.com', '+1-224-235-46-12', 'LittleYoda123', 'Star Wars fan');

