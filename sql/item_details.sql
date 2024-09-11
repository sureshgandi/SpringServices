DROP TABLE IF EXISTS item_details;

CREATE TABLE item_details (
  id varchar(255) NOT NULL,
  created_by varchar(255) DEFAULT NULL,
  created_on datetime(6) DEFAULT NULL,
  name varchar(255) NOT NULL,
  price double NOT NULL,
  qty int NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
