--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

-- Started on 2017-08-01 15:03:48

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 190 (class 1259 OID 25884)
-- Name: INCIDENT_CASE; Type: TABLE; Schema: public; Owner: postgres
--

DROP TABLE IF EXISTS "INCIDENT_CASE_ATTACHMENT"; 
DROP TABLE IF EXISTS "INCIDENT_CASE"; 
DROP TABLE IF EXISTS "INCIDENT_LOOKUP"; 
DROP TABLE IF EXISTS "SHIP";
DROP TABLE IF EXISTS "TMP_SHIP";
DROP TABLE IF EXISTS "TMP_INCIDENT_LOOKUP";
DROP TABLE IF EXISTS "TMP_ATTACHMENT";
DROP TABLE IF EXISTS "JOBS_LOG";
DROP TABLE IF EXISTS public_user;
DROP TABLE IF EXISTS schema_version;

CREATE TABLE "INCIDENT_CASE" (
    "ID" integer NOT NULL,
	"PRECEDENCE" text,
	"INCIDENT_TYPE" text,
	"TIMEZONE" text,
	"TIME_OF_INCIDENT" timestamp without time zone,
	"SHIP_NAME" text,
	"SHIP_CALLSIGN" text,
	"SHIP_FLAG" text,
	"SHIP_TYPE" text,
	"SHIP_GROSS_TONNAGE" text,
    "SHIP_IMO_NUMBER" text,
    "SHIP_MMSI_NUMBER" text,
    "SHIP_BUILT" text,
    "SHIP_NAME_OF_COMPANY" text,
	"POSITION_SEA_NAME" text,
    "POSITION_LATITUDE" double precision,
    "POSITION_LONGITUDE" double precision,
    "POSITION_AREA_DESCRIPTION" text,
    "POSITION_SHIP_ACTIVITY" text,
    "POSITION_ISPS_LEVEL" text,
	"DETAILS_ATTACK_METHOD" text,
    "DETAILS_DESCRIPTION_SUSPECT_CRAFT" text,
    "DETAILS_NUMBER_PIRATES" text,
    "DETAILS_TYPE_OF_WEAPONS" text,
    "DETAILS_TREATMENT_INJURES" text,
    "DETAILS_DAMAGES" text,
    "DETAILS_OTHER_INFORMATION" text,
    "DETAILS_STOLEN_CARGO" text,
    "DETAILS_REPORT_TO_AUTHORITIES" text,
    "DETAILS_ACTION_TAKEN_BY_MASTER" text,
    "DETAILS_ACTION_TAKEN_BY_COASTAL_STATE" text,
    "DETAILS_SOURCE_INFORMATION" text,
    "DETAILS_REMARKS" text,
	"SIGNIFICANCE_LEVEL" text,
    "SMS_TO_ALL_FOCAL_POINTS" text,
    "STATUS" text,
	"INCIDENT_NO" text,
	"RELATED_ID" integer,
	"CREATED_DATE" timestamp without time zone,
    "CREATED_BY" text,
    "UPDATED_DATE" timestamp without time zone,
	"UPDATED_BY" text,
	"LOCAL_TIME" text,
	"POSITION_LONGITUDE_DEGREE" double precision,
    "POSITION_LONGITUDE_MINUTE" double precision,
    "POSITION_LONGITUDE_DIRECTION" text,
    "POSITION_LATITUDE_DEGREE" double precision,
    "POSITION_LATITUDE_MINUTE" double precision,
    "POSITION_LATITUDE_DIRECTION" text,
    "SHIP_INMARSAT_ID" text,
	"TREATMENT_INJURIES" text,
	"NUMBER_OF_PIRATES" text,
	"STOLEN_CARGO" text,
	"WEAPONS_USED" text,
	"ISC_COMMENTS" text,
    "COASTAL_STATE" text,
    "FLAG_STATE" text,
	"LAST_PORT_OF_CALL" text,
	"NEXT_PORT_OF_CALL" text,
	"UPDATED_FLAG" text,
	"LAST_PORT_OF_CALL_DESC" text,
	"NEXT_PORT_OF_CALL_DESC" text,
	"VOYAGE_TYPE" text,
    "INCIDENT_CASE_NAME" text,
    "INCIDENT_CASE_DESCRIPTION" text,
    "NOT_COUNTED" text,
    "NUMBER_OF_SUSPECT_CRAFT" text,
    "LINKED_IR" text,
	CONSTRAINT "INCIDENT_CASE_pkey" PRIMARY KEY ("ID")
);


ALTER TABLE "INCIDENT_CASE" OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 25890)
-- Name: INCIDENT_CASE_ATTACHMENT; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "INCIDENT_CASE_ATTACHMENT" (
    "ID" integer NOT NULL,
	"INCIDENT_CASE_ID" integer NOT NULL,
    "ATTACHMENT_TYPE" text,
    "FILE_NAME" text,
    "EXTERNAL_ID" text,
    "ATTACHMENT_INDEX" integer,
    "FILE_CONTENT" text,
	CONSTRAINT "INCIDENT_CASE_ATTACHMENT_pkey" PRIMARY KEY ("ID"),
    CONSTRAINT "INCIDENT_CASE_ATTACHMENT_FK" FOREIGN KEY ("INCIDENT_CASE_ID")
        REFERENCES public."INCIDENT_CASE" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
    
);


