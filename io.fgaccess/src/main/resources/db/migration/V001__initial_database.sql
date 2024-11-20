CREATE TABLE IF NOT EXISTS BASE_TABLE
(
    version integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ROLES
(
    role_id SERIAL PRIMARY KEY,
    name character varying(50) NOT NULL UNIQUE

) INHERITS(BASE_TABLE);

CREATE TABLE IF NOT EXISTS ACTIONS
(
    action_id SERIAL PRIMARY KEY,
    name character varying(50) NOT NULL UNIQUE,
    action_type character varying(50) NOT NULL
)INHERITS(BASE_TABLE);


CREATE TABLE IF NOT EXISTS POLICY
(
    policy_id SERIAL PRIMARY KEY,
    subject_id character varying(50) NOT NULL,
    action_name character varying(50),
    object_id character varying(50),
    status boolean default true

)INHERITS(BASE_TABLE);


CREATE TABLE IF NOT EXISTS UserRole
(
    user_role_id SERIAL PRIMARY KEY,
    user_id character varying(50) NOT NULL,
    role_id INTEGER NOT NULL

)INHERITS(BASE_TABLE);


CREATE TABLE IF NOT EXISTS Subject
(
    subject_id SERIAL PRIMARY KEY,
    user_id character varying(50) NOT NULL,
    status boolean DEFAULT true

)INHERITS(BASE_TABLE);