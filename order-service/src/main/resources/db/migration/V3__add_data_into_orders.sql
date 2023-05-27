INSERT INTO orders (id, order_number)
VALUES (1, "111");
INSERT INTO orders (id, order_number)
VALUES (2, "222");

INSERT INTO order_items (id, sku_code, price, quantity, order_id)
VALUES (1, "123", 10.20, 2, 1);
INSERT INTO order_items (id, sku_code, price, quantity, order_id)
VALUES (2, "456", 0.50, 4, 1);
INSERT INTO order_items (id, sku_code, price, quantity, order_id)
VALUES (3, "789", 105.20, 5, 2);

