CREATE TABLE IF NOT EXISTS PROPERTIES
(
    id serial PRIMARY KEY,
    uuid character varying(50)  NOT NULL,
    key character varying(255)  NOT NULL,
    value_string character varying(255),
    value_number double precision,
    value_boolean boolean,
	record_status character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);