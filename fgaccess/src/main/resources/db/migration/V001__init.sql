--CREATE TYPE operator_type AS ENUM('EQUALS', 'NOT_EQUALS', 'GREATER_THAN', 'LESS_THAN', 'IN', 'NOT_IN', 'BETWEEN', 'CONTAINS');
--CREATE TYPE record_status_enum AS ENUM('INSERTED', 'DISABLED', 'DELETED');
--CREATE TYPE effect_type AS ENUM('ALLOW', 'DENY');
--CREATE TYPE target_type AS ENUM('TIME', 'USER_ATTRIBUTE', 'RESOURCE_ATTRIBUTE', 'ENVIRONMENT', 'CUSTOM');

CREATE OR REPLACE FUNCTION public.update_changetimestamp_column()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
   NEW.updatedAt = now();
   RETURN NEW;
END;
$BODY$;


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
CREATE OR REPLACE TRIGGER update_table_changetimestamp
    BEFORE UPDATE
    ON PROPERTIES
    FOR EACH ROW
    EXECUTE FUNCTION update_changetimestamp_column();

CREATE TABLE IF NOT EXISTS RESOURCE
(
    id SERIAL PRIMARY KEY,
    type character varying(50) COLLATE pg_catalog."default" NOT NULL,
    source character varying(255) COLLATE pg_catalog."default",
    owner character varying(255) COLLATE pg_catalog."default",
    record_status character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE OR REPLACE TRIGGER update_resource_changetimestamp
    BEFORE UPDATE
    ON RESOURCE
    FOR EACH ROW
    EXECUTE FUNCTION update_changetimestamp_column();




CREATE TABLE IF NOT EXISTS POLICY
(
    id SERIAL PRIMARY KEY,
    name character varying(50) NOT NULL,
    effect character varying(50) NOT NULL,
    record_status character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE TRIGGER update_policy_changetimestamp
    BEFORE UPDATE
    ON POLICY
    FOR EACH ROW
    EXECUTE FUNCTION update_changetimestamp_column();


CREATE TABLE IF NOT EXISTS CONDITIONS
(
    id SERIAL PRIMARY KEY,
    target character varying(50) NOT NULL,
    operator character varying(50) NOT NULL,
    record_status character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    policy_id integer,
    CONSTRAINT fk_policy_condition FOREIGN KEY (policy_id)
        REFERENCES policy (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE OR REPLACE TRIGGER update_conditions_changetimestamp
    BEFORE UPDATE
    ON CONDITIONS
    FOR EACH ROW
    EXECUTE FUNCTION update_changetimestamp_column();