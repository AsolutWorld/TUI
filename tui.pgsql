--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: availble_resources; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE availble_resources (
    u_id integer,
    resources text
);


ALTER TABLE availble_resources OWNER TO postgres;

--
-- Name: hresources; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hresources (
    hres_id integer NOT NULL,
    u_id integer NOT NULL,
    resource text NOT NULL,
    available boolean DEFAULT true NOT NULL
);


ALTER TABLE hresources OWNER TO postgres;

--
-- Name: hresources_hres_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hresources_hres_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hresources_hres_id_seq OWNER TO postgres;

--
-- Name: hresources_hres_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE hresources_hres_id_seq OWNED BY hresources.hres_id;


--
-- Name: nres_people; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE nres_people (
    nresp_id integer NOT NULL,
    name text NOT NULL,
    location text NOT NULL,
    column_4 integer NOT NULL,
    phone text NOT NULL
);


ALTER TABLE nres_people OWNER TO postgres;

--
-- Name: nres_people_nresp_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE nres_people_nresp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE nres_people_nresp_id_seq OWNER TO postgres;

--
-- Name: nres_people_nresp_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE nres_people_nresp_id_seq OWNED BY nres_people.nresp_id;


--
-- Name: nresources; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE nresources (
    nres_id integer NOT NULL,
    nresp_id integer NOT NULL,
    resource text NOT NULL,
    available boolean DEFAULT true NOT NULL
);


ALTER TABLE nresources OWNER TO postgres;

--
-- Name: nresources_nres_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE nresources_nres_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE nresources_nres_id_seq OWNER TO postgres;

--
-- Name: nresources_nres_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE nresources_nres_id_seq OWNED BY nresources.nres_id;


--
-- Name: resources; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE resources (
    res_id integer NOT NULL,
    resource text NOT NULL
);


ALTER TABLE resources OWNER TO postgres;

--
-- Name: resources_res_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE resources_res_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE resources_res_id_seq OWNER TO postgres;

--
-- Name: resources_res_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE resources_res_id_seq OWNED BY resources.res_id;


--
-- Name: volunteer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE volunteer (
    u_id integer NOT NULL,
    sname text NOT NULL,
    fname text NOT NULL,
    location text NOT NULL,
    phone text NOT NULL,
    access text NOT NULL
);


ALTER TABLE volunteer OWNER TO postgres;

--
-- Name: volunteer_u_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE volunteer_u_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE volunteer_u_id_seq OWNER TO postgres;

--
-- Name: volunteer_u_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE volunteer_u_id_seq OWNED BY volunteer.u_id;


--
-- Name: hres_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hresources ALTER COLUMN hres_id SET DEFAULT nextval('hresources_hres_id_seq'::regclass);


--
-- Name: nresp_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nres_people ALTER COLUMN nresp_id SET DEFAULT nextval('nres_people_nresp_id_seq'::regclass);


--
-- Name: nres_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nresources ALTER COLUMN nres_id SET DEFAULT nextval('nresources_nres_id_seq'::regclass);


--
-- Name: res_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resources ALTER COLUMN res_id SET DEFAULT nextval('resources_res_id_seq'::regclass);


--
-- Name: u_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY volunteer ALTER COLUMN u_id SET DEFAULT nextval('volunteer_u_id_seq'::regclass);


--
-- Data for Name: availble_resources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY availble_resources (u_id, resources) FROM stdin;
\.


--
-- Data for Name: hresources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hresources (hres_id, u_id, resource, available) FROM stdin;
\.


--
-- Name: hresources_hres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hresources_hres_id_seq', 1, false);


--
-- Data for Name: nres_people; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY nres_people (nresp_id, name, location, column_4, phone) FROM stdin;
\.


--
-- Name: nres_people_nresp_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('nres_people_nresp_id_seq', 1, false);


--
-- Data for Name: nresources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY nresources (nres_id, nresp_id, resource, available) FROM stdin;
\.


--
-- Name: nresources_nres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('nresources_nres_id_seq', 1, false);


--
-- Data for Name: resources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY resources (res_id, resource) FROM stdin;
\.


--
-- Name: resources_res_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('resources_res_id_seq', 1, false);


--
-- Data for Name: volunteer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY volunteer (u_id, sname, fname, location, phone, access) FROM stdin;
\.


--
-- Name: volunteer_u_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('volunteer_u_id_seq', 1, false);


--
-- Name: hresources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hresources
    ADD CONSTRAINT hresources_pkey PRIMARY KEY (hres_id);


--
-- Name: nres_people_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nres_people
    ADD CONSTRAINT nres_people_pkey PRIMARY KEY (nresp_id);


--
-- Name: nresources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nresources
    ADD CONSTRAINT nresources_pkey PRIMARY KEY (nres_id);


--
-- Name: resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT resources_pkey PRIMARY KEY (res_id);


--
-- Name: unique_hres_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hresources
    ADD CONSTRAINT unique_hres_id UNIQUE (hres_id);


--
-- Name: unique_nres_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nresources
    ADD CONSTRAINT unique_nres_id UNIQUE (nres_id);


--
-- Name: unique_nresp_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nres_people
    ADD CONSTRAINT unique_nresp_id UNIQUE (nresp_id);


--
-- Name: unique_res_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT unique_res_id UNIQUE (res_id);


--
-- Name: unique_u_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY volunteer
    ADD CONSTRAINT unique_u_id UNIQUE (u_id);


--
-- Name: volunteer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY volunteer
    ADD CONSTRAINT volunteer_pkey PRIMARY KEY (u_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

