CREATE TABLE product_carton
(
    product_carton_id varchar(36) NOT NULL,
    rare_product BOOLEAN DEFAULT FALSE NOT NULL,
    units_per_carton INTEGER NOT NULL,
    carton_price INTEGER NOT NULL,
    CONSTRAINT pkproduct_carton PRIMARY KEY (product_carton_id)
) ;

INSERT INTO product_carton(product_carton_id, rare_product, units_per_carton, carton_price) VALUES ('penguin-ears', TRUE, 20, 175);
INSERT INTO product_carton(product_carton_id, units_per_carton, carton_price) VALUES ('horseshoe', 5, 825);