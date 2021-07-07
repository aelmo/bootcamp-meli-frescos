USE meli_fresh_products;

INSERT INTO role (name)
VALUES ("ROLE_ADMIN"),
       ("ROLE_AGENT"),
       ("ROLE_SELLER");

INSERT INTO user (user_name, email, password)
VALUES ("adminuser", "teste@teste.com", "$2y$12$/Zg./fB6DDZfmXfNaGrkMuCTi6N7AOglucNOqUIj0Xf2L.8i0g7Qa"),
       ("agentuser", "teste2@teste.com", "$2y$12$/Zg./fB6DDZfmXfNaGrkMuCTi6N7AOglucNOqUIj0Xf2L.8i0g7Qa"),
       ("selleruser", "teste3@teste.com", "$2y$12$/Zg./fB6DDZfmXfNaGrkMuCTi6N7AOglucNOqUIj0Xf2L.8i0g7Qa");

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1), -- User 1 has Admin Access
       (2, 2), -- User 2 has Agent Access
       (3, 1); -- User 3 has Seller Access

INSERT INTO seller (id, name)
VALUES (1,"Joao"),
       (2,"Maria"),
       (3,"Carlos");

INSERT INTO warehouse (id, name)
VALUES (1,"Cajamar Fullfilment"),
       (2,"Salvador Fullfilment"),
       (3,"Florianopolis Fullfilment");

INSERT INTO agent (id,name)
VALUES (1, "Amanda Rodrigues"),
       (2, "Daniela Santos"),
       (3, "Carla Flores");

INSERT INTO category (id, code, description)
VALUES (1, "FS", "Fresh"),
       (2, "RF", "Refrigerated"),
       (3, "FZ", "Frozen");

INSERT INTO section (id,warehouse_id, category_id)
VALUES (1, 1, 2),
       (2, 1, 3),
       (3, 1, 1);

INSERT INTO inbound_order (id, date, section_id, agent_id)
VALUES (1, "2021-07-01", 1, 2),
       (2, "2021-06-30", 2, 3),
       (3, "2021-06-29", 3, 1);

INSERT INTO product (id, name, seller_id, category_id)
VALUES (1, "Banana", 2, 1),
       (2, "Apple", 3, 2),
       (3, "Potato", 1, 3);

INSERT INTO batch (id,current_temperature,minimum_temperature,initial_quantity,current_quantity,manufacturing_date,manufacturing_time,due_date,order_id, product_id)
VALUES (1,18.0,8.5,10,8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",2, 1),
       (2,18.0,8.5,10,8,"2021-06-27","2021-06-27 1:00:00","2021-06-30",2, 1),
       (3,20.2,10.2,10,8,"2021-06-25","2021-06-25 16:00:00","2021-06-30",2, 1);


