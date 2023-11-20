INSERT INTO db_pizzeria.pizzas (description, name, pic, price) VALUES('mozzarella, pomodoro', 'Margherita', 'https://primochef.it/wp-content/uploads/2019/08/SH_pizza_fatta_in_casa-640x350.jpg.webp', 5.70);
INSERT INTO db_pizzeria.pizzas (description, name, pic, price) VALUES('prosciutto cruto, olive nere, carciofini sotto olio, funghi champignon', 'Quattro Stagioni', 'https://www.petitchef.it/imgupl/recipe/pizza-4-stagioni--449891p695427.jpg',7.5);
INSERT INTO db_pizzeria.pizzas (description, name, pic, price) VALUES('fiori di zucca, alici, mozzarella', 'Fiori e Alici', 'https://www.ricettedalmondo.it/images/foto-ricette/p/31389-pizza-con-fiori-di-zucca-e-alici.jpg', 8.00);

INSERT INTO ingredients(name) VALUES('basil');
INSERT INTO ingredients(name) VALUES('mozzarella cheese');
INSERT INTO ingredients(name) VALUES('tomato sauce');
INSERT INTO ingredients(name) VALUES('olive');

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('john@email.com', 'John', 'Doe', '2023-11-20 10:35', '{noop}john');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('jane@email.com', 'Jane', 'Smith', '2023-11-20 10:35','{noop}jane');

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);