CREATE TABLE IF NOT EXISTS EMAIL_NOTIFICATIONS
(
    id SERIAL PRIMARY KEY,
    template_name character varying(50),
    subject_placeholder character varying(500),
    body_placeholder TEXT,
    recipients character varying(500),
    status character varying(50),
    severity character varying(100),
    createdDate timestamp(6) without time zone NOT NULL
);

CREATE TABLE IF NOT EXISTS RECIPIENTS
(
    id SERIAL PRIMARY KEY,
    name character varying(50) NOT NULL,
    email character varying(50),
    phone character varying(20),
    device_id character varying(100)
);

CREATE TABLE IF NOT EXISTS NOTIFICATION_TEMPLATES
(
    id SERIAL PRIMARY KEY,
    name character varying(50) NOT NULL UNIQUE,
    subject_template character varying(500),
    body_template text
);
