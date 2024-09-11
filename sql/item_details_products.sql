DROP TABLE IF EXISTS item_details_products;

CREATE TABLE item_details_products (
  item_id varchar(255) NOT NULL,
  products_id bigint NOT NULL,
  KEY FKnf7b0m3i2kko7ra8aei8qanly (products_id),
  KEY FKgmh97ic8ghewenhgvjqdci7bt (item_id),
  CONSTRAINT FKgmh97ic8ghewenhgvjqdci7bt FOREIGN KEY (item_id) REFERENCES item_details (id),
  CONSTRAINT FKnf7b0m3i2kko7ra8aei8qanly FOREIGN KEY (products_id) REFERENCES product_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
