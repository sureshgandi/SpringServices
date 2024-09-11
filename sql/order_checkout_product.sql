DROP TABLE IF EXISTS order_checkout_product;

CREATE TABLE order_checkout_product (
  check_out_id varchar(255) NOT NULL,
  product_id bigint NOT NULL,
  UNIQUE KEY UK_f3qqklgmp5lhw99rs2umavre1 (product_id),
  KEY FKb08lxf6nd1733sw9ttm5r53p2 (check_out_id),
  CONSTRAINT FKb08lxf6nd1733sw9ttm5r53p2 FOREIGN KEY (check_out_id) REFERENCES order_checkout (id),
  CONSTRAINT FKv2k73l1bamlrkbntos9twd84 FOREIGN KEY (product_id) REFERENCES product_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
