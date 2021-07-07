USE meli_fresh_products;

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
       (1, 3),
       (1, 1);

INSERT INTO inbound_order (date, section_id, agent_id)
VALUES ("2021-07-01", 1, 2),
       ("2021-06-30", 2, 3),
       ("2021-06-29", 3, 1);

INSERT INTO product (name, seller_id, category_id, amount)
VALUES ("Banana", 2, 1, 10),
       ("Apple", 3, 2, 10),
       ("Potato", 1, 3, 10);

INSERT INTO purchase_statuses (title)
VALUES ('Paid');

INSERT INTO purchase_statuses (title)
VALUES ('In Process');

INSERT INTO purchase_statuses (title)
VALUES ('Refused');


INSERT INTO batch (current_temperature,minimum_temperature,initial_quantity,current_quantity, last_quantity, manufacturing_date,manufacturing_time,due_date,order_id, product_id)
VALUES (18.0,8.5,10,8,8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",2, 2),
       (18.0,8.5,10,18,18,"2021-06-27","2021-06-27 1:00:00","2021-06-30",2, 2),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-06-30",2, 2);

INSERT INTO batch (current_temperature,minimum_temperature,initial_quantity,current_quantity,last_quantity, manufacturing_date,manufacturing_time,due_date,order_id, product_id)
VALUES (18.0,8.5,10,8,8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",2, 2),
       (18.0,8.5,10,18,18,"2021-06-27","2021-06-27 1:00:00","2021-06-30",2, 2),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-06-30",2, 2);

INSERT INTO batch (current_temperature,minimum_temperature,initial_quantity,current_quantity,last_quantity, manufacturing_date,manufacturing_time,due_date,order_id, product_id)
VALUES (18.0,8.5,10,8,8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",2, 3),
       (18.0,8.5,10,18,18,"2021-06-27","2021-06-27 1:00:00","2021-06-30",2, 2),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-06-30",2, 1);

INSERT INTO batch (current_temperature,minimum_temperature,initial_quantity,current_quantity,last_quantity, manufacturing_date,manufacturing_time,due_date,order_id, product_id)
VALUES (18.0,8.5,10,8, 8,"2021-06-27","2021-06-27 5:00:00","2021-06-30",2, 3),
       (18.0,8.5,10,18, 18,"2021-06-27","2021-06-27 1:00:00","2021-06-30",2, 3),
       (20.2,10.2,10,28,28,"2021-06-25","2021-06-25 16:00:00","2021-06-30",2, 3);

