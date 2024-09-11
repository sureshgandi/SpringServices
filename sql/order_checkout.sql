DROP TABLE IF EXISTS order_checkout;

CREATE TABLE order_checkout (
  id varchar(255) NOT NULL,
  created_by varchar(255) DEFAULT NULL,
  created_on datetime(6) DEFAULT NULL,
  total_amount double DEFAULT NULL,
  total_qty int NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
