DROP TABLE IF EXISTS customer_details;

CREATE TABLE customer_details (
                                  id bigint NOT NULL,
                                  customer_id varchar(255) DEFAULT NULL,
                                  `name` varchar(255) DEFAULT NULL,
                                  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS product_details;

CREATE TABLE product_details (
                                 id bigint NOT NULL,
                                 `name` varchar(255) DEFAULT NULL,
                                 qty varchar(255) DEFAULT NULL,
                                 `type` varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS address;

CREATE TABLE address (
  id bigint NOT NULL,
  address1 varchar(255) DEFAULT NULL,
  address2 varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  pincode varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS address_customer;

CREATE TABLE address_customer (
  address_id bigint NOT NULL,
  customer_id bigint NOT NULL,
  KEY FK8o83bjin3lnqdq2v2y3hp7p2o (customer_id),
  KEY FKb416v23b89dom3odncx1xvd80 (address_id),
  CONSTRAINT FK8o83bjin3lnqdq2v2y3hp7p2o FOREIGN KEY (customer_id) REFERENCES customer_details (id),
  CONSTRAINT FKb416v23b89dom3odncx1xvd80 FOREIGN KEY (address_id) REFERENCES address (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS address_seq;

CREATE TABLE address_seq (
  next_val bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS customer_details_address;

CREATE TABLE customer_details_address (
  customer_id bigint NOT NULL,
  address_id bigint NOT NULL,
  KEY FKeof0rm52mf9kybbulufsj4c48 (address_id),
  KEY FKp60yxsrw3lqh5e3hy9tbaigju (customer_id),
  CONSTRAINT FKeof0rm52mf9kybbulufsj4c48 FOREIGN KEY (address_id) REFERENCES address (id),
  CONSTRAINT FKp60yxsrw3lqh5e3hy9tbaigju FOREIGN KEY (customer_id) REFERENCES customer_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS customer_details_seq;

CREATE TABLE customer_details_seq (
  next_val bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS item_details;

CREATE TABLE item_details (
  id varchar(255) NOT NULL,
  created_by varchar(255) DEFAULT NULL,
  created_on datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  price double NOT NULL,
  qty int NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS item_details_products;

CREATE TABLE item_details_products (
  item_id varchar(255) NOT NULL,
  products_id bigint NOT NULL,
  KEY FKnf7b0m3i2kko7ra8aei8qanly (products_id),
  KEY FKgmh97ic8ghewenhgvjqdci7bt (item_id),
  CONSTRAINT FKgmh97ic8ghewenhgvjqdci7bt FOREIGN KEY (item_id) REFERENCES item_details (id),
  CONSTRAINT FKnf7b0m3i2kko7ra8aei8qanly FOREIGN KEY (products_id) REFERENCES product_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS order_checkout;

CREATE TABLE order_checkout (
  id varchar(255) NOT NULL,
  created_by varchar(255) DEFAULT NULL,
  created_on datetime(6) DEFAULT NULL,
  total_amount double DEFAULT NULL,
  total_qty int NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS order_checkout_customer;

CREATE TABLE order_checkout_customer (
  check_out_id varchar(255) NOT NULL,
  customer_id bigint NOT NULL,
  KEY FKqr0l6yw6f6ldpwyw3pfsv42ks (customer_id),
  KEY FK4sy7untfvlxja22ng862boscb (check_out_id),
  CONSTRAINT FK4sy7untfvlxja22ng862boscb FOREIGN KEY (check_out_id) REFERENCES order_checkout (id),
  CONSTRAINT FKqr0l6yw6f6ldpwyw3pfsv42ks FOREIGN KEY (customer_id) REFERENCES customer_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS order_checkout_product;

CREATE TABLE order_checkout_product (
  check_out_id varchar(255) NOT NULL,
  product_id bigint NOT NULL,
  UNIQUE KEY UK_f3qqklgmp5lhw99rs2umavre1 (product_id),
  KEY FKb08lxf6nd1733sw9ttm5r53p2 (check_out_id),
  CONSTRAINT FKb08lxf6nd1733sw9ttm5r53p2 FOREIGN KEY (check_out_id) REFERENCES order_checkout (id),
  CONSTRAINT FKv2k73l1bamlrkbntos9twd84 FOREIGN KEY (product_id) REFERENCES product_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS product_details_items;

CREATE TABLE product_details_items (
  product_id bigint NOT NULL,
  items_id varchar(255) NOT NULL,
  UNIQUE KEY UK_n5vw8tsud6ne3ygrq3piohh3r (items_id),
  KEY FKm3l51a5budiyi0uaqp2do5otd (product_id),
  CONSTRAINT FK4fp2sx5gjnbhemcbaig5fum2u FOREIGN KEY (items_id) REFERENCES item_details (id),
  CONSTRAINT FKm3l51a5budiyi0uaqp2do5otd FOREIGN KEY (product_id) REFERENCES product_details (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS product_details_seq;

CREATE TABLE product_details_seq (
  next_val bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

