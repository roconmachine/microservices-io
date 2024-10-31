CREATE TABLE IF NOT EXISTS PolicyMapping
(
    id serial PRIMARY KEY,
    policy_id integer,
    subject_type character varying (50)NOT NULL,
    subject_id integer,
    resource_id integer,
    action character varying(50) NOT NULL,
    record_status character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE OR REPLACE TRIGGER update_table_changetimestamp
    BEFORE UPDATE
    ON PolicyMapping
    FOR EACH ROW
    EXECUTE FUNCTION update_changetimestamp_column();