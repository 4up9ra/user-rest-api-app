CREATE TABLE IF NOT EXISTS users
(
    user_id     BIGSERIAL PRIMARY KEY,
    user_name   VARCHAR    NOT NULL,
    first_name  VARCHAR    NOT NULL,
    second_name VARCHAR    NOT NULL,
    age         INTEGER    NOT NULL,
    gender      VARCHAR(1) NOT NULL,
    created_at  TIMESTAMP  NOT NULL
);