ALTER TABLE "INCIDENT_CASE_ATTACHMENT" OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 43160)
-- Name: INCIDENT_LOOKUP; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "INCIDENT_LOOKUP" (
    "TYPE" text NOT NULL,
    "VALUE" text NOT NULL,
    "CATLEVEL" double precision,
    "DESCRIPTION" text,
	CONSTRAINT "INCIDENT_LOOKUP_pkey" PRIMARY KEY ("TYPE", "VALUE")
);


ALTER TABLE "INCIDENT_LOOKUP" OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 43308)
-- Name: SHIP; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "SHIP" (
    "ID" integer NOT NULL,
    "SHIP_NAME" character varying(100) NOT NULL,
    "SHIP_CALLSIGN" character varying(100) NOT NULL,
    "SHIP_FLAG" character varying(100) NOT NULL,
    "SHIP_TYPE" character varying(100) NOT NULL,
    "SHIP_GROSS_TONNAGE" character varying(100) NOT NULL,
    "SHIP_IMO_NUMBER" character varying(100) NOT NULL,
    "SHIP_MSSI_NUMBER" character varying(100) NOT NULL,
    "SHIP_BUILT" character varying(100) NOT NULL,
    "SHIP_NAME_OF_COMPANY" character varying(100) NOT NULL,
    "SHIP_INMARSAT_ID" character varying(100) NOT NULL,
    "CREATED_BY" character varying(50),
    "CREATED_DATE" timestamp without time zone,
    "UPDATED_BY" character varying(50),
    "UPDATED_DATE" timestamp without time zone,
	CONSTRAINT "SHIP_pkey" PRIMARY KEY ("ID")
);


ALTER TABLE "SHIP" OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 42626)
-- Name: JOBS_LOG; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "JOBS_LOG" (
    "ID" character varying(50) NOT NULL,
    "GROUP_ID" character varying(50) NOT NULL,
    "JOB_NAME" character varying(100),
    "START_TIME" timestamp without time zone,
    "END_TIME" timestamp without time zone,
    "STATUS" integer,
    "DESCRIPTION" text,
    "MODE" integer,
	CONSTRAINT "JOBS_LOG_pkey" PRIMARY KEY ("ID")
);


ALTER TABLE "JOBS_LOG" OWNER TO postgres;
--
-- TOC entry 186 (class 1259 OID 25821)
-- Name: TMP_INCIDENT_LOOKUP; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "TMP_INCIDENT_LOOKUP" (
    "TYPE" text NOT NULL,
    "VALUE" text NOT NULL,
    "CATLEVEL" double precision,
    "DESCRIPTION" character varying(500),
	CONSTRAINT "TMP_INCIDENT_LOOKUP_pkey" PRIMARY KEY ("TYPE", "VALUE")
);


ALTER TABLE "TMP_INCIDENT_LOOKUP" OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 26017)
-- Name: tmp_attachment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "TMP_ATTACHMENT" (
    "ID" integer NOT NULL,
    "INCIDENT_CASE_ID" integer NOT NULL,
    "UPDATED_DATE" date,
    "STATUS" integer,
	CONSTRAINT "TMP_ATTACHMENT_pkey" PRIMARY KEY ("ID")
);


ALTER TABLE "TMP_ATTACHMENT" OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 25956)
-- Name: tmp_ship; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "TMP_SHIP" (
    "ID" integer NOT NULL,
    "CREATED_BY" character varying(50),
    "CREATED_DATE" date,
    "SHIP_BUILT" character varying(100) NOT NULL,
    "SHIP_CALLSIGN" character varying(100) NOT NULL,
    "SHIP_FLAG" character varying(100) NOT NULL,
    "SHIP_GROSS_TONNAGE" character varying(100) NOT NULL,
    "SHIP_IMO_NUMBER" character varying(100) NOT NULL,
    "SHIP_INMARSAT_ID" character varying(100) NOT NULL,
    "SHIP_MSSI_NUMBER" character varying(100) NOT NULL,
    "SHIP_NAME" character varying(100) NOT NULL,
    "SHIP_NAME_OF_COMPANY" character varying(100) NOT NULL,
    "SHIP_TYPE" character varying(100) NOT NULL,
    "UPDATED_BY" character varying(50),
    "UPDATED_DATE" date,
	CONSTRAINT "TMP_SHIP_pkey" PRIMARY KEY ("ID")
);


ALTER TABLE "TMP_SHIP" OWNER TO postgres;

-- Completed on 2017-08-01 15:03:48

--
-- PostgreSQL database dump complete
--

