DROP TABLE IF EXISTS customer_details_address;

CREATE TABLE customer_details_address (
  customer_id bigint NOT NULL,
  address_id bigint NOT NULL,
  KEY FKeof0rm52mf9kybbulufsj4c48 (address_id),
  KEY FKp60yxsrw3lqh5e3hy9tbaigju (customer_id),
  CONSTRAINT FKeof0rm52mf9kybbulufsj4c48 FOREIGN KEY (address_id) REFERENCES address (id),
  CONSTRAINT FKp60yxsrw3lqh5e3hy9tbaigju FOREIGN KEY (customer_id) REFERENCES customer_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
