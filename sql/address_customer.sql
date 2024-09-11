CREATE TABLE address_customer (
                                  address_id bigint NOT NULL,
                                  customer_id bigint NOT NULL,
                                  KEY FK8o83bjin3lnqdq2v2y3hp7p2o (customer_id),
                                  KEY FKb416v23b89dom3odncx1xvd80 (address_id),
                                  CONSTRAINT FK8o83bjin3lnqdq2v2y3hp7p2o FOREIGN KEY (customer_id) REFERENCES customer_details (id),
                                  CONSTRAINT FKb416v23b89dom3odncx1xvd80 FOREIGN KEY (address_id) REFERENCES address (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
