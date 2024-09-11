DROP TABLE IF EXISTS customer_details_seq;

CREATE TABLE customer_details_seq (
  next_val bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
