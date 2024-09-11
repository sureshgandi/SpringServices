DROP TABLE IF EXISTS product_details;

CREATE TABLE product_details (
  id bigint NOT NULL,
  name varchar(255) DEFAULT NULL,
  qty varchar(255) DEFAULT NULL,
  type varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
