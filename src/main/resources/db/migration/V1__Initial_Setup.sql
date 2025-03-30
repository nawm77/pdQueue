CREATE TABLE groups
(
    id         BIGSERIAL PRIMARY KEY,
    group_name VARCHAR(255) NOT NULL
);

CREATE TABLE users
(
    id        BIGSERIAL PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    group_id  BIGINT,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE SET NULL
);

CREATE TABLE permissions
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE roles_permissions
(
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions (id) ON DELETE CASCADE
);

CREATE TABLE queues
(
    id              BIGSERIAL PRIMARY KEY,
    subject_name    VARCHAR(255) NOT NULL,
    submission_date TIMESTAMP    NOT NULL,
    created_by      BIGINT,
    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL
);

CREATE TABLE queue_students
(
    queue_id   BIGINT,
    student_id BIGINT,
    PRIMARY KEY (queue_id, student_id),
    FOREIGN KEY (queue_id) REFERENCES queues (id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE queue_moderators
(
    queue_id     BIGINT,
    moderator_id BIGINT,
    PRIMARY KEY (queue_id, moderator_id),
    FOREIGN KEY (queue_id) REFERENCES queues (id) ON DELETE CASCADE,
    FOREIGN KEY (moderator_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE grades
(
    id         BIGSERIAL PRIMARY KEY,
    grade      DOUBLE PRECISION NOT NULL,
    student_id BIGINT,
    queue_id   BIGINT,
    FOREIGN KEY (student_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (queue_id) REFERENCES queues (id) ON DELETE CASCADE
);

CREATE TABLE user_roles
(
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);