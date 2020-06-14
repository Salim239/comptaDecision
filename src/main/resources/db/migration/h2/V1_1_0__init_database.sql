create schema if not exists public;

CREATE TABLE public.acompte_provisionnel (
    id bigint NOT NULL,
    annee integer NOT NULL,
    numero integer NOT NULL,
    jhi_date date,
    numero_quittance  VARCHAR(255),
    montant_base numeric(20,6),
    montant_acompte_provisionnel numeric(20,6),
    montant_report_anterieur numeric(20,6),
    montant_retenue_source numeric(20,6),
    montant_net numeric(20,6),
    fiche_client_id bigint,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

CREATE TABLE public.activite (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

CREATE TABLE public.cnss (
    id bigint NOT NULL,
    annee integer NOT NULL,
    trimestre integer NOT NULL,
    jhi_date date,
    numero_quittance  VARCHAR(255),
    montant_salaire_brut_normal numeric(20,6),
    montant_salaire_brut_karama numeric(20,6),
    montant_salaire_brut_autre nUMERIC(20,6),
    montant_total numeric(10,2),
    cnss numeric(10,2),
    fiche_client_id bigint,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
    type_cnss  VARCHAR(255)
);

CREATE TABLE public.declaration_annuelle (
    id bigint NOT NULL,
    annee integer NOT NULL,
    numero  VARCHAR(255),
    date_paiement date,
    montant_impot_annuel numeric(20,6),
    montant_ap_payes numeric(20,6),
    montant_retenue_source numeric(20,6),
    montant_report_anterieur numeric(20,6),
    montant_net numeric(20,6),
    fiche_client_id bigint,
    type_declaration  VARCHAR(255),
    statut  VARCHAR(50) DEFAULT 'BROUILLON',
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);


CREATE TABLE public.declaration_annuelle_detail (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    tri_ordre integer NOT NULL,
    calcule boolean DEFAULT false,
    montant numeric(20,6),
    montant_calcule numeric(20,6),
    impot_annuel_id bigint NOT NULL,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
    declaration_annuelle_id bigint NOT NULL
);


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
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

CREATE TABLE public.fiche_client (
    id bigint NOT NULL,
    categorie_client  VARCHAR(255),
    designation  VARCHAR(255) NOT NULL,
    logo bytea,
    logo_content_type  VARCHAR(255),
    adresse  VARCHAR(255) NOT NULL,
    code_postal  VARCHAR(255) NOT NULL,
    telephone  VARCHAR(255),
    fax  VARCHAR(255),
    email  VARCHAR(255),
    matricule_fiscale  VARCHAR(255) NOT NULL,
    registre_commerce  VARCHAR(255),
    date_creation date,
    cnss_employeur  VARCHAR(255),
    cnss_gerant  VARCHAR(255),
    fichier_patente bytea,
    fichier_patente_content_type  VARCHAR(255),
    secteur_activite_id bigint,
    activite_id bigint,
    activite_scondaire_id bigint,
    region_id bigint,
    ville_id bigint,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1000
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.impot_annuel (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
    calcule boolean DEFAULT false,
    tri_ordre integer NOT NULL
);

CREATE TABLE public.impot_annuel_detail (
    id bigint NOT NULL,
    impot_annuel_id bigint NOT NULL,
    impot_mensuel_detail_id bigint NOT NULL,
    coefficient integer DEFAULT 1,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

CREATE TABLE public.impot_mensuel (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
    parent boolean DEFAULT false,
    parent_impot_mensuel_id bigint,
    child boolean DEFAULT false,
    appliquer_report_montant boolean DEFAULT false,
    coefficient_montant integer DEFAULT 1
);

CREATE TABLE public.impot_mensuel_client (
    id bigint NOT NULL,
    applicable boolean,
    fiche_client_id bigint,
    impot_mensuel_id bigint,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);


CREATE TABLE public.impot_mensuel_detail (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    tri_ordre numeric(2,0) NOT NULL,
    valeur numeric(10,3),
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    impot_mensuel_id bigint NOT NULL,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
    type_valeur  VARCHAR(20) DEFAULT 'TAUX',
    valeur_modifiable boolean DEFAULT true
);

CREATE TABLE public.jhi_authority (
    name  VARCHAR(50) NOT NULL
);

CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal  VARCHAR(50) NOT NULL,
    event_date timestamp,
    event_type  VARCHAR(255)
);

CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name  VARCHAR(150) NOT NULL,
    value  VARCHAR(255)
);

CREATE TABLE public.jhi_user (
    id bigint NOT NULL,
    login  VARCHAR(50) NOT NULL,
    password_hash  VARCHAR(60) NOT NULL,
    first_name  VARCHAR(50),
    last_name  VARCHAR(50),
    email  VARCHAR(191),
    image_url  VARCHAR(256),
    activated boolean NOT NULL,
    lang_key  VARCHAR(6),
    activation_key  VARCHAR(20),
    reset_key  VARCHAR(20),
    created_by  VARCHAR(50) NOT NULL,
    created_date timestamp,
    reset_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name  VARCHAR(50) NOT NULL
);

CREATE TABLE public.quittance_mensuelle_impot (
    id bigint NOT NULL,
    annee integer NOT NULL,
    mois integer NOT NULL,
    numero_quittance  VARCHAR(255),
    date_paiement date,
    montant_total numeric(20,6),
    fiche_client_id bigint,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
    type_declaration  VARCHAR(255),
    statut  VARCHAR(50) DEFAULT 'BROUILLON',
    parent_quittance_id bigint
);

CREATE TABLE public.quittance_mensuelle_impot_detail (
    id bigint NOT NULL,
    impot_mensuel_id bigint,
    quittance_mensuelle_impot_id bigint,
    montant_total numeric(20,6),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
    parent boolean,
    child boolean,
    parent_quittance_mensuelle_impot_detail_id bigint,
    libelle  VARCHAR(255),
    code  VARCHAR(255),
    appliquer_report_montant boolean DEFAULT false,
    coefficient_montant integer DEFAULT 1,
    description  VARCHAR(255),
    montant_report numeric(20,6)
);


CREATE TABLE public.quittance_mensuelle_impot_sous_detail (
    id bigint NOT NULL,
    impot_mensuel_detail_id bigint,
    quittance_mensuelle_impot_detail_id bigint,
    montant_base numeric(20,6),
    montant_total numeric(20,6),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);


CREATE TABLE public.region (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

CREATE TABLE public.secteur_activite (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

CREATE TABLE public.ville (
    id bigint NOT NULL,
    code  VARCHAR(255),
    code_postal  VARCHAR(255),
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    region_id bigint,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);


INSERT INTO public.impot_mensuel VALUES (7055, 'TVA_COLLECTEE', 'TVA Collectée', 'TVA Collectée', 'admin', '2019-10-27 19:25:07.904', 'admin', '2019-11-16 17:08:21.885', false, 7054, true, false, 1);
INSERT INTO public.impot_mensuel VALUES (7054, 'TVA', 'TVA', 'Taxe Valeur Ajoutée', 'admin', '2019-10-27 19:22:59.165', 'admin', '2019-11-16 17:06:46.655', true, NULL, false, true, 1);
INSERT INTO public.impot_mensuel VALUES (7059, 'AUTRE', 'Autre', 'Autre', 'admin', '2019-10-27 19:34:55.859', 'admin', '2019-10-27 19:34:55.859', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7052, 'TFP', 'TFP', 'Taxe Formation Professionnelle', 'admin', '2019-10-27 19:20:45.74', 'admin', '2019-10-27 19:20:45.74', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7053, 'FOPROLOS', 'FOPROLOS', 'FOPROLOS', 'admin', '2019-10-27 19:21:50.239', 'admin', '2019-10-27 19:21:50.239', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7057, 'DROIT_DE_TIMPBRE', 'Droit de Timbre', 'Droit de Timbre', 'admin', '2019-10-27 19:32:08.412', 'admin', '2019-11-24 13:26:24.417', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7051, 'RS', 'RS', 'Retenue à la Source', 'admin', '2019-10-27 19:17:09.218', 'admin', '2019-11-24 13:39:00.974', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7056, 'TVA_DEDUCTIBLE', 'TVA Déductible', 'TVA Déductible', 'admin', '2019-10-27 19:28:54.658', 'admin', '2020-01-22 18:50:09.486', false, 7054, true, false, -1);
INSERT INTO public.impot_mensuel VALUES (7058, 'TCL', 'TCL', 'Taxe Collectée Locale', 'admin', '2019-10-27 19:34:20.955', 'admin', '2020-02-06 20:49:08.64', false, NULL, false, false, 1);

INSERT INTO public.impot_mensuel_detail VALUES (7113, 'IMMOBILISATION_7', 1, 7.000, 'Immobilisation', NULL, 7056, 'admin', '2019-10-27 19:28:54.659', 'admin', '2020-01-22 18:50:09.487', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7114, 'IMMOBILISATION_13', 2, 13.000, 'Immobilisation', NULL, 7056, 'admin', '2019-10-27 19:28:54.659', 'admin', '2020-01-22 18:50:09.488', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7115, 'IMMOBILISATION_19', 3, 19.000, 'Immobilisation', NULL, 7056, 'admin', '2019-10-27 19:28:54.66', 'admin', '2020-01-22 18:50:09.489', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7116, 'ACHAT_7', 4, 7.000, 'Achat', NULL, 7056, 'admin', '2019-10-27 19:28:54.66', 'admin', '2020-01-22 18:50:09.49', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7117, 'ACHAT_13', 5, 13.000, 'Achat', NULL, 7056, 'admin', '2019-10-27 19:28:54.661', 'admin', '2020-01-22 18:50:09.491', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7118, 'ACHAT_19', 6, 19.000, 'Achat', NULL, 7056, 'admin', '2019-10-27 19:28:54.662', 'admin', '2020-01-22 18:50:09.491', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7107, 'SALAIRE_BRUT_1', 1, 1.000, 'Salaire Brut', NULL, 7052, 'admin', '2019-10-27 19:20:45.742', 'admin', '2019-10-27 19:20:45.742', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7108, 'SALAIRE_BRUT_2', 2, 2.000, 'Salaire Brut', NULL, 7052, 'admin', '2019-10-27 19:20:45.743', 'admin', '2019-10-27 19:20:45.743', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7109, 'SUM_SALAIRE_BRUT', 1, 1.000, 'Sum Salaire Brut', NULL, 7053, 'admin', '2019-10-27 19:21:50.241', 'admin', '2019-10-27 19:21:50.241', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7119, 'RS_TVA', 7, 25.000, 'RS TVA', NULL, 7056, 'admin', '2019-10-27 19:28:54.662', 'admin', '2020-01-22 18:50:09.492', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7120, 'AUTRE1', 8, NULL, 'Autre 1', NULL, 7056, 'admin', '2019-10-27 19:30:01.744', 'admin', '2020-01-22 18:50:09.492', 'MONTANT', false);
INSERT INTO public.impot_mensuel_detail VALUES (7121, 'AUTRE2', 9, NULL, 'Autre', NULL, 7056, 'admin', '2019-10-27 19:30:01.744', 'admin', '2020-01-22 18:50:09.492', 'MONTANT', false);
INSERT INTO public.impot_mensuel_detail VALUES (7122, 'NB_FACTURES', 1, 0.600, 'Nb Factures', NULL, 7057, 'admin', '2019-10-27 19:32:08.413', 'admin', '2019-11-24 13:26:24.417', 'MONTANT', false);
INSERT INTO public.impot_mensuel_detail VALUES (7102, 'LOYER', 2, 15.000, 'Loyer', NULL, 7051, 'admin', '2019-10-27 19:19:16.569', 'admin', '2019-11-24 13:39:00.986', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7103, 'MONTANT_SUP_1000', 3, 1.500, 'Montant >1000', NULL, 7051, 'admin', '2019-10-27 19:19:16.57', 'admin', '2019-11-24 13:39:00.986', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7104, 'HONORAIRE_5', 4, 5.000, 'Honoraire', NULL, 7051, 'admin', '2019-10-27 19:19:16.571', 'admin', '2019-11-24 13:39:00.992', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (17002, 'HONORAIRE_15', 4, 15.000, 'Honoraire', NULL, 7051, 'admin', '2019-10-27 19:19:16.571', 'admin', '2019-11-24 13:39:00.992', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7106, 'AUTRE2', 6, NULL, 'Autre 2', NULL, 7051, 'admin', '2019-10-27 19:19:16.573', 'admin', '2019-11-24 13:39:00.993', 'MONTANT', false);
INSERT INTO public.impot_mensuel_detail VALUES (7101, 'SALAIRE_IMPOSABLE', 1, NULL, 'Salaire Imposable', NULL, 7051, 'admin', '2019-10-27 19:17:09.226', 'admin', '2019-11-24 13:39:00.994', 'MONTANT', false);
INSERT INTO public.impot_mensuel_detail VALUES (7105, 'AUTRE1', 5, NULL, 'Autre 1', NULL, 7051, 'admin', '2019-10-27 19:19:16.572', 'admin', '2019-11-24 13:39:00.994', 'MONTANT', false);
INSERT INTO public.impot_mensuel_detail VALUES (17001, 'VENTE_SUSPENSION_TVA', 3, NULL, 'Vente en suspension de TVA', NULL, 7058, 'admin', '2020-02-06 20:49:08.629', 'admin', '2020-02-06 20:49:08.629', 'MONTANT', false);
INSERT INTO public.impot_mensuel_detail VALUES (7123, 'CA_EXPORT', 1, 0.100, 'CA_Export', NULL, 7058, 'admin', '2019-10-27 19:34:20.956', 'admin', '2020-02-06 20:49:08.641', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7124, 'CA_LOCAL', 2, 0.200, 'CA Local', NULL, 7058, 'admin', '2019-10-27 19:34:20.956', 'admin', '2020-02-06 20:49:08.641', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7110, 'CA_HT_7', 1, 7.000, 'CA HT', NULL, 7055, 'admin', '2019-10-27 19:25:07.905', 'admin', '2019-11-16 17:08:21.885', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7111, 'CA_HT_13', 2, 13.000, 'CA HT', NULL, 7055, 'admin', '2019-10-27 19:25:07.906', 'admin', '2019-11-16 17:08:21.886', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7112, 'CA_HT_19', 3, 19.000, 'CA HT', NULL, 7055, 'admin', '2019-10-27 19:25:07.907', 'admin', '2019-11-16 17:08:21.886', 'TAUX', false);


INSERT INTO public.jhi_authority VALUES ('ROLE_ADMIN');
INSERT INTO public.jhi_authority VALUES ('ROLE_USER');

--

--

INSERT INTO public.jhi_user VALUES (1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', '', true, 'fr', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO public.jhi_user VALUES (2, 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', '', true, 'fr', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO public.jhi_user VALUES (4, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '', true, 'fr', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO public.jhi_user VALUES (3, 'admin', '$2a$10$yp3LsD4yvCEjQ5bNyKo9Ke4iWeuySnsDgekpQGm76jnJI2KD8kkGy', 'Administrator', 'Administrator', 'salim@godeals.pro', '', true, 'fr', NULL, NULL, 'system', NULL, NULL, 'anonymousUser', '2019-04-27 22:41:19.448');
INSERT INTO public.jhi_user VALUES (3701, 'salim', '$2a$10$tC6fk/XXVUggcTkBm.NazuRVRRiaOSPDe1OymEUN9FLRYW7qbWQ5C', NULL, NULL, 'salim.hamidi@gmail.com', NULL, true, 'fr', NULL, '75066859491761084391', 'anonymousUser', '2019-04-14 21:15:42.224', '2019-10-06 22:45:11.015', 'anonymousUser', '2019-10-06 22:45:11.065');


--

--

INSERT INTO public.jhi_user_authority VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.jhi_user_authority VALUES (1, 'ROLE_USER');
INSERT INTO public.jhi_user_authority VALUES (3, 'ROLE_ADMIN');
INSERT INTO public.jhi_user_authority VALUES (3, 'ROLE_USER');
INSERT INTO public.jhi_user_authority VALUES (4, 'ROLE_USER');
INSERT INTO public.jhi_user_authority VALUES (3701, 'ROLE_USER');


--

--

ALTER TABLE public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);


--

--

ALTER TABLE public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);


--

--

ALTER TABLE public.acompte_provisionnel
    ADD CONSTRAINT pk_acompte_provisionnel PRIMARY KEY (id);


--

--

ALTER TABLE public.activite
    ADD CONSTRAINT pk_activite PRIMARY KEY (id);


--

--

ALTER TABLE public.cnss
    ADD CONSTRAINT pk_cnss PRIMARY KEY (id);


--

--

ALTER TABLE public.declaration_annuelle
    ADD CONSTRAINT pk_declaration_annuel PRIMARY KEY (id);


--

--

ALTER TABLE public.declaration_annuelle_detail
    ADD CONSTRAINT pk_declaration_annuel_detail PRIMARY KEY (id);


--

--

ALTER TABLE public.declaration_employeur_annuelle
    ADD CONSTRAINT pk_declaration_employeur_annuelle PRIMARY KEY (id);


--

--

ALTER TABLE public.fiche_client
    ADD CONSTRAINT pk_fiche_client PRIMARY KEY (id);


--

--

ALTER TABLE public.impot_annuel
    ADD CONSTRAINT pk_impot_annuel PRIMARY KEY (id);


--

--

ALTER TABLE public.impot_mensuel
    ADD CONSTRAINT pk_impot_mensuel PRIMARY KEY (id);


--

--

ALTER TABLE public.impot_annuel_detail
    ADD CONSTRAINT pk_impot_mensuel_annuel PRIMARY KEY (id);


--

--

ALTER TABLE public.impot_mensuel_client
    ADD CONSTRAINT pk_impot_mensuel_client PRIMARY KEY (id);


--

--

ALTER TABLE public.impot_mensuel_detail
    ADD CONSTRAINT pk_impot_mensuel_detail PRIMARY KEY (id);


--

--

ALTER TABLE public.jhi_authority
    ADD CONSTRAINT pk_jhi_authority PRIMARY KEY (name);


--

--

ALTER TABLE public.jhi_persistent_audit_event
    ADD CONSTRAINT pk_jhi_persistent_audit_event PRIMARY KEY (event_id);


--

--

ALTER TABLE public.jhi_user
    ADD CONSTRAINT pk_jhi_user PRIMARY KEY (id);


--

--

ALTER TABLE public.quittance_mensuelle_impot
    ADD CONSTRAINT pk_quittance_mensuelle_impot PRIMARY KEY (id);


--

--

ALTER TABLE public.quittance_mensuelle_impot_detail
    ADD CONSTRAINT pk_quittance_mensuelle_impot_detail PRIMARY KEY (id);


--

--

ALTER TABLE public.quittance_mensuelle_impot_sous_detail
    ADD CONSTRAINT pk_quittance_mensuelle_impot_sous_detail PRIMARY KEY (id);


--

--

ALTER TABLE public.region
    ADD CONSTRAINT pk_region PRIMARY KEY (id);


--

--

ALTER TABLE public.secteur_activite
    ADD CONSTRAINT pk_secteur_activite PRIMARY KEY (id);


--

--

ALTER TABLE public.ville
    ADD CONSTRAINT pk_ville PRIMARY KEY (id);


--

--

ALTER TABLE public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);


--

--

ALTER TABLE public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);


--

--

CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event (principal, event_date);

CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data(event_id);


--

--

ALTER TABLE public.acompte_provisionnel
    ADD CONSTRAINT fk_acompte_provisionnel_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--

--

ALTER TABLE public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);


--

--

ALTER TABLE public.cnss
    ADD CONSTRAINT fk_cnss_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--

--

ALTER TABLE public.declaration_annuelle_detail
    ADD CONSTRAINT fk_declaration_annuelle_detail_declaration_annuelle_id FOREIGN KEY (declaration_annuelle_id) REFERENCES public.declaration_annuelle(id);


--

--

ALTER TABLE public.declaration_annuelle_detail
    ADD CONSTRAINT fk_declaration_annuelle_detail_impot_annuel_id FOREIGN KEY (impot_annuel_id) REFERENCES public.impot_annuel(id);


--

--

ALTER TABLE public.declaration_annuelle
    ADD CONSTRAINT fk_declaration_annuelle_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--

--

ALTER TABLE public.declaration_employeur_annuelle
    ADD CONSTRAINT fk_declaration_employeur_annuelle_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--

--

ALTER TABLE public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);


--

--

ALTER TABLE public.fiche_client
    ADD CONSTRAINT fk_fiche_client_activite_id FOREIGN KEY (activite_id) REFERENCES public.activite(id);


--

--

ALTER TABLE public.fiche_client
    ADD CONSTRAINT fk_fiche_client_activite_scondaire_id FOREIGN KEY (activite_scondaire_id) REFERENCES public.activite(id);


--

--

ALTER TABLE public.fiche_client
    ADD CONSTRAINT fk_fiche_client_region_id FOREIGN KEY (region_id) REFERENCES public.region(id);


--

--

ALTER TABLE public.fiche_client
    ADD CONSTRAINT fk_fiche_client_secteur_activite_id FOREIGN KEY (secteur_activite_id) REFERENCES public.secteur_activite(id);


--

--

ALTER TABLE public.fiche_client
    ADD CONSTRAINT fk_fiche_client_ville_id FOREIGN KEY (ville_id) REFERENCES public.ville(id);


--

--

ALTER TABLE public.impot_annuel_detail
    ADD CONSTRAINT fk_impot_annuel_impot_mensuel_detail_id FOREIGN KEY (impot_mensuel_detail_id) REFERENCES public.impot_mensuel_detail(id);


--

--

ALTER TABLE public.impot_mensuel_client
    ADD CONSTRAINT fk_impot_mensuel_client_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--

--

ALTER TABLE public.impot_mensuel_client
    ADD CONSTRAINT fk_impot_mensuel_client_impot_mensuel_id FOREIGN KEY (impot_mensuel_id) REFERENCES public.impot_mensuel(id);


--

--

ALTER TABLE public.impot_mensuel_detail
    ADD CONSTRAINT fk_impot_mensuel_detail_impot_mensuel_id FOREIGN KEY (impot_mensuel_id) REFERENCES public.impot_mensuel(id);


--

--

ALTER TABLE public.quittance_mensuelle_impot_detail
    ADD CONSTRAINT fk_impot_mensuel_quittance_mensuelle_impot FOREIGN KEY (impot_mensuel_id) REFERENCES public.impot_mensuel(id);


--

--

ALTER TABLE public.impot_mensuel
    ADD CONSTRAINT fk_parent__impot_mensuel_impot_mensuel_id FOREIGN KEY (parent_impot_mensuel_id) REFERENCES public.impot_mensuel(id);


--

--

ALTER TABLE public.quittance_mensuelle_impot
    ADD CONSTRAINT fk_parent_quittance_id FOREIGN KEY (parent_quittance_id) REFERENCES public.quittance_mensuelle_impot(id);


--

--

ALTER TABLE public.quittance_mensuelle_impot_detail
    ADD CONSTRAINT fk_parent_quittance_mensuelle_impot_detail FOREIGN KEY (parent_quittance_mensuelle_impot_detail_id) REFERENCES public.quittance_mensuelle_impot_detail(id);


--

--

ALTER TABLE public.quittance_mensuelle_impot
    ADD CONSTRAINT fk_quittance_mensuelle_impot_fiche_client_id FOREIGN KEY (fiche_client_id) REFERENCES public.fiche_client(id);


--

--

ALTER TABLE public.quittance_mensuelle_impot_detail
    ADD CONSTRAINT fk_quittance_mensuelle_impot_quittance_mensuelle_impot_detail FOREIGN KEY (quittance_mensuelle_impot_id) REFERENCES public.quittance_mensuelle_impot(id);


--

--

ALTER TABLE public.quittance_mensuelle_impot_sous_detail
    ADD CONSTRAINT fk_quittance_mensuelle_impot_sous_detail_impot_mensuel_detail FOREIGN KEY (impot_mensuel_detail_id) REFERENCES public.impot_mensuel_detail(id);


--

--

ALTER TABLE public.quittance_mensuelle_impot_sous_detail
    ADD CONSTRAINT fk_quittance_mensuelle_impot_sous_detail_quittance_mensuelle_im FOREIGN KEY (quittance_mensuelle_impot_detail_id) REFERENCES public.quittance_mensuelle_impot_detail(id);


--

--

ALTER TABLE public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);


--

--

ALTER TABLE public.ville
    ADD CONSTRAINT fk_ville_region_id FOREIGN KEY (region_id) REFERENCES public.region(id);


--
-- PostgreSQL database dump complete
--

