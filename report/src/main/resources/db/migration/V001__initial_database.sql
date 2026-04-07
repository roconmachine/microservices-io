CREATE TABLE IF NOT EXISTS BASE_TABLE
(
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS REPORTS
(
    id SERIAL PRIMARY KEY,
    title character varying(255) not null,
    description text,
    priority character varying(50) not null,
    tags character varying(500),
    reporter character varying(255)
)INHERITS(BASE_TABLE);


CREATE TABLE IF NOT EXISTS LOCATIONS
(
    id SERIAL PRIMARY KEY,
    report_id integer,
    name character varying(255),
    address character varying(255),
    lat NUMERIC,
    lng NUMERIC,
    reference character varying(500)
)INHERITS(BASE_TABLE);



CREATE TABLE IF NOT EXISTS PROPOSALS
(
    id SERIAL PRIMARY KEY,
    report_id integer not null,
    user_id integer not null,
    state_id integer not null,
    action_name character varying(255),
    amount NUMERIC
)INHERITS(BASE_TABLE);


CREATE TABLE IF NOT EXISTS COMMENTS
(
    id SERIAL PRIMARY KEY,
    report_id integer not null,
    user_id integer not null,
    comment text,
    parent_comment_id integer,
    rank integer
)INHERITS(BASE_TABLE);



CREATE TABLE IF NOT EXISTS RESOURCES
(
    id SERIAL PRIMARY KEY,
    domain_type character varying(50),
    domain_id integer,
    path character varying(500)
)INHERITS(BASE_TABLE);

CREATE TABLE IF NOT EXISTS Assainments
(
    id SERIAL PRIMARY KEY,
    report_id integer,
    assign_by integer,
    assign_to integer,
    proposal_id integer
)INHERITS(BASE_TABLE);