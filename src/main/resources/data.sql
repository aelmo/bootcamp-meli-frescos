USE meli_fresh_products;

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

INSERT INTO section (id,warehouse_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);

INSERT INTO inbound_order (id, date, section_id, agent_id)
VALUES (1, "2021-07-01", 1, 2),
       (2, "2021-06-30", 2, 3),
       (3, "2021-06-29", 3, 1);

INSERT INTO product (id, name, seller_id)
VALUES (1, "Banana", 2),
       (2, "Apple", 3),
       (3, "Potato", 1);

INSERT INTO batch (id,current_temperature,minimum_temperature,initial_quantity,current_quantity,manufacturing_date,manufacturing_time,due_date,order_id, product_id)
VALUES (1,20.2,10.2,10,8,"2021-06-25","2021-06-25 16:00:00","2021-06-30",2, 1),
       (2,18.0,8.5,10,8,"2021-06-27","2021-06-27 1:00:00","2021-06-30",2, 1),
       (3,20.2,10.2,10,8,"2021-06-25","2021-06-25 16:00:00","2021-06-30",2, 1);