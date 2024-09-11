DROP TABLE IF EXISTS order_checkout_customer;

CREATE TABLE order_checkout_customer (
  check_out_id varchar(255) NOT NULL,
  customer_id bigint NOT NULL,
  KEY FKqr0l6yw6f6ldpwyw3pfsv42ks (customer_id),
  KEY FK4sy7untfvlxja22ng862boscb (check_out_id),
  CONSTRAINT FK4sy7untfvlxja22ng862boscb FOREIGN KEY (check_out_id) REFERENCES order_checkout (id),
  CONSTRAINT FKqr0l6yw6f6ldpwyw3pfsv42ks FOREIGN KEY (customer_id) REFERENCES customer_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
