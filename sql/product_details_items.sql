DROP TABLE IF EXISTS product_details_items;

CREATE TABLE product_details_items (
  product_id bigint NOT NULL,
  items_id varchar(255) NOT NULL,
  UNIQUE KEY UK_n5vw8tsud6ne3ygrq3piohh3r (items_id),
  KEY FKm3l51a5budiyi0uaqp2do5otd (product_id),
  CONSTRAINT FK4fp2sx5gjnbhemcbaig5fum2u FOREIGN KEY (items_id) REFERENCES item_details (id),
  CONSTRAINT FKm3l51a5budiyi0uaqp2do5otd FOREIGN KEY (product_id) REFERENCES product_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
