CREATE TABLE comments (
    id BIGINT PRIMARY KEY,
    nickname VARCHAR NOT NULL,
    content varchar NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    post_id BIGINT
);
CREATE TABLE available_posts (
    id BIGINT PRIMARY KEY,
    title VARCHAR NOT NULL
);