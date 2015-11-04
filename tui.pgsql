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
-- Name: hresources; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hresources (
    hres_id integer NOT NULL,
    u_id integer NOT NULL,
    stock_id integer NOT NULL,
    resource text NOT NULL,
    count integer NOT NULL,
    type text NOT NULL,
    date date NOT NULL
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
-- Name: nresources; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE nresources (
    nres_id integer NOT NULL,
    u_id integer NOT NULL,
    stock_id integer NOT NULL,
    resource text NOT NULL,
    count integer NOT NULL,
    type text NOT NULL,
    date date NOT NULL
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
-- Name: requests; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE requests (
    req_id integer NOT NULL,
    u_id integer NOT NULL,
    address text NOT NULL,
    resource text NOT NULL,
    count integer NOT NULL,
    type text NOT NULL,
    date date NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE requests OWNER TO postgres;

--
-- Name: requests_req_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE requests_req_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE requests_req_id_seq OWNER TO postgres;

--
-- Name: requests_req_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE requests_req_id_seq OWNED BY requests.req_id;


--
-- Name: resources; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE resources (
    res_id integer NOT NULL,
    resource text NOT NULL,
    type text NOT NULL
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
-- Name: stocks; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE stocks (
    stock_id integer NOT NULL,
    st_name text NOT NULL,
    address text NOT NULL,
    phone text NOT NULL,
    work_time text NOT NULL
);


ALTER TABLE stocks OWNER TO postgres;

--
-- Name: stocks_stock_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE stocks_stock_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stocks_stock_id_seq OWNER TO postgres;

--
-- Name: stocks_stock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE stocks_stock_id_seq OWNED BY stocks.stock_id;


--
-- Name: transport; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE transport (
    tr_id integer NOT NULL,
    number text NOT NULL,
    descr text NOT NULL,
    type text NOT NULL,
    address text NOT NULL,
    phone text NOT NULL,
    work_time text NOT NULL
);


ALTER TABLE transport OWNER TO postgres;

--
-- Name: transport_tr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE transport_tr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE transport_tr_id_seq OWNER TO postgres;

--
-- Name: transport_tr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE transport_tr_id_seq OWNED BY transport.tr_id;


--
-- Name: volunteers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE volunteers (
    u_id integer NOT NULL,
    login text NOT NULL,
    pass text NOT NULL,
    sname text NOT NULL,
    fname text NOT NULL,
    address text NOT NULL,
    phone text NOT NULL,
    access text NOT NULL,
    hash text NOT NULL
);


ALTER TABLE volunteers OWNER TO postgres;

--
-- Name: volunteers_u_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE volunteers_u_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE volunteers_u_id_seq OWNER TO postgres;

--
-- Name: volunteers_u_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE volunteers_u_id_seq OWNED BY volunteers.u_id;


--
-- Name: ways; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ways (
    way_id integer NOT NULL,
    address_1 text NOT NULL,
    address_2 text NOT NULL,
    length double precision NOT NULL,
    type text NOT NULL
);


ALTER TABLE ways OWNER TO postgres;

--
-- Name: ways_way_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ways_way_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ways_way_id_seq OWNER TO postgres;

--
-- Name: ways_way_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ways_way_id_seq OWNED BY ways.way_id;


--
-- Name: hres_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hresources ALTER COLUMN hres_id SET DEFAULT nextval('hresources_hres_id_seq'::regclass);


--
-- Name: nres_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nresources ALTER COLUMN nres_id SET DEFAULT nextval('nresources_nres_id_seq'::regclass);


--
-- Name: req_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY requests ALTER COLUMN req_id SET DEFAULT nextval('requests_req_id_seq'::regclass);


--
-- Name: res_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resources ALTER COLUMN res_id SET DEFAULT nextval('resources_res_id_seq'::regclass);


--
-- Name: stock_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stocks ALTER COLUMN stock_id SET DEFAULT nextval('stocks_stock_id_seq'::regclass);


--
-- Name: tr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transport ALTER COLUMN tr_id SET DEFAULT nextval('transport_tr_id_seq'::regclass);


--
-- Name: u_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY volunteers ALTER COLUMN u_id SET DEFAULT nextval('volunteers_u_id_seq'::regclass);


--
-- Name: way_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ways ALTER COLUMN way_id SET DEFAULT nextval('ways_way_id_seq'::regclass);


--
-- Data for Name: hresources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hresources (hres_id, u_id, stock_id, resource, count, type, date) FROM stdin;
1	1	1	Meat	14	kg	2015-11-18
2	1	1	Apples	3	kg	2015-11-06
3	1	1	Water	100	litr	2015-11-02
4	2	1	Water	250	litr	2015-11-02
5	2	1	Apples	350	kol	2015-11-02
\.


--
-- Name: hresources_hres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hresources_hres_id_seq', 5, true);


--
-- Data for Name: nresources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY nresources (nres_id, u_id, stock_id, resource, count, type, date) FROM stdin;
1	1	1	Meat	3	kg	2015-11-20
2	2	1	Meat	10	kg	2015-11-02
\.


--
-- Name: nresources_nres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('nresources_nres_id_seq', 2, true);


--
-- Data for Name: requests; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY requests (req_id, u_id, address, resource, count, type, date, active) FROM stdin;
1	1	dasogfaidfjg	Apples	10	kg	2015-11-04	t
2	1	erfg	Apples	25	kol	2015-11-01	t
3	1		Bint	2	upakovka	2015-11-02	t
4	2	office1	Meat	10	kg	2015-11-02	t
5	2	office1	Bint	1	upakovka	2015-11-02	t
\.


--
-- Name: requests_req_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('requests_req_id_seq', 5, true);


--
-- Data for Name: resources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY resources (res_id, resource, type) FROM stdin;
2	Apples	kol
3	Bint	upakovka
4	Water	litr
5	Meat	kg
\.


--
-- Name: resources_res_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('resources_res_id_seq', 5, true);


--
-- Data for Name: stocks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY stocks (stock_id, st_name, address, phone, work_time) FROM stdin;
1	my first stock	gde-to	435	12-16
\.


--
-- Name: stocks_stock_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('stocks_stock_id_seq', 1, true);


--
-- Data for Name: transport; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY transport (tr_id, number, descr, type, address, phone, work_time) FROM stdin;
1	AE 22-33	белый	Грузовая	ул.Ю.Ленинцев, 25	222-2222	13:00-24:00
2	AE 55-33	синий	Легковая	пр.К.Маркса, 2	555-5555	00:00-24:00
3	1	2	Грузовая	3	4	5
\.


--
-- Name: transport_tr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('transport_tr_id_seq', 3, true);


--
-- Data for Name: volunteers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY volunteers (u_id, login, pass, sname, fname, address, phone, access, hash) FROM stdin;
1	admin	admin	ASdmin	Idmin	ads	+23543	admin	;fadsjfsednfhl
2	volunteer1	admin	Ivanov	Ivan	v1@dlit.dp.ua	222222222	user	-1509591577
\.


--
-- Name: volunteers_u_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('volunteers_u_id_seq', 3, true);


--
-- Data for Name: ways; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ways (way_id, address_1, address_2, length, type) FROM stdin;
\.


--
-- Name: ways_way_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ways_way_id_seq', 1, false);


--
-- Name: hresources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hresources
    ADD CONSTRAINT hresources_pkey PRIMARY KEY (hres_id);


--
-- Name: nresources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nresources
    ADD CONSTRAINT nresources_pkey PRIMARY KEY (nres_id);


--
-- Name: requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY requests
    ADD CONSTRAINT requests_pkey PRIMARY KEY (req_id);


--
-- Name: resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT resources_pkey PRIMARY KEY (res_id);


--
-- Name: stocks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY stocks
    ADD CONSTRAINT stocks_pkey PRIMARY KEY (stock_id);


--
-- Name: transport_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transport
    ADD CONSTRAINT transport_pkey PRIMARY KEY (tr_id);


--
-- Name: unique_hres_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hresources
    ADD CONSTRAINT unique_hres_id UNIQUE (hres_id);


--
-- Name: unique_login; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY volunteers
    ADD CONSTRAINT unique_login UNIQUE (login);


--
-- Name: unique_nres_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nresources
    ADD CONSTRAINT unique_nres_id UNIQUE (nres_id);


--
-- Name: unique_req_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY requests
    ADD CONSTRAINT unique_req_id UNIQUE (req_id);


--
-- Name: unique_res_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT unique_res_id UNIQUE (res_id);


--
-- Name: unique_stock_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY stocks
    ADD CONSTRAINT unique_stock_id UNIQUE (stock_id);


--
-- Name: unique_tr_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transport
    ADD CONSTRAINT unique_tr_id UNIQUE (tr_id);


--
-- Name: unique_u_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY volunteers
    ADD CONSTRAINT unique_u_id UNIQUE (u_id);


--
-- Name: unique_way_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ways
    ADD CONSTRAINT unique_way_id UNIQUE (way_id);


--
-- Name: volunteers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY volunteers
    ADD CONSTRAINT volunteers_pkey PRIMARY KEY (u_id);


--
-- Name: ways_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ways
    ADD CONSTRAINT ways_pkey PRIMARY KEY (way_id);


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

