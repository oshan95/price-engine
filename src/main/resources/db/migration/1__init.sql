CREATE TABLE product
(
    product_id INTEGER NOT NULL,
    product_name VARCHAR(36) NOT NULL,
    rare_product BOOLEAN DEFAULT FALSE NOT NULL,
    units_per_carton INTEGER NOT NULL,
    carton_price INTEGER NOT NULL,
    CONSTRAINT pkproduct PRIMARY KEY (product_id)
) ;

INSERT INTO product(product_id, product_name, rare_product, units_per_carton, carton_price) VALUES (1, 'penguin-ears', TRUE, 20, 175);
INSERT INTO product(product_id, product_name, units_per_carton, carton_price) VALUES (2, 'horseshoe', 5, 825);