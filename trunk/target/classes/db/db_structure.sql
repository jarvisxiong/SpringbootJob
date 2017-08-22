-- Table: public.public_user

-- DROP TABLE public.public_user;

CREATE TABLE public.public_user
(
    user_id integer NOT NULL,
    active integer,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT public_user_pkey PRIMARY KEY (user_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.public_user
    OWNER to postgres;