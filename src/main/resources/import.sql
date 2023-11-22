-- INSERISCO LE PIZZE
INSERT INTO db_pizzeria.pizzas (description, name, pic, price) VALUES('Tipica pizza Napoletana, la più popolare ed amata pizza Italiana dove il pomodoro si sposa con la mozzarella', 'Margherita', 'https://primochef.it/wp-content/uploads/2019/08/SH_pizza_fatta_in_casa-640x350.jpg.webp', 5.70);
INSERT INTO db_pizzeria.pizzas (description, name, pic, price) VALUES("Pizza composta da ingredienti caratterizzanti della propria stagione: i funghi per l'autunno, il prosciutto e le olive corrispondono all'inverno, i carciofi figurano la primavera ed infine i pomodori e il basilico l'estate.", 'Quattro Stagioni', 'https://www.petitchef.it/imgupl/recipe/pizza-4-stagioni--449891p695427.jpg',7.5);
INSERT INTO db_pizzeria.pizzas (description, name, pic, price) VALUES("I fiori di zucca sono un toccasana per la salute. Sono ortaggi diuretici e depurativi che si sposano perfettamente con la pizza", 'Fiori e Alici', 'https://www.ricettedalmondo.it/images/foto-ricette/p/31389-pizza-con-fiori-di-zucca-e-alici.jpg', 8.00);
INSERT INTO db_pizzeria.pizzas (description, name, pic, price) VALUES("Condita con una combinazione di quattro tipi di formaggio sciolti insieme, con salsa di pomodoro. Popolare in tutto il mondo è uno degli elementi iconici dei menu delle pizzerie.",'Quattro formaggi', 'https://i0.wp.com/www.piccolericette.net/piccolericette/wp-content/uploads/2017/06/3234_Pizza.jpg?resize=895%2C616&ssl=1', 8.00);

-- INSERISCO GLI INGREDIENTI
INSERT INTO ingredients(name) VALUES('basil');
INSERT INTO ingredients(name) VALUES('mozzarella cheese');
INSERT INTO ingredients(name) VALUES('tomato sauce');
INSERT INTO ingredients(name) VALUES('olive');
INSERT INTO ingredients(name) VALUES('anchovy');
INSERT INTO ingredients(name) VALUES('zucchini flower');
INSERT INTO ingredients(name) VALUES('raw ham');
INSERT INTO ingredients(name) VALUES('mushroom');
INSERT INTO ingredients(name) VALUES('artichoke');
INSERT INTO ingredients(name) VALUES('gorgonzola cheese');
INSERT INTO ingredients(name) VALUES('emmenthal chese');
INSERT INTO ingredients(name) VALUES('parmisan');

-- ASSEGNO GLI INGREDIENTI SULLE PIZZE

-- pizza margherita
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(1, 1)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(1, 2)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(1, 3)
-- pizza 4 stagioni
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(2, 2)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(2, 3)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(2, 4)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(2, 5)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(2, 7)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(2, 8)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(2, 9)
-- pizza fiori ed alici
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(3, 2)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(3, 5)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(3, 6)
-- pizza 4 formaggi
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(4, 2)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(4, 10)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(4, 11)
INSERT INTO pizzas_ingredients (pizzas_id, ingredients_id) VALUES(4, 12)

-- INSERISCO I RUOLI
INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

-- INSERISCO GLI UTENTI
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('john@email.com', 'John', 'Doe', '2023-11-20 10:35', '{noop}john');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('jane@email.com', 'Jane', 'Smith', '2023-11-20 10:35','{noop}jane');

-- ASSEGNO I RUOLI AGLI UTENTI
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);