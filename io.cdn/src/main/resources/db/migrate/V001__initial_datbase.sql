CREATE TABLE IF NOT EXISTS BASE_TABLE
(
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS PROPERTIES
(
    id SERIAL PRIMARY KEY,
    report_file_id integer not null,
    key character varying(50) not null,
    value character varying(50) not null

)INHERITS(BASE_TABLE);


CREATE TABLE IF NOT EXISTS Report_File
(
    id SERIAL PRIMARY KEY,
    checksum character varying(255) NOT NULL,
    source character varying(255) not null

)INHERITS(BASE_TABLE);