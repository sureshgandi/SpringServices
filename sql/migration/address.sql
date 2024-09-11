
CREATE TABLE address (
                         id bigint NOT NULL,
                         address1 varchar(255) DEFAULT NULL,
                         address2 varchar(255) DEFAULT NULL,
                         phone varchar(255) DEFAULT NULL,
                         pincode varchar(255) DEFAULT NULL,
                         PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
