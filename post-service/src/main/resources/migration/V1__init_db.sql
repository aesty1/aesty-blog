CREATE TABLE posts (
                          id BIGINT PRIMARY KEY,
                          title VARCHAR NOT NULL,
                          content TEXT NOT NULL,
                          tags VARCHAR[],
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          author_id BIGINT,
                          CONSTRAINT fk_post_author
                               FOREIGN KEY (author_id)
                               REFERENCES available_authors(id)
);

CREATE TABLE available_authors (
                                 id BIGINT PRIMARY KEY,
                                 nickname VARCHAR NOT NULL
);