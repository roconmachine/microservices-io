CREATE TABLE IF NOT EXISTS GROUPS
(
    id SERIAL PRIMARY KEY,
    name character varying(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS GROUP_RECIPIENTS
(
    id SERIAL PRIMARY KEY,
    group_name character varying(50) NOT NULL,
    recipient_id bigint
);