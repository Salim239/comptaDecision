--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: compta-decision2; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "compta-decision2" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';


ALTER DATABASE "compta-decision2" OWNER TO postgres;

\connect -reuse-previous=on "dbname='compta-decision2'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: acompte_provisionnel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.acompte_provisionnel (
    id bigint NOT NULL,
    annee integer NOT NULL,
    numero integer NOT NULL,
    jhi_date date,
    numero_quittance character varying(255),
    montant_base numeric(10,2),
    montant_acompte_provisionnel numeric(10,2),
    montant_report_anterieur numeric(10,2),
    montant_retenue_source numeric(10,2),
    montant_net numeric(10,2),
    fiche_client_id bigint,
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.acompte_provisionnel OWNER TO postgres;

--
-- Name: activite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.activite (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    libelle character varying(255) NOT NULL,
    description character varying(255),
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.activite OWNER TO postgres;

--
-- Name: cnss; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cnss (
    id bigint NOT NULL,
    annee integer NOT NULL,
    trimestre integer NOT NULL,
    jhi_date date,
    numero_quittance character varying(255),
    montant_salaire_brut_normal numeric(10,2),
    montant_salaire_brut_karama numeric(10,2),
    montant_salaire_brut_autre numeric(10,2),
    montant_total numeric(10,2),
    cnss numeric(10,2),
    fiche_client_id bigint,
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    type_cnss character varying(255)
);


ALTER TABLE public.cnss OWNER TO postgres;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- Name: declaration_annuelle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.declaration_annuelle (
    id bigint NOT NULL,
    annee integer NOT NULL,
    date_paiement date,
    numero_quittance character varying(255),
    montant_chiffre_affaire_ht numeric(10,2),
    montant_chiffre_affaire_export numeric(10,2),
    montant_chiffre_affaire_local numeric(10,2),
    montant_chiffre_affaire_ttc numeric(10,2),
    montant_resultat_comptable numeric(10,2),
    montant_resultat_fiscal numeric(10,2),
    montant_autre_deduction numeric(10,2),
    montant_base_imposable numeric(10,2),
    montant_impot_liquide numeric(10,2),
    montant_acompte_provisionnel numeric(10,2),
    montant_retenue_source numeric(10,2),
    montant_net_a_paye numeric(10,2),
    fiche_client_id bigint,
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    type_declaration character varying(255)
);


ALTER TABLE public.declaration_annuelle OWNER TO postgres;

--
-- Name: declaration_employeur_annuelle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.declaration_employeur_annuelle (
    id bigint NOT NULL,
    annee integer NOT NULL,
    montant_annexe_1 numeric(10,2),
    montant_annexe_2 numeric(10,2),
    montant_annexe_3 numeric(10,2),
    montant_annexe_4 numeric(10,2),
    montant_annexe_5 numeric(10,2),
    montant_annexe_6 numeric(10,2),
    montant_annexe_7 numeric(10,2),
    montant_annexe_8 numeric(10,2),
    montant_annexe_9 numeric(10,2),
    montant_annexe_10 numeric(10,2),
    montant_annexe_11 numeric(10,2),
    montant_annexe_12 numeric(10,2),
    fiche_client_id bigint,
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.declaration_employeur_annuelle OWNER TO postgres;

--
-- Name: fiche_client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fiche_client (
    id bigint NOT NULL,
    categorie_client character varying(255),
    designation character varying(255) NOT NULL,
    logo bytea,
    logo_content_type character varying(255),
    adresse character varying(255) NOT NULL,
    code_postal character varying(255) NOT NULL,
    telephone character varying(255),
    fax character varying(255),
    email character varying(255),
    matricule_fiscale character varying(255) NOT NULL,
    registre_commerce character varying(255),
    date_creation date,
    cnss_employeur character varying(255),
    cnss_gerant character varying(255),
    fichier_patente bytea,
    fichier_patente_content_type character varying(255),
    secteur_activite_id bigint,
    activite_id bigint,
    activite_scondaire_id bigint,
    region_id bigint,
    ville_id bigint,
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.fiche_client OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1000
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: impot_mensuel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.impot_mensuel (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    libelle character varying(255) NOT NULL,
    description character varying(255),
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.impot_mensuel OWNER TO postgres;

--
-- Name: impot_mensuel_client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.impot_mensuel_client (
    id bigint NOT NULL,
    applicable boolean,
    fiche_client_id bigint,
    impot_mensuel_id bigint,
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.impot_mensuel_client OWNER TO postgres;

--
-- Name: jhi_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_authority OWNER TO postgres;

--
-- Name: jhi_persistent_audit_event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_event OWNER TO postgres;

--
-- Name: jhi_persistent_audit_evt_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_evt_data OWNER TO postgres;

--
-- Name: jhi_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_user (
    id bigint NOT NULL,
    login character varying(50) NOT NULL,
    password_hash character varying(60) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    email character varying(191),
    image_url character varying(256),
    activated boolean NOT NULL,
    lang_key character varying(6),
    activation_key character varying(20),
    reset_key character varying(20),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    reset_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.jhi_user OWNER TO postgres;

--
-- Name: jhi_user_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_user_authority OWNER TO postgres;

--
-- Name: quittance_mensuelle_impot; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quittance_mensuelle_impot (
    id bigint NOT NULL,
    annee integer NOT NULL,
    mois integer NOT NULL,
    numero_quittance character varying(255),
    date_paiement date,
    montant_paye numeric(10,2),
    fiche_client_id bigint,
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    type_declaration character varying(255)
);


ALTER TABLE public.quittance_mensuelle_impot OWNER TO postgres;

--
-- Name: quittance_mensuelle_impot_line; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quittance_mensuelle_impot_line (
    id bigint NOT NULL,
    montant_paye numeric(10,2),
    quittance_mensuelle_impot_id bigint,
    impot_mensuel_client_id bigint,
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.quittance_mensuelle_impot_line OWNER TO postgres;

--
-- Name: region; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.region (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    libelle character varying(255) NOT NULL,
    description character varying(255),
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.region OWNER TO postgres;

--
-- Name: secteur_activite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.secteur_activite (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    libelle character varying(255) NOT NULL,
    description character varying(255),
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.secteur_activite OWNER TO postgres;

--
-- Name: ville; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ville (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    code_postal character varying(255),
    libelle character varying(255) NOT NULL,
    description character varying(255),
    region_id bigint,
    created_by character varying(50) DEFAULT 'admin'::character varying NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.ville OWNER TO postgres;

--
-- Data for Name: acompte_provisionnel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.acompte_provisionnel (id, annee, numero, jhi_date, numero_quittance, montant_base, montant_acompte_provisionnel, montant_report_anterieur, montant_retenue_source, montant_net, fiche_client_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: activite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.activite (id, code, libelle, description, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1005	QUINCAILLERIE	Quincaillerie	\N	admin	\N	\N	\N
1006	VENTE_DETAIL_PRODUIT_ALIMENTAIRE	Vente détail produit alimentaire	\N	admin	\N	\N	\N
1007	ARCHITECTE	Architecte	\N	admin	\N	\N	\N
1008	TOPOGRAPHIE	Topographie	\N	admin	\N	\N	\N
\.


--
-- Data for Name: cnss; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cnss (id, annee, trimestre, jhi_date, numero_quittance, montant_salaire_brut_normal, montant_salaire_brut_karama, montant_salaire_brut_autre, montant_total, cnss, fiche_client_id, created_by, created_date, last_modified_by, last_modified_date, type_cnss) FROM stdin;
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
00000000000000	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2019-04-03 21:31:08.146275	1	EXECUTED	7:a6235f40597a13436aa36c6d61db2269	createSequence sequenceName=hibernate_sequence		\N	3.5.4	\N	\N	4319868083
00000000000001	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2019-04-03 21:31:08.268645	2	EXECUTED	7:eaed1392e0ea670b3df53ab7cc511fe2	createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...		\N	3.5.4	\N	\N	4319868083
20190403192318-1	jhipster	config/liquibase/changelog/20190403192318_added_entity_Region.xml	2019-04-03 21:31:08.315533	3	EXECUTED	7:21cfb763dcf50eb56951743ba4193633	createTable tableName=region		\N	3.5.4	\N	\N	4319868083
20190403192319-1	jhipster	config/liquibase/changelog/20190403192319_added_entity_Ville.xml	2019-04-03 21:31:08.331135	4	EXECUTED	7:99d34fa9b1c02e4a2ffebb724e84c292	createTable tableName=ville		\N	3.5.4	\N	\N	4319868083
20190403192320-1	jhipster	config/liquibase/changelog/20190403192320_added_entity_SecteurActivite.xml	2019-04-03 21:31:08.50038	5	EXECUTED	7:08dddf2791f325ba32d0a8144b993fb8	createTable tableName=secteur_activite		\N	3.5.4	\N	\N	4319868083
20190403192321-1	jhipster	config/liquibase/changelog/20190403192321_added_entity_Activite.xml	2019-04-03 21:31:08.516001	6	EXECUTED	7:4fe9c8acdfedb82d5baae4e8589d8a7b	createTable tableName=activite		\N	3.5.4	\N	\N	4319868083
20190403192322-1	jhipster	config/liquibase/changelog/20190403192322_added_entity_ImpotMensuel.xml	2019-04-03 21:31:08.531653	7	EXECUTED	7:c4aa3d896e5173e20f672d97850a2938	createTable tableName=impot_mensuel		\N	3.5.4	\N	\N	4319868083
20190403192323-1	jhipster	config/liquibase/changelog/20190403192323_added_entity_FicheClient.xml	2019-04-03 21:31:08.562864	8	EXECUTED	7:39288c6dd1bd643c9df1172f197ca8c5	createTable tableName=fiche_client		\N	3.5.4	\N	\N	4319868083
20190403192324-1	jhipster	config/liquibase/changelog/20190403192324_added_entity_Cnss.xml	2019-04-03 21:31:08.569373	9	EXECUTED	7:8737fa83851849b86738187a61f06d94	createTable tableName=cnss		\N	3.5.4	\N	\N	4319868083
20190403192325-1	jhipster	config/liquibase/changelog/20190403192325_added_entity_ImpotMensuelClient.xml	2019-04-03 21:31:08.584999	10	EXECUTED	7:a57c0c148c0f40726347786cd2ebdbd2	createTable tableName=impot_mensuel_client		\N	3.5.4	\N	\N	4319868083
20190403192326-1	jhipster	config/liquibase/changelog/20190403192326_added_entity_QuittanceMensuelleImpot.xml	2019-04-03 21:31:08.60062	11	EXECUTED	7:5dd953ffe1ad48451e1cec11b950d91f	createTable tableName=quittance_mensuelle_impot		\N	3.5.4	\N	\N	4319868083
20190403192327-1	jhipster	config/liquibase/changelog/20190403192327_added_entity_QuittanceMensuelleImpotLine.xml	2019-04-03 21:31:08.60062	12	EXECUTED	7:44e4972909f7e4391c223d199d3ded2c	createTable tableName=quittance_mensuelle_impot_line		\N	3.5.4	\N	\N	4319868083
20190403192328-1	jhipster	config/liquibase/changelog/20190403192328_added_entity_DeclarationAnnuelle.xml	2019-04-03 21:31:08.616244	13	EXECUTED	7:bbe97428d40a1157ce9a9935719f5857	createTable tableName=declaration_annuelle		\N	3.5.4	\N	\N	4319868083
20190403192329-1	jhipster	config/liquibase/changelog/20190403192329_added_entity_DeclarationEmployeurAnnuelle.xml	2019-04-03 21:31:08.631913	14	EXECUTED	7:92489bf2fd529ba3f7df935529608648	createTable tableName=declaration_employeur_annuelle		\N	3.5.4	\N	\N	4319868083
20190403192330-1	jhipster	config/liquibase/changelog/20190403192330_added_entity_AcompteProvisionnel.xml	2019-04-03 21:31:08.647485	15	EXECUTED	7:c9735a1105eb8720c96de783bd4ac6da	createTable tableName=acompte_provisionnel		\N	3.5.4	\N	\N	4319868083
20190403192319-2	jhipster	config/liquibase/changelog/20190403192319_added_entity_constraints_Ville.xml	2019-04-03 21:31:08.647485	16	EXECUTED	7:6e43051e229f5ed361b4f4fbc21ff4d0	addForeignKeyConstraint baseTableName=ville, constraintName=fk_ville_region_id, referencedTableName=region		\N	3.5.4	\N	\N	4319868083
20190403192323-2	jhipster	config/liquibase/changelog/20190403192323_added_entity_constraints_FicheClient.xml	2019-04-03 21:31:08.647485	17	EXECUTED	7:1e24a24f35996bce857ca1e6930cb7e1	addForeignKeyConstraint baseTableName=fiche_client, constraintName=fk_fiche_client_secteur_activite_id, referencedTableName=secteur_activite; addForeignKeyConstraint baseTableName=fiche_client, constraintName=fk_fiche_client_activite_id, reference...		\N	3.5.4	\N	\N	4319868083
20190403192324-2	jhipster	config/liquibase/changelog/20190403192324_added_entity_constraints_Cnss.xml	2019-04-03 21:31:08.663138	18	EXECUTED	7:70bebb54d4c389fc957d6cbe140575b5	addForeignKeyConstraint baseTableName=cnss, constraintName=fk_cnss_fiche_client_id, referencedTableName=fiche_client		\N	3.5.4	\N	\N	4319868083
20190403192325-2	jhipster	config/liquibase/changelog/20190403192325_added_entity_constraints_ImpotMensuelClient.xml	2019-04-03 21:31:08.669677	19	EXECUTED	7:b8fb475e74595bb1fa24f6cfaf1b60e6	addForeignKeyConstraint baseTableName=impot_mensuel_client, constraintName=fk_impot_mensuel_client_fiche_client_id, referencedTableName=fiche_client; addForeignKeyConstraint baseTableName=impot_mensuel_client, constraintName=fk_impot_mensuel_clien...		\N	3.5.4	\N	\N	4319868083
20190403192326-2	jhipster	config/liquibase/changelog/20190403192326_added_entity_constraints_QuittanceMensuelleImpot.xml	2019-04-03 21:31:08.669677	20	EXECUTED	7:91bdb24d8199a34977bebb1a26e9947c	addForeignKeyConstraint baseTableName=quittance_mensuelle_impot, constraintName=fk_quittance_mensuelle_impot_fiche_client_id, referencedTableName=fiche_client		\N	3.5.4	\N	\N	4319868083
20190403192327-2	jhipster	config/liquibase/changelog/20190403192327_added_entity_constraints_QuittanceMensuelleImpotLine.xml	2019-04-03 21:31:08.669677	21	EXECUTED	7:f3a789efe5c50c639fc45a8b32ab3e0c	addForeignKeyConstraint baseTableName=quittance_mensuelle_impot_line, constraintName=fk_quittance_mensuelle_impot_line_quittance_mensuelle_impot_id, referencedTableName=quittance_mensuelle_impot		\N	3.5.4	\N	\N	4319868083
20190403192328-2	jhipster	config/liquibase/changelog/20190403192328_added_entity_constraints_DeclarationAnnuelle.xml	2019-04-03 21:31:08.669677	22	EXECUTED	7:d34de311e6ad854b8a7507c2256314e4	addForeignKeyConstraint baseTableName=declaration_annuelle, constraintName=fk_declaration_annuelle_fiche_client_id, referencedTableName=fiche_client		\N	3.5.4	\N	\N	4319868083
20190403192329-2	jhipster	config/liquibase/changelog/20190403192329_added_entity_constraints_DeclarationEmployeurAnnuelle.xml	2019-04-03 21:31:08.685305	23	EXECUTED	7:996b4332b846c3066e72c57700ee927c	addForeignKeyConstraint baseTableName=declaration_employeur_annuelle, constraintName=fk_declaration_employeur_annuelle_fiche_client_id, referencedTableName=fiche_client		\N	3.5.4	\N	\N	4319868083
20190403192330-2	jhipster	config/liquibase/changelog/20190403192330_added_entity_constraints_AcompteProvisionnel.xml	2019-04-03 21:31:08.685305	24	EXECUTED	7:50092497bef2fbefa9cca319cdf5c899	addForeignKeyConstraint baseTableName=acompte_provisionnel, constraintName=fk_acompte_provisionnel_fiche_client_id, referencedTableName=fiche_client		\N	3.5.4	\N	\N	4319868083
20190403192327-10	salim	config/liquibase/changelog/20190403192327_added_entity_QuittanceMensuelleImpotLine.xml	2019-04-11 23:47:35.090732	25	EXECUTED	7:a9b937cc8745f8f3742f60d157f54388	addColumn tableName=quittance_mensuelle_impot_line		\N	3.5.4	\N	\N	5019254319
20190403192327-20	salim	config/liquibase/changelog/20190403192327_added_entity_constraints_QuittanceMensuelleImpotLine.xml	2019-04-11 23:50:30.852519	26	EXECUTED	7:4a7740588b0c7799df8f82cc25e7ad7e	addForeignKeyConstraint baseTableName=quittance_mensuelle_impot_line, constraintName=fk_quittance_mensuelle_impot_line_impot_mensuel_cient_id, referencedTableName=impot_mensuel_client		\N	3.5.4	\N	\N	5019429988
20190403192327-11	salim	config/liquibase/changelog/20190403192327_added_entity_QuittanceMensuelleImpotLine.xml	2019-04-12 00:16:26.520862	27	EXECUTED	7:6ec9b59978cdbd7af5b8c14f0c9a2c4e	addColumn tableName=quittance_mensuelle_impot_line		\N	3.5.4	\N	\N	5020985718
20190403192327-21	salim	config/liquibase/changelog/20190403192327_added_entity_constraints_QuittanceMensuelleImpotLine.xml	2019-04-12 00:16:26.575918	28	EXECUTED	7:57a57f9c32aefd1ec4a703f1d4a1dbd9	addForeignKeyConstraint baseTableName=quittance_mensuelle_impot_line, constraintName=fk_quittance_mensuelle_impot_line_impot_mensuel_client_id, referencedTableName=impot_mensuel_client		\N	3.5.4	\N	\N	5020985718
20190403192327-12	salim	config/liquibase/changelog/20190403192327_added_entity_QuittanceMensuelleImpotLine.xml	2019-04-12 00:32:50.187347	29	EXECUTED	7:ae7a531513618f49a0e0d5c24e478a46	dropColumn tableName=quittance_mensuelle_impot_line		\N	3.5.4	\N	\N	5021970122
20190403192331	salim	config/liquibase/changelog/20190403192331_added_auditing_colums.xml	2019-04-14 16:58:06.878198	30	EXECUTED	7:f24823b4bda63ad1aa2e5643f3802021	addColumn tableName=region; addColumn tableName=ville; addColumn tableName=secteur_activite; addColumn tableName=activite; addColumn tableName=acompte_provisionnel; addColumn tableName=cnss; addColumn tableName=declaration_annuelle; addColumn tabl...		\N	3.5.4	\N	\N	5253886820
20190403192324-2	salim	config/liquibase/changelog/20190403192324_added_entity_Cnss.xml	2019-04-28 02:08:13.253673	31	EXECUTED	7:c78563eaec130b2ee8e7e38cd244cfaf	addColumn tableName=cnss		\N	3.5.4	\N	\N	6410093132
20190403192326-2	salim	config/liquibase/changelog/20190403192326_added_entity_QuittanceMensuelleImpot.xml	2019-04-28 02:08:13.279005	32	EXECUTED	7:117075c6bed10187a3863c08a8e1d53a	addColumn tableName=quittance_mensuelle_impot		\N	3.5.4	\N	\N	6410093132
20190403192328-2	salim	config/liquibase/changelog/20190403192328_added_entity_DeclarationAnnuelle.xml	2019-04-28 02:08:13.290382	33	EXECUTED	7:4050fe16e4982865ab8cca6738db2380	addColumn tableName=declaration_annuelle		\N	3.5.4	\N	\N	6410093132
20190403192328-3	salim	config/liquibase/changelog/20190403192328_added_entity_DeclarationAnnuelle.xml	2019-04-29 00:58:23.439073	34	EXECUTED	7:de4a7ac3f2c75da539fa2f8f63f6c36f	renameColumn newColumnName=montant_chiffre_affaire_local, oldColumnName=montant_chiffre_affaire_impot, tableName=declaration_annuelle; renameColumn newColumnName=montant_resultat_fiscal, oldColumnName=montant_deduction_commune, tableName=declarati...		\N	3.5.4	\N	\N	6492303384
20190403192325-10	salim	config/liquibase/changelog/20190403192325_added_entity_ImpotMensuelClient.xml	2019-09-24 00:10:31.509754	35	EXECUTED	7:c269279e7e434ea4b3b7ea9994e8f77a	dropColumn columnName=quittance_mensuelle_impot_line_id, tableName=impot_mensuel_client		\N	3.5.4	\N	\N	9276631431
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: declaration_annuelle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.declaration_annuelle (id, annee, date_paiement, numero_quittance, montant_chiffre_affaire_ht, montant_chiffre_affaire_export, montant_chiffre_affaire_local, montant_chiffre_affaire_ttc, montant_resultat_comptable, montant_resultat_fiscal, montant_autre_deduction, montant_base_imposable, montant_impot_liquide, montant_acompte_provisionnel, montant_retenue_source, montant_net_a_paye, fiche_client_id, created_by, created_date, last_modified_by, last_modified_date, type_declaration) FROM stdin;
\.


--
-- Data for Name: declaration_employeur_annuelle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.declaration_employeur_annuelle (id, annee, montant_annexe_1, montant_annexe_2, montant_annexe_3, montant_annexe_4, montant_annexe_5, montant_annexe_6, montant_annexe_7, montant_annexe_8, montant_annexe_9, montant_annexe_10, montant_annexe_11, montant_annexe_12, fiche_client_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: fiche_client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fiche_client (id, categorie_client, designation, logo, logo_content_type, adresse, code_postal, telephone, fax, email, matricule_fiscale, registre_commerce, date_creation, cnss_employeur, cnss_gerant, fichier_patente, fichier_patente_content_type, secteur_activite_id, activite_id, activite_scondaire_id, region_id, ville_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
4902	PERSONNE_PHYSIQUE	qaq	\N	\N	sszz	1414	\N	\N	\N	deded	\N	\N	\N	\N	\N	\N	1052	1006	\N	\N	\N	admin	2019-09-25 22:21:21.684	admin	2019-09-25 22:41:33.143
\.


--
-- Data for Name: impot_mensuel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.impot_mensuel (id, code, libelle, description, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1151	RS	RS	Raison Sociale	admin	\N	\N	\N
1201	TFP	TFP	Taxe de Formation Professionnelle	admin	\N	\N	\N
1202	FOPROLS	FOPROLS	Fond de Promotion de Logement Sociaux	admin	\N	\N	\N
1203	TVA	TVA	Taxe sur la Valeur Ajoutée	admin	\N	\N	\N
1204	TCL	TCL	Taxe de Collectivité Locale	admin	\N	\N	\N
1205	IS	IS	Impôt sur Société	admin	\N	\N	\N
1206	EMP	EMP	Déclaration Employeur	admin	\N	\N	\N
2601	AUTRE	Autre	Autre impôt	admin	\N	\N	\N
\.


--
-- Data for Name: impot_mensuel_client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.impot_mensuel_client (id, applicable, fiche_client_id, impot_mensuel_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
4959	f	4902	1151	admin	2019-09-25 22:21:21.686	admin	2019-09-25 22:41:33.144
4960	f	4902	1201	admin	2019-09-25 22:21:21.687	admin	2019-09-25 22:41:33.145
4961	f	4902	1202	admin	2019-09-25 22:21:21.687	admin	2019-09-25 22:41:33.145
4962	t	4902	1203	admin	2019-09-25 22:21:21.688	admin	2019-09-25 22:41:33.146
4963	f	4902	1204	admin	2019-09-25 22:21:21.69	admin	2019-09-25 22:41:33.147
4964	t	4902	1205	admin	2019-09-25 22:21:21.692	admin	2019-09-25 22:41:33.148
4965	t	4902	1206	admin	2019-09-25 22:21:21.693	admin	2019-09-25 22:41:33.149
4966	f	4902	2601	admin	2019-09-25 22:21:21.693	admin	2019-09-25 22:41:33.15
\.


--
-- Data for Name: jhi_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_authority (name) FROM stdin;
ROLE_ADMIN
ROLE_USER
\.


--
-- Data for Name: jhi_persistent_audit_event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
951	admin	2019-04-03 19:32:25.885	AUTHENTICATION_SUCCESS
1051	admin	2019-04-03 22:03:30.576	AUTHENTICATION_SUCCESS
1052	admin	2019-04-03 22:14:40.321	AUTHENTICATION_SUCCESS
1053	admin	2019-04-03 22:14:55.122	AUTHENTICATION_SUCCESS
1151	admin	2019-04-03 22:24:02.955	AUTHENTICATION_SUCCESS
1501	admin	2019-04-03 23:20:07.482	AUTHENTICATION_SUCCESS
1701	user	2019-04-08 21:27:42.542	AUTHENTICATION_SUCCESS
1702	user	2019-04-08 21:42:32.731	AUTHENTICATION_SUCCESS
1752	user	2019-04-09 22:37:43.694	AUTHENTICATION_SUCCESS
1751	user	2019-04-09 22:37:43.694	AUTHENTICATION_SUCCESS
1801	admin	2019-04-09 22:51:31.021	AUTHENTICATION_SUCCESS
1802	admin	2019-04-09 22:51:32.753	AUTHENTICATION_SUCCESS
1901	admin	2019-04-09 23:12:28.12	AUTHENTICATION_SUCCESS
1951	admin	2019-04-11 21:56:31.325	AUTHENTICATION_SUCCESS
1952	admin	2019-04-11 21:56:32.242	AUTHENTICATION_SUCCESS
2001	admin	2019-04-11 23:25:23.849	AUTHENTICATION_SUCCESS
2051	admin	2019-04-11 23:28:02.504	AUTHENTICATION_SUCCESS
2201	admin	2019-04-11 23:55:56.282	AUTHENTICATION_SUCCESS
2251	admin	2019-04-12 00:01:58.043	AUTHENTICATION_SUCCESS
2252	admin	2019-04-12 00:01:58.5	AUTHENTICATION_SUCCESS
2253	admin	2019-04-12 00:02:04.748	AUTHENTICATION_SUCCESS
2254	admin	2019-04-12 00:02:04.783	AUTHENTICATION_SUCCESS
2255	admin	2019-04-12 00:02:04.797	AUTHENTICATION_SUCCESS
2256	admin	2019-04-12 00:04:30.138	AUTHENTICATION_SUCCESS
2301	admin	2019-04-13 17:43:46.842	AUTHENTICATION_SUCCESS
2302	admin	2019-04-13 17:45:12.127	AUTHENTICATION_SUCCESS
2303	admin	2019-04-13 17:53:19.183	AUTHENTICATION_SUCCESS
2351	admin	2019-04-13 18:00:47.965	AUTHENTICATION_SUCCESS
2352	admin	2019-04-13 18:00:49.255	AUTHENTICATION_SUCCESS
2401	admin	2019-04-13 19:50:38.968	AUTHENTICATION_SUCCESS
2451	admin	2019-04-14 01:03:52.956	AUTHENTICATION_SUCCESS
2601	admin	2019-04-14 01:37:10.025	AUTHENTICATION_SUCCESS
3351	admin	2019-04-14 15:21:06.295	AUTHENTICATION_SUCCESS
3352	user	2019-04-14 15:21:24.573	AUTHENTICATION_SUCCESS
3401	admin	2019-04-14 17:28:43.402	AUTHENTICATION_SUCCESS
3402	admin	2019-04-14 19:19:03.733	AUTHENTICATION_SUCCESS
3403	adminadmin	2019-04-14 19:19:51.641	AUTHENTICATION_FAILURE
3404	admin	2019-04-14 19:20:03.286	AUTHENTICATION_SUCCESS
3451	admin	2019-04-14 19:28:35.52	AUTHENTICATION_SUCCESS
3452	user	2019-04-14 19:43:29.649	AUTHENTICATION_SUCCESS
3751	salim	2019-04-14 21:17:37.121	AUTHENTICATION_SUCCESS
3752	admin	2019-04-14 21:17:37.44	AUTHENTICATION_SUCCESS
3753	user	2019-04-14 21:18:42.049	AUTHENTICATION_SUCCESS
3951	admin	2019-04-14 22:31:42.288	AUTHENTICATION_FAILURE
3952	admin	2019-04-14 22:31:48.471	AUTHENTICATION_FAILURE
3953	admin	2019-04-14 22:31:50.261	AUTHENTICATION_FAILURE
3954	admin	2019-04-14 22:31:51.829	AUTHENTICATION_FAILURE
3955	user	2019-04-14 22:31:57.518	AUTHENTICATION_SUCCESS
3956	admin	2019-04-14 22:32:26.136	AUTHENTICATION_FAILURE
3957	admin	2019-04-14 22:32:39.388	AUTHENTICATION_SUCCESS
4001	admin	2019-04-27 22:12:48.923	AUTHENTICATION_FAILURE
4002	admin	2019-04-27 22:12:55.004	AUTHENTICATION_FAILURE
4003	admin	2019-04-27 22:13:03.777	AUTHENTICATION_FAILURE
4004	admin	2019-04-27 22:13:06.787	AUTHENTICATION_FAILURE
4005	user	2019-04-27 22:13:14.047	AUTHENTICATION_FAILURE
4006	user	2019-04-27 22:13:15.673	AUTHENTICATION_FAILURE
4007	user	2019-04-27 22:15:11.251	AUTHENTICATION_FAILURE
4008	user	2019-04-27 22:15:12.506	AUTHENTICATION_FAILURE
4051	admin	2019-04-27 22:20:29.727	AUTHENTICATION_FAILURE
4052	user	2019-04-27 22:20:48.462	AUTHENTICATION_SUCCESS
4053	admin	2019-04-27 22:21:08.652	AUTHENTICATION_FAILURE
4054	admin	2019-04-27 22:21:21.006	AUTHENTICATION_FAILURE
4055	admin	2019-04-27 22:21:26.486	AUTHENTICATION_FAILURE
4056	admin	2019-04-27 22:32:47.986	AUTHENTICATION_FAILURE
4057	admin	2019-04-27 22:32:48.627	AUTHENTICATION_FAILURE
4058	admin	2019-04-27 22:32:53.645	AUTHENTICATION_FAILURE
4059	admin	2019-04-27 22:32:54.578	AUTHENTICATION_FAILURE
4060	admin	2019-04-27 22:32:55.128	AUTHENTICATION_FAILURE
4061	admin	2019-04-27 22:32:55.617	AUTHENTICATION_FAILURE
4062	admin	2019-04-27 22:32:55.801	AUTHENTICATION_FAILURE
4063	admin	2019-04-27 22:32:55.991	AUTHENTICATION_FAILURE
4064	admin	2019-04-27 22:32:56.433	AUTHENTICATION_FAILURE
4065	admin	2019-04-27 22:32:56.613	AUTHENTICATION_FAILURE
4066	admin	2019-04-27 22:32:56.618	AUTHENTICATION_FAILURE
4067	admin	2019-04-27 22:32:56.621	AUTHENTICATION_FAILURE
4068	admin	2019-04-27 22:32:56.628	AUTHENTICATION_FAILURE
4069	admin	2019-04-27 22:32:57.577	AUTHENTICATION_FAILURE
4070	salim	2019-04-27 22:33:09.714	AUTHENTICATION_SUCCESS
4071	admin	2019-04-27 22:33:35.209	AUTHENTICATION_FAILURE
4072	admin	2019-04-27 22:33:35.566	AUTHENTICATION_FAILURE
4073	admin	2019-04-27 22:33:40.895	AUTHENTICATION_FAILURE
4074	admin	2019-04-27 22:33:41.56	AUTHENTICATION_FAILURE
4075	admin	2019-04-27 22:33:46.734	AUTHENTICATION_FAILURE
4076	admin	2019-04-27 22:33:47.572	AUTHENTICATION_FAILURE
4077	admin	2019-04-27 22:33:47.828	AUTHENTICATION_FAILURE
4078	admin	2019-04-27 22:33:48.569	AUTHENTICATION_FAILURE
4079	admin	2019-04-27 22:35:12.639	AUTHENTICATION_FAILURE
4080	admin	2019-04-27 22:35:16.107	AUTHENTICATION_FAILURE
4081	admin	2019-04-27 22:35:17.276	AUTHENTICATION_FAILURE
4082	admin	2019-04-27 22:35:17.901	AUTHENTICATION_FAILURE
4083	salim	2019-04-27 22:36:01.759	AUTHENTICATION_FAILURE
4084	salim	2019-04-27 22:36:04	AUTHENTICATION_FAILURE
4085	salim	2019-04-27 22:36:11.886	AUTHENTICATION_FAILURE
4086	salim	2019-04-27 22:36:12.992	AUTHENTICATION_FAILURE
4087	user	2019-04-27 22:36:45.008	AUTHENTICATION_SUCCESS
4088	salim	2019-04-27 22:37:22.515	AUTHENTICATION_FAILURE
4089	salim	2019-04-27 22:38:32.553	AUTHENTICATION_SUCCESS
4090	salim	2019-04-27 22:40:19.577	AUTHENTICATION_SUCCESS
4091	admin	2019-04-27 22:40:19.587	AUTHENTICATION_SUCCESS
4092	salim	2019-04-27 22:40:19.588	AUTHENTICATION_SUCCESS
4093	salimadmin	2019-04-27 22:41:27.314	AUTHENTICATION_FAILURE
4094	admin	2019-04-27 22:41:32.121	AUTHENTICATION_SUCCESS
4101	admin	2019-04-28 06:11:08.488	AUTHENTICATION_FAILURE
4102	admin	2019-04-28 06:11:17.232	AUTHENTICATION_SUCCESS
4103	admin	2019-04-28 06:13:19.614	AUTHENTICATION_SUCCESS
4104	admin	2019-04-28 06:13:19.629	AUTHENTICATION_SUCCESS
4151	admin	2019-04-28 06:25:51.488	AUTHENTICATION_SUCCESS
4301	adminadmin	2019-09-22 22:57:41.305	AUTHENTICATION_FAILURE
4302	admin	2019-09-22 22:57:46.599	AUTHENTICATION_FAILURE
4303	admin	2019-09-22 22:57:48.719	AUTHENTICATION_FAILURE
4304	admin	2019-09-22 22:57:53.235	AUTHENTICATION_FAILURE
4305	admin	2019-09-22 22:57:57.27	AUTHENTICATION_FAILURE
4306	user	2019-09-22 22:58:03.037	AUTHENTICATION_SUCCESS
4307	salim	2019-09-22 22:58:21.253	AUTHENTICATION_SUCCESS
4308	admin	2019-09-22 22:59:35.174	AUTHENTICATION_FAILURE
4309	admin	2019-09-22 22:59:40.598	AUTHENTICATION_SUCCESS
4351	admin	2019-09-23 22:20:54.051	AUTHENTICATION_SUCCESS
4401	admin	2019-09-24 20:57:57.541	AUTHENTICATION_SUCCESS
4451	admin	2019-09-24 22:37:03.773	AUTHENTICATION_SUCCESS
4501	admin	2019-09-24 22:45:31.418	AUTHENTICATION_SUCCESS
4651	admin	2019-09-25 20:18:37.165	AUTHENTICATION_SUCCESS
4652	admin	2019-09-25 20:20:11.428	AUTHENTICATION_SUCCESS
5001	admin	2019-09-25 22:36:54.27	AUTHENTICATION_SUCCESS
5151	admin	2019-09-26 19:53:57.597	AUTHENTICATION_SUCCESS
5201	admin	2019-09-26 20:19:55.097	AUTHENTICATION_FAILURE
5202	admin	2019-09-26 20:20:00.997	AUTHENTICATION_FAILURE
5203	admin	2019-09-26 20:20:15.02	AUTHENTICATION_SUCCESS
5252	admin	2019-09-27 20:59:23.099	AUTHENTICATION_SUCCESS
5251	admin	2019-09-27 20:59:23.096	AUTHENTICATION_SUCCESS
\.


--
-- Data for Name: jhi_persistent_audit_evt_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
3403	type	org.springframework.security.authentication.BadCredentialsException
3403	message	Bad credentials
3951	type	org.springframework.security.authentication.BadCredentialsException
3951	message	Bad credentials
3952	type	org.springframework.security.authentication.BadCredentialsException
3952	message	Bad credentials
3953	type	org.springframework.security.authentication.BadCredentialsException
3953	message	Bad credentials
3954	type	org.springframework.security.authentication.BadCredentialsException
3954	message	Bad credentials
3956	type	org.springframework.security.authentication.BadCredentialsException
3956	message	Bad credentials
4001	type	org.springframework.security.authentication.BadCredentialsException
4001	message	Bad credentials
4002	type	org.springframework.security.authentication.BadCredentialsException
4002	message	Bad credentials
4003	type	org.springframework.security.authentication.BadCredentialsException
4003	message	Bad credentials
4004	type	org.springframework.security.authentication.BadCredentialsException
4004	message	Bad credentials
4005	type	org.springframework.security.authentication.BadCredentialsException
4005	message	Bad credentials
4006	type	org.springframework.security.authentication.BadCredentialsException
4006	message	Bad credentials
4007	type	org.springframework.security.authentication.BadCredentialsException
4007	message	Bad credentials
4008	type	org.springframework.security.authentication.BadCredentialsException
4008	message	Bad credentials
4051	type	org.springframework.security.authentication.BadCredentialsException
4051	message	Bad credentials
4053	type	org.springframework.security.authentication.BadCredentialsException
4053	message	Bad credentials
4054	type	org.springframework.security.authentication.BadCredentialsException
4054	message	Bad credentials
4055	type	org.springframework.security.authentication.BadCredentialsException
4055	message	Bad credentials
4056	type	org.springframework.security.authentication.BadCredentialsException
4056	message	Bad credentials
4057	type	org.springframework.security.authentication.BadCredentialsException
4057	message	Bad credentials
4058	type	org.springframework.security.authentication.BadCredentialsException
4058	message	Bad credentials
4059	type	org.springframework.security.authentication.BadCredentialsException
4059	message	Bad credentials
4060	type	org.springframework.security.authentication.BadCredentialsException
4060	message	Bad credentials
4061	type	org.springframework.security.authentication.BadCredentialsException
4061	message	Bad credentials
4062	type	org.springframework.security.authentication.BadCredentialsException
4062	message	Bad credentials
4063	type	org.springframework.security.authentication.BadCredentialsException
4063	message	Bad credentials
4064	type	org.springframework.security.authentication.BadCredentialsException
4064	message	Bad credentials
4065	type	org.springframework.security.authentication.BadCredentialsException
4065	message	Bad credentials
4066	type	org.springframework.security.authentication.BadCredentialsException
4066	message	Bad credentials
4067	type	org.springframework.security.authentication.BadCredentialsException
4067	message	Bad credentials
4068	type	org.springframework.security.authentication.BadCredentialsException
4068	message	Bad credentials
4069	type	org.springframework.security.authentication.BadCredentialsException
4069	message	Bad credentials
4071	type	org.springframework.security.authentication.BadCredentialsException
4071	message	Bad credentials
4072	type	org.springframework.security.authentication.BadCredentialsException
4072	message	Bad credentials
4073	type	org.springframework.security.authentication.BadCredentialsException
4073	message	Bad credentials
4074	type	org.springframework.security.authentication.BadCredentialsException
4074	message	Bad credentials
4075	type	org.springframework.security.authentication.BadCredentialsException
4075	message	Bad credentials
4076	type	org.springframework.security.authentication.BadCredentialsException
4076	message	Bad credentials
4077	type	org.springframework.security.authentication.BadCredentialsException
4077	message	Bad credentials
4078	type	org.springframework.security.authentication.BadCredentialsException
4078	message	Bad credentials
4079	type	org.springframework.security.authentication.BadCredentialsException
4079	message	Bad credentials
4080	type	org.springframework.security.authentication.BadCredentialsException
4080	message	Bad credentials
4081	type	org.springframework.security.authentication.BadCredentialsException
4081	message	Bad credentials
4082	type	org.springframework.security.authentication.BadCredentialsException
4082	message	Bad credentials
4083	type	org.springframework.security.authentication.BadCredentialsException
4083	message	Bad credentials
4084	type	org.springframework.security.authentication.BadCredentialsException
4084	message	Bad credentials
4085	type	org.springframework.security.authentication.BadCredentialsException
4085	message	Bad credentials
4086	type	org.springframework.security.authentication.BadCredentialsException
4086	message	Bad credentials
4088	type	org.springframework.security.authentication.BadCredentialsException
4088	message	Bad credentials
4093	type	org.springframework.security.authentication.BadCredentialsException
4093	message	Bad credentials
4101	type	org.springframework.security.authentication.BadCredentialsException
4101	message	Bad credentials
4301	type	org.springframework.security.authentication.BadCredentialsException
4301	message	Bad credentials
4302	type	org.springframework.security.authentication.BadCredentialsException
4302	message	Bad credentials
4303	type	org.springframework.security.authentication.BadCredentialsException
4303	message	Bad credentials
4304	type	org.springframework.security.authentication.BadCredentialsException
4304	message	Bad credentials
4305	type	org.springframework.security.authentication.BadCredentialsException
4305	message	Bad credentials
4308	type	org.springframework.security.authentication.BadCredentialsException
4308	message	Bad credentials
5201	type	org.springframework.security.authentication.BadCredentialsException
5201	message	Bad credentials
5202	type	org.springframework.security.authentication.BadCredentialsException
5202	message	Bad credentials
\.


--
-- Data for Name: jhi_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM stdin;
1	system	$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG	System	System	system@localhost		t	fr	\N	\N	system	\N	\N	system	\N
2	anonymoususer	$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO	Anonymous	User	anonymous@localhost		t	fr	\N	\N	system	\N	\N	system	\N
4	user	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	User	User	user@localhost		t	fr	\N	\N	system	\N	\N	system	\N
3701	salim	$2a$10$tC6fk/XXVUggcTkBm.NazuRVRRiaOSPDe1OymEUN9FLRYW7qbWQ5C	\N	\N	salim.hamidi@gmail.com	\N	t	fr	\N	\N	anonymousUser	2019-04-14 21:15:42.224	\N	anonymousUser	2019-04-27 22:38:22.769
3	admin	$2a$10$3Sj2.6n/5Nq9dtWQzztAGu4901OHgnuPCMUiC3D7./FK5Ql.4kzEm	Administrator	Administrator	salim@godeals.pro		t	fr	\N	\N	system	\N	\N	anonymousUser	2019-04-27 22:41:19.448
\.


--
-- Data for Name: jhi_user_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
1	ROLE_ADMIN
1	ROLE_USER
3	ROLE_ADMIN
3	ROLE_USER
4	ROLE_USER
3701	ROLE_USER
\.


--
-- Data for Name: quittance_mensuelle_impot; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.quittance_mensuelle_impot (id, annee, mois, numero_quittance, date_paiement, montant_paye, fiche_client_id, created_by, created_date, last_modified_by, last_modified_date, type_declaration) FROM stdin;
5051	2019	9	\N	\N	60.00	4902	admin	2019-09-25 22:42:05.14	admin	2019-09-25 22:42:05.14	0
\.


--
-- Data for Name: quittance_mensuelle_impot_line; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.quittance_mensuelle_impot_line (id, montant_paye, quittance_mensuelle_impot_id, impot_mensuel_client_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
5101	0.00	5051	4959	admin	2019-09-25 22:42:05.144	admin	2019-09-25 22:42:05.144
5102	0.00	5051	4960	admin	2019-09-25 22:42:05.145	admin	2019-09-25 22:42:05.145
5103	0.00	5051	4961	admin	2019-09-25 22:42:05.146	admin	2019-09-25 22:42:05.146
5104	10.00	5051	4962	admin	2019-09-25 22:42:05.147	admin	2019-09-25 22:42:05.147
5105	0.00	5051	4963	admin	2019-09-25 22:42:05.147	admin	2019-09-25 22:42:05.147
5106	20.00	5051	4964	admin	2019-09-25 22:42:05.147	admin	2019-09-25 22:42:05.147
5107	30.00	5051	4965	admin	2019-09-25 22:42:05.148	admin	2019-09-25 22:42:05.148
5108	0.00	5051	4966	admin	2019-09-25 22:42:05.148	admin	2019-09-25 22:42:05.148
\.


--
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.region (id, code, libelle, description, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1101	MAHDIA	MAHDIA	\N	admin	\N	\N	\N
1102	SOUSSE	SOUSSE	\N	admin	\N	\N	\N
1103	MONASTIR	MONASTIR	MONASTIR	admin	\N	\N	\N
1104	TUNIS	TUNIS	\N	admin	\N	\N	\N
1105	ARIANA	ARIANA	ARIANA	admin	\N	\N	\N
\.


--
-- Data for Name: secteur_activite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.secteur_activite (id, code, libelle, description, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1051	COMMERCE	Commerce	\N	admin	\N	\N	\N
1052	SERVICE	Service	\N	admin	\N	\N	\N
1053	INDUSTRIE	Industrie	\N	admin	\N	\N	\N
1054	AUTRES	Autres	\N	admin	\N	\N	\N
\.


--
-- Data for Name: ville; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ville (id, code, code_postal, libelle, description, region_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1201	LACHEBBA	5170	LA CHEBBA	\N	1101	admin	\N	\N	\N
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 5300, true);


--
-- Name: jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);


--
-- Name: jhi_user_authority jhi_user_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);


--
-- Name: acompte_provisionnel pk_acompte_provisionnel; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acompte_provisionnel
    ADD CONSTRAINT pk_acompte_provisionnel PRIMARY KEY (id);


--
-- Name: activite pk_activite; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activite
    ADD CONSTRAINT pk_activite PRIMARY KEY (id);


--
-- Name: cnss pk_cnss; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cnss
    ADD CONSTRAINT pk_cnss PRIMARY KEY (id);


--
-- Name: databasechangeloglock pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- Name: declaration_annuelle pk_declaration_annuelle; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.declaration_annuelle
    ADD CONSTRAINT pk_declaration_annuelle PRIMARY KEY (id);


--
-- Name: declaration_employeur_annuelle pk_declaration_employeur_annuelle; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.declaration_employeur_annuelle
    ADD CONSTRAINT pk_declaration_employeur_annuelle PRIMARY KEY (id);


--
-- Name: fiche_client pk_fiche_client; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fiche_client
    ADD CONSTRAINT pk_fiche_client PRIMARY KEY (id);


--
-- Name: impot_mensuel pk_impot_mensuel; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.impot_mensuel
    ADD CONSTRAINT pk_impot_mensuel PRIMARY KEY (id);


--
-- Name: impot_mensuel_client pk_impot_mensuel_client; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.impot_mensuel_client
    ADD CONSTRAINT pk_impot_mensuel_client PRIMARY KEY (id);


--
-- Name: jhi_authority pk_jhi_authority; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT pk_jhi_authority PRIMARY KEY (name);


--
-- Name: jhi_persistent_audit_event pk_jhi_persistent_audit_event; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_persistent_audit_event
    ADD CONSTRAINT pk_jhi_persistent_audit_event PRIMARY KEY (event_id);


--
-- Name: jhi_user pk_jhi_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT pk_jhi_user PRIMARY KEY (id);


--
-- Name: quittance_mensuelle_impot pk_quittance_mensuelle_impot; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quittance_mensuelle_impot
    ADD CONSTRAINT pk_quittance_mensuelle_impot PRIMARY KEY (id);


--
-- Name: quittance_mensuelle_impot_line pk_quittance_mensuelle_impot_line; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quittance_mensuelle_impot_line
    ADD CONSTRAINT pk_quittance_mensuelle_impot_line PRIMARY KEY (id);


--
-- Name: region pk_region; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.region
    ADD CONSTRAINT pk_region PRIMARY KEY (id);


--
-- Name: secteur_activite pk_secteur_activite; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.secteur_activite
    ADD CONSTRAINT pk_secteur_activite PRIMARY KEY (id);


--
-- Name: ville pk_ville; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ville
    ADD CONSTRAINT pk_ville PRIMARY KEY (id);


--
-- Name: jhi_user ux_user_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);


--
-- Name: jhi_user ux_user_login; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);


--
-- Name: idx_persistent_audit_event; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event USING btree (principal, event_date);


--
-- Name: idx_persistent_audit_evt_data; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data USING btree (event_id);


--
-- Name: acompte_provisionnel fk_acompte_provisionnel_fiche_client_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acompte_provisionnel
    ADD CONSTRAINT fk_acompte_provisionnel_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--
-- Name: jhi_user_authority fk_authority_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);


--
-- Name: cnss fk_cnss_fiche_client_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cnss
    ADD CONSTRAINT fk_cnss_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--
-- Name: declaration_annuelle fk_declaration_annuelle_fiche_client_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.declaration_annuelle
    ADD CONSTRAINT fk_declaration_annuelle_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--
-- Name: declaration_employeur_annuelle fk_declaration_employeur_annuelle_fiche_client_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.declaration_employeur_annuelle
    ADD CONSTRAINT fk_declaration_employeur_annuelle_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--
-- Name: jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);


--
-- Name: fiche_client fk_fiche_client_activite_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fiche_client
    ADD CONSTRAINT fk_fiche_client_activite_id FOREIGN KEY (activite_id) REFERENCES public.activite(id);


--
-- Name: fiche_client fk_fiche_client_activite_scondaire_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fiche_client
    ADD CONSTRAINT fk_fiche_client_activite_scondaire_id FOREIGN KEY (activite_scondaire_id) REFERENCES public.activite(id);


--
-- Name: fiche_client fk_fiche_client_region_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fiche_client
    ADD CONSTRAINT fk_fiche_client_region_id FOREIGN KEY (region_id) REFERENCES public.region(id);


--
-- Name: fiche_client fk_fiche_client_secteur_activite_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fiche_client
    ADD CONSTRAINT fk_fiche_client_secteur_activite_id FOREIGN KEY (secteur_activite_id) REFERENCES public.secteur_activite(id);


--
-- Name: fiche_client fk_fiche_client_ville_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fiche_client
    ADD CONSTRAINT fk_fiche_client_ville_id FOREIGN KEY (ville_id) REFERENCES public.ville(id);


--
-- Name: impot_mensuel_client fk_impot_mensuel_client_fiche_client_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.impot_mensuel_client
    ADD CONSTRAINT fk_impot_mensuel_client_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--
-- Name: impot_mensuel_client fk_impot_mensuel_client_impot_mensuel_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.impot_mensuel_client
    ADD CONSTRAINT fk_impot_mensuel_client_impot_mensuel_id FOREIGN KEY (impot_mensuel_id) REFERENCES public.impot_mensuel(id);


--
-- Name: quittance_mensuelle_impot fk_quittance_mensuelle_impot_fiche_client_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quittance_mensuelle_impot
    ADD CONSTRAINT fk_quittance_mensuelle_impot_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--
-- Name: quittance_mensuelle_impot_line fk_quittance_mensuelle_impot_line_impot_mensuel_client_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quittance_mensuelle_impot_line
    ADD CONSTRAINT fk_quittance_mensuelle_impot_line_impot_mensuel_client_id FOREIGN KEY (impot_mensuel_client_id) REFERENCES public.impot_mensuel_client(id);


--
-- Name: quittance_mensuelle_impot_line fk_quittance_mensuelle_impot_line_quittance_mensuelle_impot_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quittance_mensuelle_impot_line
    ADD CONSTRAINT fk_quittance_mensuelle_impot_line_quittance_mensuelle_impot_id FOREIGN KEY (quittance_mensuelle_impot_id) REFERENCES public.quittance_mensuelle_impot(id);


--
-- Name: jhi_user_authority fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);


--
-- Name: ville fk_ville_region_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ville
    ADD CONSTRAINT fk_ville_region_id FOREIGN KEY (region_id) REFERENCES public.region(id);


--
-- PostgreSQL database dump complete
--

