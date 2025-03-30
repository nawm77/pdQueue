CREATE TABLE file_metadata
(
    id            SERIAL PRIMARY KEY,
    file_name     VARCHAR(255) NOT NULL,
    file_path     VARCHAR(255),
    created_by_id BIGINT,
    upload_date   TIMESTAMP    NOT NULL,
    queue_id      BIGINT,
    FOREIGN KEY (created_by_id) REFERENCES users (id),
    FOREIGN KEY (queue_id) REFERENCES queues (id)
);