USE meli_fresh_products;

INSERT INTO role (name)
VALUES ("ROLE_ADMIN"),
       ("ROLE_AGENT"),
       ("ROLE_BUYER");

INSERT INTO user (user_name, email, password)
VALUES ("adminuser", "teste@teste.com", "$2y$12$/Zg./fB6DDZfmXfNaGrkMuCTi6N7AOglucNOqUIj0Xf2L.8i0g7Qa"),
       ("agentuser", "teste2@teste.com", "$2y$12$/Zg./fB6DDZfmXfNaGrkMuCTi6N7AOglucNOqUIj0Xf2L.8i0g7Qa"),
       ("buyeruser", "teste3@teste.com", "$2y$12$/Zg./fB6DDZfmXfNaGrkMuCTi6N7AOglucNOqUIj0Xf2L.8i0g7Qa");

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1), -- User 1 has Admin Access
       (2, 2), -- User 2 has Agent Access
       (3, 3); -- User 3 has Buyer Access

INSERT INTO seller (name)
VALUES ("Joao"),
       ("Maria"),
       ("Carlos");

INSERT INTO buyer (name)
VALUES ("Joao"),
       ("Maria"),
       ("Carlos");

INSERT INTO warehouse (name)
VALUES ("Cajamar Fullfilment"),
       ("Salvador Fullfilment"),
       ("Florianopolis Fullfilment");

INSERT INTO agent (name)
VALUES ("Amanda Rodrigues"),
       ("Daniela Santos"),
       ("Carla Flores");

INSERT INTO category (code, description)
VALUES ("FS", "Fresh"),
       ("RF", "Refrigerated"),
       ("FZ", "Frozen");

INSERT INTO section (warehouse_id, category_id)
VALUES (1, 2),
       (2, 3),
       (3, 1);

INSERT INTO inbound_order (date, section_id, agent_id)
VALUES ("2021-07-01", 1, 2),
       ("2021-06-30", 2, 3),
       ("2021-06-29", 3, 1);

INSERT INTO product (name, seller_id, category_id, amount)
VALUES ("Banana", 2, 1, 10),
       ("Apple", 3, 2, 10),
       ("Potato", 1, 3, 10),
       ("Strawberry",1,2,10);

INSERT INTO purchase_statuses (title)
VALUES ('Paid');

INSERT INTO purchase_statuses (title)
VALUES ('In Process');

INSERT INTO purchase_statuses (title)
VALUES ('Refused');


INSERT INTO batch (current_temperature,minimum_temperature,initial_quantity,current_quantity, last_quantity, manufacturing_date,manufacturing_time,due_date,order_id, product_id)
VALUES (18.0,8.5,10,8,8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",2, 2),
       (18.0,8.5,10,18,18,"2021-06-27","2021-06-27 1:00:00","2021-07-28",1, 2),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-09-27",3, 2),
       (18.0,8.5,10,8,8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",1, 2),
       (18.0,8.5,10,25,18,"2021-06-27","2021-06-27 1:00:00","2021-07-25",2, 2),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-10-25",1, 2),
       (18.0,8.5,10,8,8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",3, 3),
       (18.0,8.5,10,25,18,"2021-06-27","2021-06-27 1:00:00","2021-07-25",2, 3),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-11-25",1, 3),
       (18.0,8.5,10,8,8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",1, 2),
       (18.0,8.5,10,25,18,"2021-06-27","2021-06-27 1:00:00","2021-07-25",1, 2),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-08-10",1, 2),
       (18.0,8.5,10,8,8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",2, 3),
       (18.0,8.5,10,18,18,"2021-06-27","2021-06-27 1:00:00","2021-08-09",2, 2),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-08-30",2, 1),
       (18.0,8.5,10,8, 8,"2021-06-27","2021-06-27 5:00:00","2021-08-10",2, 3),
       (18.0,8.5,10,18, 18,"2021-06-27","2021-06-27 1:00:00","2021-08-30",2, 3),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-09-02",2, 3),
       (18.0,8.5,10,8, 8,"2021-06-27","2021-06-27 5:00:00","2021-08-10",3, 1),
       (18.0,8.5,10,18, 18,"2021-06-27","2021-06-27 1:00:00","2021-08-30",2, 1),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-09-02",1, 1),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-01-02",1, 4);
