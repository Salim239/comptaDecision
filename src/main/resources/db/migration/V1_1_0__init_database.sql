create schema if not exists public;

CREATE TABLE public.acompte_provisionnel (
    id bigint NOT NULL,
    annee integer NOT NULL,
    numero integer NOT NULL,
    jhi_date date,
    numero_quittance  VARCHAR(255),
    montant_base numeric(10,2),
    montant_acompte_provisionnel numeric(10,2),
    montant_report_anterieur numeric(10,2),
    montant_retenue_source numeric(10,2),
    montant_net numeric(10,2),
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
    montant_salaire_brut_normal numeric(10,2),
    montant_salaire_brut_karama numeric(10,2),
    montant_salaire_brut_autre numeric(10,2),
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
    montant numeric(20,6),
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
    code  VARCHAR(255) NOT NULL,
    code_postal  VARCHAR(255),
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    region_id bigint,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

INSERT INTO public.acompte_provisionnel VALUES (18451, 2020, 1, NULL, NULL, 100.00, 200.00, 1478.00, 1414.25, 147.20, 14302, 'admin', '2020-02-21 21:56:03.894', 'admin', '2020-02-21 21:56:03.894');

INSERT INTO public.activite VALUES (1005, 'QUINCAILLERIE', 'Quincaillerie', NULL, 'admin', NULL, NULL, NULL);
INSERT INTO public.activite VALUES (1006, 'VENTE_DETAIL_PRODUIT_ALIMENTAIRE', 'Vente détail produit alimentaire', NULL, 'admin', NULL, NULL, NULL);
INSERT INTO public.activite VALUES (1007, 'ARCHITECTE', 'Architecte', NULL, 'admin', NULL, NULL, NULL);
INSERT INTO public.activite VALUES (1008, 'TOPOGRAPHIE', 'Topographie', NULL, 'admin', NULL, NULL, NULL);

INSERT INTO public.cnss VALUES (18351, 2020, 1, NULL, NULL, 10.00, 20.00, 30.00, 60.00, NULL, 14302, 'admin', '2020-02-21 17:41:16.05', 'admin', '2020-02-21 17:41:16.05', '0');

INSERT INTO public.declaration_annuelle VALUES (18201, 2020, NULL, NULL, 40.000000, 14302, 'DECLARATION_INITIALE', NULL, 'admin', '2020-02-21 10:39:10.711', 'admin', '2020-02-21 10:39:10.711');

INSERT INTO public.declaration_annuelle_detail VALUES (18251, 'CA_HT', 'CA HT', '', 1, true, 10.000000, 0.000000, 1, 'admin', '2020-02-21 10:39:10.716', 'admin', '2020-02-21 10:39:10.716', 18201);
INSERT INTO public.declaration_annuelle_detail VALUES (18252, 'ACHAT_HT', 'Achat HT', NULL, 2, true, 20.000000, 0.000000, 2, 'admin', '2020-02-21 10:39:10.718', 'admin', '2020-02-21 10:39:10.718', 18201);
INSERT INTO public.declaration_annuelle_detail VALUES (18253, 'SALAIRE_BRUT', 'Salaire Brut', NULL, 3, true, 10.000000, 0.000000, 3, 'admin', '2020-02-21 10:39:10.719', 'admin', '2020-02-21 10:39:10.719', 18201);
INSERT INTO public.declaration_annuelle_detail VALUES (18254, 'LOCATION', 'Location', NULL, 4, true, 0.000000, 1547.255000, 4, 'admin', '2020-02-21 10:39:10.72', 'admin', '2020-02-21 10:39:10.72', 18201);
INSERT INTO public.declaration_annuelle_detail VALUES (18255, 'HONORAIRE', 'Honoraire', NULL, 5, true, 0.000000, 0.000000, 5, 'admin', '2020-02-21 10:39:10.72', 'admin', '2020-02-21 10:39:10.72', 18201);
INSERT INTO public.declaration_annuelle_detail VALUES (18256, 'CA_TTC', 'CA TTC', NULL, 6, true, 0.000000, 0.000000, 6, 'admin', '2020-02-21 10:39:10.721', 'admin', '2020-02-21 10:39:10.721', 18201);
INSERT INTO public.declaration_annuelle_detail VALUES (18257, 'SI', 'Sock Initiale', NULL, 6, false, 0.000000, NULL, 7, 'admin', '2020-02-21 10:39:10.721', 'admin', '2020-02-21 10:39:10.721', 18201);
INSERT INTO public.declaration_annuelle_detail VALUES (18258, 'SF', 'Stock Final', NULL, 7, false, 0.000000, NULL, 8, 'admin', '2020-02-21 10:39:10.721', 'admin', '2020-02-21 10:39:10.721', 18201);
INSERT INTO public.declaration_annuelle_detail VALUES (18259, 'BALANCE_COMPTE', 'Résultat Comptable', NULL, 8, false, 0.000000, NULL, 9, 'admin', '2020-02-21 10:39:10.722', 'admin', '2020-02-21 10:39:10.722', 18201);
INSERT INTO public.declaration_annuelle_detail VALUES (18260, 'BALANCE_FISCALE', 'Résultat Fiscale', NULL, 9, false, 0.000000, NULL, 10, 'admin', '2020-02-21 10:39:10.723', 'admin', '2020-02-21 10:39:10.723', 18201);

INSERT INTO public.declaration_employeur_annuelle VALUES (18501, 2020, 100.00, 0.00, 147.25, 14141.00, 14.00, 144.00, 150.00, 147.00, 147.25, 12.26, 147.20, 147.02, 14302, 'admin', '2020-02-21 22:44:22.275', 'admin', '2020-02-21 22:44:22.275');

INSERT INTO public.fiche_client VALUES (14302, 'PERSONNE_MORALE', 'Grow up', NULL, NULL, 'Av. Hédi Chaker', '5170', NULL, NULL, NULL, '1353270P', NULL, '2013-05-08', NULL, NULL, NULL, NULL, 1051, 1005, NULL, NULL, NULL, 'admin', '2020-01-22 19:49:42.522', 'admin', '2020-01-22 19:51:06.001');


INSERT INTO public.impot_annuel VALUES (4, 'LOCATION', 'Location', NULL, 'admin', '2020-02-06 20:04:48.187', NULL, NULL, true, 4);
INSERT INTO public.impot_annuel VALUES (6, 'CA_TTC', 'CA TTC', NULL, 'admin', '2020-02-06 20:04:53.978', NULL, NULL, true, 6);
INSERT INTO public.impot_annuel VALUES (2, 'ACHAT_HT', 'Achat HT', NULL, 'admin', '2020-02-06 20:04:43.001', NULL, NULL, true, 2);
INSERT INTO public.impot_annuel VALUES (1, 'CA_HT', 'CA HT', '', 'admin', '2020-02-06 20:04:33.389', NULL, NULL, true, 1);
INSERT INTO public.impot_annuel VALUES (3, 'SALAIRE_BRUT', 'Salaire Brut', NULL, 'admin', '2020-02-06 20:04:45.647', NULL, NULL, true, 3);
INSERT INTO public.impot_annuel VALUES (5, 'HONORAIRE', 'Honoraire', NULL, 'admin', '2020-02-06 20:04:50.634', NULL, NULL, true, 5);
INSERT INTO public.impot_annuel VALUES (7, 'SI', 'Sock Initiale', NULL, 'admin', '2020-02-06 20:06:14.742', NULL, NULL, false, 6);
INSERT INTO public.impot_annuel VALUES (8, 'SF', 'Stock Final', NULL, 'admin', '2020-02-06 20:06:47.351', NULL, NULL, false, 7);
INSERT INTO public.impot_annuel VALUES (9, 'BALANCE_COMPTE', 'Résultat Comptable', NULL, 'admin', '2020-02-06 20:08:24.431', NULL, NULL, false, 8);
INSERT INTO public.impot_annuel VALUES (10, 'BALANCE_FISCALE', 'Résultat Fiscale', NULL, 'admin', '2020-02-06 20:08:55.773', NULL, NULL, false, 9);


INSERT INTO public.impot_annuel_detail VALUES (1, 1, 7111, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (2, 1, 7112, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (3, 1, 7110, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (4, 2, 7117, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (5, 2, 7118, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (6, 2, 7116, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (7, 3, 7107, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (8, 3, 7108, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (11, 5, 7104, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (12, 6, 17001, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (13, 6, 17002, 1, 'admin', NULL, NULL, NULL);
INSERT INTO public.impot_annuel_detail VALUES (9, 4, 7102, 1, 'admin', NULL, NULL, NULL);


INSERT INTO public.impot_mensuel VALUES (7055, 'TVA_COLLECTEE', 'TVA Collectée', 'TVA Collectée', 'admin', '2019-10-27 19:25:07.904', 'admin', '2019-11-16 17:08:21.885', false, 7054, true, false, 1);
INSERT INTO public.impot_mensuel VALUES (7054, 'TVA', 'TVA', 'Taxe Valeur Ajoutée', 'admin', '2019-10-27 19:22:59.165', 'admin', '2019-11-16 17:06:46.655', true, NULL, false, true, 1);
INSERT INTO public.impot_mensuel VALUES (7059, 'AUTRE', 'Autre', 'Autre', 'admin', '2019-10-27 19:34:55.859', 'admin', '2019-10-27 19:34:55.859', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7052, 'TFP', 'TFP', 'Taxe Formation Professionnelle', 'admin', '2019-10-27 19:20:45.74', 'admin', '2019-10-27 19:20:45.74', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7053, 'FOPROLOS', 'FOPROLOS', 'FOPROLOS', 'admin', '2019-10-27 19:21:50.239', 'admin', '2019-10-27 19:21:50.239', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7057, 'DROIT_DE_TIMPBRE', 'Droit de Timbre', 'Droit de Timbre', 'admin', '2019-10-27 19:32:08.412', 'admin', '2019-11-24 13:26:24.417', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7051, 'RS', 'RS', 'Retenue à la Source', 'admin', '2019-10-27 19:17:09.218', 'admin', '2019-11-24 13:39:00.974', false, NULL, false, false, 1);
INSERT INTO public.impot_mensuel VALUES (7056, 'TVA_DEDUCTIBLE', 'TVA Déductible', 'TVA Déductible', 'admin', '2019-10-27 19:28:54.658', 'admin', '2020-01-22 18:50:09.486', false, 7054, true, false, -1);
INSERT INTO public.impot_mensuel VALUES (7058, 'TCL', 'TCL', 'Taxe Collectée Locale', 'admin', '2019-10-27 19:34:20.955', 'admin', '2020-02-06 20:49:08.64', false, NULL, false, false, 1);

INSERT INTO public.impot_mensuel_client VALUES (14358, true, 14302, 7054, 'admin', '2020-01-22 19:49:42.523', 'admin', '2020-01-22 19:51:06.002');
INSERT INTO public.impot_mensuel_client VALUES (14359, true, 14302, 7058, 'admin', '2020-01-22 19:49:42.524', 'admin', '2020-01-22 19:51:06.004');
INSERT INTO public.impot_mensuel_client VALUES (14360, false, 14302, 7059, 'admin', '2020-01-22 19:49:42.526', 'admin', '2020-01-22 19:51:06.005');
INSERT INTO public.impot_mensuel_client VALUES (14361, true, 14302, 7052, 'admin', '2020-01-22 19:49:42.527', 'admin', '2020-01-22 19:51:06.007');
INSERT INTO public.impot_mensuel_client VALUES (14362, true, 14302, 7053, 'admin', '2020-01-22 19:49:42.529', 'admin', '2020-01-22 19:51:06.009');
INSERT INTO public.impot_mensuel_client VALUES (14363, true, 14302, 7057, 'admin', '2020-01-22 19:49:42.53', 'admin', '2020-01-22 19:51:06.01');
INSERT INTO public.impot_mensuel_client VALUES (14364, true, 14302, 7051, 'admin', '2020-01-22 19:49:42.532', 'admin', '2020-01-22 19:51:06.01');


INSERT INTO public.impot_mensuel_detail VALUES (7113, 'IMMOBILISATION_7', 1, 7.000, 'Immobilisation', NULL, 7056, 'admin', '2019-10-27 19:28:54.659', 'admin', '2020-01-22 18:50:09.487', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7114, 'IMMOBILISATION_13', 2, 13.000, 'Immobilisation', NULL, 7056, 'admin', '2019-10-27 19:28:54.659', 'admin', '2020-01-22 18:50:09.488', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7115, 'IMMOBILISATION_19', 3, 19.000, 'Immobilisation', NULL, 7056, 'admin', '2019-10-27 19:28:54.66', 'admin', '2020-01-22 18:50:09.489', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7116, 'ACHAT_7', 4, 7.000, 'Achat', NULL, 7056, 'admin', '2019-10-27 19:28:54.66', 'admin', '2020-01-22 18:50:09.49', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7117, 'ACHAT_13', 5, 13.000, 'Achat', NULL, 7056, 'admin', '2019-10-27 19:28:54.661', 'admin', '2020-01-22 18:50:09.491', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7118, 'ACHAT_19', 6, 19.000, 'Achat', NULL, 7056, 'admin', '2019-10-27 19:28:54.662', 'admin', '2020-01-22 18:50:09.491', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7107, 'SALAIRE_BRUT_1', 1, 1.000, 'Salaire Brut', NULL, 7052, 'admin', '2019-10-27 19:20:45.742', 'admin', '2019-10-27 19:20:45.742', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7108, 'SALAIRE_BRUT_2', 2, 2.000, 'Salaire Brut', NULL, 7052, 'admin', '2019-10-27 19:20:45.743', 'admin', '2019-10-27 19:20:45.743', 'TAUX', false);
INSERT INTO public.impot_mensuel_detail VALUES (7109, 'SOM_SALAIRE8BRUT', 1, 1.000, 'Sum Salaire Brut', NULL, 7053, 'admin', '2019-10-27 19:21:50.241', 'admin', '2019-10-27 19:21:50.241', 'TAUX', false);
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


INSERT INTO public.jhi_persistent_audit_event VALUES (951, 'admin', '2019-04-03 19:32:25.885', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1051, 'admin', '2019-04-03 22:03:30.576', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1052, 'admin', '2019-04-03 22:14:40.321', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1053, 'admin', '2019-04-03 22:14:55.122', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1151, 'admin', '2019-04-03 22:24:02.955', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1501, 'admin', '2019-04-03 23:20:07.482', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1701, 'user', '2019-04-08 21:27:42.542', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1702, 'user', '2019-04-08 21:42:32.731', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1752, 'user', '2019-04-09 22:37:43.694', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1751, 'user', '2019-04-09 22:37:43.694', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1801, 'admin', '2019-04-09 22:51:31.021', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1802, 'admin', '2019-04-09 22:51:32.753', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1901, 'admin', '2019-04-09 23:12:28.12', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1951, 'admin', '2019-04-11 21:56:31.325', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (1952, 'admin', '2019-04-11 21:56:32.242', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2001, 'admin', '2019-04-11 23:25:23.849', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2051, 'admin', '2019-04-11 23:28:02.504', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2201, 'admin', '2019-04-11 23:55:56.282', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2251, 'admin', '2019-04-12 00:01:58.043', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2252, 'admin', '2019-04-12 00:01:58.5', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2253, 'admin', '2019-04-12 00:02:04.748', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2254, 'admin', '2019-04-12 00:02:04.783', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2255, 'admin', '2019-04-12 00:02:04.797', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2256, 'admin', '2019-04-12 00:04:30.138', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2301, 'admin', '2019-04-13 17:43:46.842', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2302, 'admin', '2019-04-13 17:45:12.127', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2303, 'admin', '2019-04-13 17:53:19.183', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2351, 'admin', '2019-04-13 18:00:47.965', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2352, 'admin', '2019-04-13 18:00:49.255', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2401, 'admin', '2019-04-13 19:50:38.968', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2451, 'admin', '2019-04-14 01:03:52.956', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (2601, 'admin', '2019-04-14 01:37:10.025', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3351, 'admin', '2019-04-14 15:21:06.295', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3352, 'user', '2019-04-14 15:21:24.573', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3401, 'admin', '2019-04-14 17:28:43.402', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3402, 'admin', '2019-04-14 19:19:03.733', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3403, 'adminadmin', '2019-04-14 19:19:51.641', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (3404, 'admin', '2019-04-14 19:20:03.286', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3451, 'admin', '2019-04-14 19:28:35.52', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3452, 'user', '2019-04-14 19:43:29.649', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3751, 'salim', '2019-04-14 21:17:37.121', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3752, 'admin', '2019-04-14 21:17:37.44', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3753, 'user', '2019-04-14 21:18:42.049', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3951, 'admin', '2019-04-14 22:31:42.288', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (3952, 'admin', '2019-04-14 22:31:48.471', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (3953, 'admin', '2019-04-14 22:31:50.261', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (3954, 'admin', '2019-04-14 22:31:51.829', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (3955, 'user', '2019-04-14 22:31:57.518', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (3956, 'admin', '2019-04-14 22:32:26.136', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (3957, 'admin', '2019-04-14 22:32:39.388', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4001, 'admin', '2019-04-27 22:12:48.923', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4002, 'admin', '2019-04-27 22:12:55.004', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4003, 'admin', '2019-04-27 22:13:03.777', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4004, 'admin', '2019-04-27 22:13:06.787', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4005, 'user', '2019-04-27 22:13:14.047', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4006, 'user', '2019-04-27 22:13:15.673', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4007, 'user', '2019-04-27 22:15:11.251', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4008, 'user', '2019-04-27 22:15:12.506', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4051, 'admin', '2019-04-27 22:20:29.727', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4052, 'user', '2019-04-27 22:20:48.462', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4053, 'admin', '2019-04-27 22:21:08.652', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4054, 'admin', '2019-04-27 22:21:21.006', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4055, 'admin', '2019-04-27 22:21:26.486', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4056, 'admin', '2019-04-27 22:32:47.986', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4057, 'admin', '2019-04-27 22:32:48.627', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4058, 'admin', '2019-04-27 22:32:53.645', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4059, 'admin', '2019-04-27 22:32:54.578', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4060, 'admin', '2019-04-27 22:32:55.128', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4061, 'admin', '2019-04-27 22:32:55.617', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4062, 'admin', '2019-04-27 22:32:55.801', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4063, 'admin', '2019-04-27 22:32:55.991', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4064, 'admin', '2019-04-27 22:32:56.433', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4065, 'admin', '2019-04-27 22:32:56.613', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4066, 'admin', '2019-04-27 22:32:56.618', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4067, 'admin', '2019-04-27 22:32:56.621', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4068, 'admin', '2019-04-27 22:32:56.628', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4069, 'admin', '2019-04-27 22:32:57.577', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4070, 'salim', '2019-04-27 22:33:09.714', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4071, 'admin', '2019-04-27 22:33:35.209', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4072, 'admin', '2019-04-27 22:33:35.566', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4073, 'admin', '2019-04-27 22:33:40.895', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4074, 'admin', '2019-04-27 22:33:41.56', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4075, 'admin', '2019-04-27 22:33:46.734', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4076, 'admin', '2019-04-27 22:33:47.572', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4077, 'admin', '2019-04-27 22:33:47.828', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4078, 'admin', '2019-04-27 22:33:48.569', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4079, 'admin', '2019-04-27 22:35:12.639', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4080, 'admin', '2019-04-27 22:35:16.107', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4081, 'admin', '2019-04-27 22:35:17.276', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4082, 'admin', '2019-04-27 22:35:17.901', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4083, 'salim', '2019-04-27 22:36:01.759', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4084, 'salim', '2019-04-27 22:36:04', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4085, 'salim', '2019-04-27 22:36:11.886', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4086, 'salim', '2019-04-27 22:36:12.992', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4087, 'user', '2019-04-27 22:36:45.008', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4088, 'salim', '2019-04-27 22:37:22.515', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4089, 'salim', '2019-04-27 22:38:32.553', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4090, 'salim', '2019-04-27 22:40:19.577', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4091, 'admin', '2019-04-27 22:40:19.587', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4092, 'salim', '2019-04-27 22:40:19.588', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4093, 'salimadmin', '2019-04-27 22:41:27.314', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4094, 'admin', '2019-04-27 22:41:32.121', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4101, 'admin', '2019-04-28 06:11:08.488', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4102, 'admin', '2019-04-28 06:11:17.232', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4103, 'admin', '2019-04-28 06:13:19.614', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4104, 'admin', '2019-04-28 06:13:19.629', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4151, 'admin', '2019-04-28 06:25:51.488', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4301, 'adminadmin', '2019-09-22 22:57:41.305', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4302, 'admin', '2019-09-22 22:57:46.599', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4303, 'admin', '2019-09-22 22:57:48.719', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4304, 'admin', '2019-09-22 22:57:53.235', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4305, 'admin', '2019-09-22 22:57:57.27', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4306, 'user', '2019-09-22 22:58:03.037', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4307, 'salim', '2019-09-22 22:58:21.253', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4308, 'admin', '2019-09-22 22:59:35.174', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (4309, 'admin', '2019-09-22 22:59:40.598', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4351, 'admin', '2019-09-23 22:20:54.051', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4401, 'admin', '2019-09-24 20:57:57.541', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4451, 'admin', '2019-09-24 22:37:03.773', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4501, 'admin', '2019-09-24 22:45:31.418', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4651, 'admin', '2019-09-25 20:18:37.165', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (4652, 'admin', '2019-09-25 20:20:11.428', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5001, 'admin', '2019-09-25 22:36:54.27', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5151, 'admin', '2019-09-26 19:53:57.597', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5201, 'admin', '2019-09-26 20:19:55.097', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (5202, 'admin', '2019-09-26 20:20:00.997', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (5203, 'admin', '2019-09-26 20:20:15.02', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5252, 'admin', '2019-09-27 20:59:23.099', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5251, 'admin', '2019-09-27 20:59:23.096', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5301, 'admin', '2019-09-29 20:48:43.393', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5401, 'admin', '2019-09-30 21:02:03.615', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5501, 'admin', '2019-09-30 23:44:01.066', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (5502, 'admin', '2019-09-30 23:44:11.825', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5503, 'admin', '2019-09-30 23:52:43.995', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5601, 'admin', '2019-10-01 21:00:22.108', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5602, 'admin', '2019-10-01 21:01:19.761', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5751, 'admin', '2019-10-01 21:36:24.704', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5801, 'admin', '2019-10-04 20:05:35.326', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (5802, 'admin', '2019-10-04 20:24:03.464', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6001, 'admin', '2019-10-05 17:31:28.86', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6002, 'admin', '2019-10-05 17:31:29.48', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6003, 'admin', '2019-10-05 17:31:30.642', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6051, 'admin', '2019-10-05 18:51:11.389', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6052, 'admin', '2019-10-05 18:51:11.853', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6101, 'admin', '2019-10-05 19:16:37.419', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6251, 'admin', '2019-10-05 22:25:28.313', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6301, 'admin', '2019-10-05 22:31:49.66', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6351, 'admin', '2019-10-06 22:43:27.434', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (6352, 'admin', '2019-10-06 22:43:37.253', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (6353, 'admin', '2019-10-06 22:43:47.533', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (6354, 'admin', '2019-10-06 22:44:15.398', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (6355, 'admin', '2019-10-06 22:44:45.897', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (6356, 'admin', '2019-10-06 22:46:53.031', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (6357, 'admin', '2019-10-06 22:47:16.877', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6401, 'admin', '2019-10-08 19:56:51.678', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (6402, 'admin', '2019-10-08 19:56:57.637', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (6403, 'admin', '2019-10-08 19:57:19.514', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6451, 'admin', '2019-10-22 20:31:41.329', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6501, 'admin', '2019-10-22 21:58:37.389', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6551, 'admin', '2019-10-22 22:31:16.704', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6701, 'admin', '2019-10-26 17:13:03.697', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6751, 'admin', '2019-10-26 17:30:13.035', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6801, 'admin', '2019-10-26 19:59:29.292', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6802, 'admin', '2019-10-26 20:01:00.654', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6851, 'admin', '2019-10-26 21:45:23.618', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6901, 'admin', '2019-10-26 23:34:07.731', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6951, 'admin', '2019-10-26 23:56:33.043', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (6952, 'admin', '2019-10-26 23:56:33.043', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7001, 'admin', '2019-10-27 19:10:43.931', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7201, 'admin', '2019-10-27 19:46:12.072', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7351, 'admin', '2019-10-27 21:01:42.16', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7401, 'admin', '2019-11-02 11:07:07.065', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7402, 'admin', '2019-11-02 11:25:36.166', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7451, 'admin', '2019-11-02 13:23:05.775', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7501, 'admin', '2019-11-03 22:37:36.276', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7551, 'admin', '2019-11-04 20:45:09.51', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7601, 'admin', '2019-11-04 21:08:11.871', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7651, 'admin', '2019-11-10 17:10:17.134', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7652, 'admin', '2019-11-10 17:36:45.726', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7653, 'admin', '2019-11-10 19:09:48.617', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7701, 'admin', '2019-11-10 19:51:30.607', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7751, 'admin', '2019-11-10 20:32:21.358', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7752, 'admin', '2019-11-10 20:48:13.303', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (7951, 'admin', '2019-11-10 21:40:55.396', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (8151, 'admin', '2019-11-11 11:24:11.114', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (8152, 'admin', '2019-11-11 11:25:23.309', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (8202, 'admin', '2019-11-11 14:44:39.916', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (8201, 'admin', '2019-11-11 14:44:39.921', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (8551, 'admin', '2019-11-16 14:51:11.381', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (8751, 'admin', '2019-11-16 22:05:50.417', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (9101, 'admin', '2019-11-24 13:25:01.366', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (9151, 'admin', '2019-11-24 13:37:39.902', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (9201, 'admin', '2019-12-24 20:40:30.88', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (9202, 'admin', '2019-12-24 20:45:50.26', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (9951, 'admin', '2019-12-25 13:39:16.192', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (9952, 'admin', '2019-12-25 13:40:12.665', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (9953, 'admin', '2019-12-25 13:40:49.952', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (10351, 'admin', '2019-12-25 17:15:05.403', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (11951, 'admin', '2019-12-26 09:46:47.303', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (11952, 'admin', '2019-12-26 10:07:05.48', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12251, 'admin', '2019-12-26 20:00:04.608', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12301, 'admin', '2019-12-27 18:51:09.216', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12351, 'admin', '2019-12-27 19:46:16.63', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12401, 'admin', '2019-12-27 20:00:15.477', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12601, 'admin', '2019-12-28 09:34:44.913', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12651, 'admin', '2020-01-19 10:00:35.249', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (12652, 'admin', '2020-01-19 10:00:39.798', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12701, 'admin', '2020-01-19 14:40:23.955', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12751, 'admin', '2020-01-19 19:05:13.228', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (12752, 'admin', '2020-01-19 19:05:35.982', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12753, 'admin', '2020-01-19 20:17:11.281', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (12951, 'admin', '2020-01-20 08:38:04.456', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (13351, 'admin', '2020-01-20 16:46:09.402', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (13651, 'admin', '2020-01-21 10:15:23.264', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (13701, 'admin', '2020-01-22 09:50:49.681', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (13702, 'admin', '2020-01-22 13:50:32.095', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (14401, 'admin', '2020-01-22 19:48:07.287', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (14651, 'admin', '2020-01-23 09:33:36.966', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (14652, 'adminaaa', '2020-01-23 09:48:31.646', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (14653, 'admin', '2020-01-23 10:15:21.791', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (14701, 'admin', '2020-01-23 11:12:01.251', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (14901, 'admin', '2020-01-23 14:58:43.793', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (15101, 'admin', '2020-01-24 15:40:00.413', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (15151, 'admin', '2020-01-24 22:00:01.857', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (15152, 'admin', '2020-01-24 22:00:02.23', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (15153, 'admin', '2020-01-24 22:07:46.493', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (15154, 'admin', '2020-01-24 22:11:12.908', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (15155, 'admin', '2020-01-24 22:15:42.169', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (15351, 'admin', '2020-01-25 10:23:36.657', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (15851, 'admin', '2020-01-31 20:42:51.701', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (15901, 'admin', '2020-02-04 17:25:07.094', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (16001, 'admin', '2020-02-04 21:49:26.061', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (16051, 'admin', '2020-02-05 11:35:33.965', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (16052, 'admin', '2020-02-05 11:36:48.285', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (16301, 'admin', '2020-02-05 15:17:51.18', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (16951, 'admin', '2020-02-06 20:24:07.377', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (16952, 'admin', '2020-02-06 20:24:29.479', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (17201, 'admin', '2020-02-07 09:54:20.928', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17202, 'admin', '2020-02-07 09:54:45.698', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (17203, 'admin', '2020-02-07 10:18:19.548', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (17251, 'admin', '2020-02-07 16:37:11.151', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (17301, 'admin', '2020-02-08 17:58:22.419', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (17351, 'admin', '2020-02-10 10:27:44.744', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17352, 'admin', '2020-02-10 10:27:52.61', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (17501, 'admin', '2020-02-10 12:43:44.554', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (17751, 'aaa', '2020-02-11 11:21:03.294', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17752, 'aaa', '2020-02-11 11:21:57.787', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17753, 'aaa', '2020-02-11 11:22:05.381', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17754, 'aaa', '2020-02-11 11:22:37.827', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17755, 'ccc', '2020-02-11 11:24:12.823', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17756, 'ccc', '2020-02-11 11:25:08.538', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17757, 'ccc', '2020-02-11 11:25:38.078', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17758, 'ccc', '2020-02-11 11:27:44.799', 'AUTHENTICATION_FAILURE');
INSERT INTO public.jhi_persistent_audit_event VALUES (17802, 'admin', '2020-02-11 16:43:24.762', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (17801, 'admin', '2020-02-11 16:43:24.762', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (17851, 'admin', '2020-02-12 08:15:03.702', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (18151, 'admin', '2020-02-21 10:33:39.265', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (18153, 'admin', '2020-02-21 10:33:39.258', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (18152, 'admin', '2020-02-21 10:33:39.266', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (18154, 'admin', '2020-02-21 10:33:39.258', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (18301, 'admin', '2020-02-21 11:06:35.705', 'AUTHENTICATION_SUCCESS');
INSERT INTO public.jhi_persistent_audit_event VALUES (18401, 'admin', '2020-02-21 20:41:51.671', 'AUTHENTICATION_SUCCESS');


--

--

INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3403, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3403, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3951, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3951, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3952, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3952, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3953, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3953, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3954, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3954, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3956, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (3956, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4001, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4001, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4002, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4002, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4003, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4003, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4004, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4004, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4005, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4005, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4006, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4006, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4007, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4007, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4008, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4008, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4051, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4051, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4053, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4053, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4054, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4054, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4055, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4055, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4056, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4056, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4057, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4057, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4058, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4058, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4059, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4059, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4060, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4060, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4061, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4061, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4062, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4062, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4063, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4063, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4064, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4064, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4065, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4065, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4066, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4066, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4067, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4067, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4068, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4068, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4069, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4069, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4071, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4071, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4072, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4072, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4073, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4073, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4074, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4074, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4075, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4075, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4076, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4076, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4077, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4077, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4078, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4078, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4079, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4079, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4080, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4080, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4081, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4081, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4082, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4082, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4083, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4083, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4084, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4084, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4085, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4085, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4086, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4086, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4088, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4088, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4093, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4093, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4101, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4101, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4301, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4301, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4302, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4302, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4303, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4303, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4304, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4304, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4305, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4305, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4308, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (4308, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (5201, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (5201, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (5202, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (5202, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (5501, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (5501, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6351, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6351, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6352, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6352, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6353, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6353, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6354, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6354, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6355, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6355, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6356, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6356, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6401, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6401, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6402, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (6402, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (12651, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (12651, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (12751, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (12751, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (14652, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (14652, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (16001, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (16001, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (16301, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (16301, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (16951, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (16951, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17201, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17201, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17351, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17351, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17751, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17751, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17752, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17752, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17753, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17753, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17754, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17754, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17755, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17755, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17756, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17756, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17757, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17757, 'message', 'Bad credentials');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17758, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO public.jhi_persistent_audit_evt_data VALUES (17758, 'message', 'Bad credentials');


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

INSERT INTO public.quittance_mensuelle_impot VALUES (17904, 2020, 1, '100', '2020-02-21', 100335.553090, 14302, 'admin', '2020-02-12 11:54:14.513', 'admin', '2020-02-21 10:35:10.488', 'DECLARATION_INITIALE', 'VALIDE', NULL);


--

--

INSERT INTO public.quittance_mensuelle_impot_detail VALUES (17982, 7058, 17904, 0.000000, 'admin', '2020-02-12 11:54:14.536', 'admin', '2020-02-21 10:35:10.515', false, false, NULL, 'TCL', 'TCL', false, 1, NULL, 0.000000);
INSERT INTO public.quittance_mensuelle_impot_detail VALUES (17975, 7051, 17904, 100335.553090, 'admin', '2020-02-12 11:54:14.514', 'admin', '2020-02-21 10:35:10.495', false, false, NULL, 'RS', 'RS', false, 1, NULL, 0.000000);
INSERT INTO public.quittance_mensuelle_impot_detail VALUES (17976, 7052, 17904, 0.000000, 'admin', '2020-02-12 11:54:14.52', 'admin', '2020-02-21 10:35:10.502', false, false, NULL, 'TFP', 'TFP', false, 1, NULL, 0.000000);
INSERT INTO public.quittance_mensuelle_impot_detail VALUES (17977, 7053, 17904, 0.000000, 'admin', '2020-02-12 11:54:14.522', 'admin', '2020-02-21 10:35:10.503', false, false, NULL, 'FOPROLOS', 'FOPROLOS', false, 1, NULL, 0.000000);
INSERT INTO public.quittance_mensuelle_impot_detail VALUES (17978, 7054, 17904, 0.000000, 'admin', '2020-02-12 11:54:14.524', 'admin', '2020-02-21 10:35:10.505', true, false, NULL, 'TVA', 'TVA', true, 1, NULL, 0.000000);
INSERT INTO public.quittance_mensuelle_impot_detail VALUES (17979, 7055, NULL, 0.000000, 'admin', '2020-02-12 11:54:14.524', 'admin', '2020-02-21 10:35:10.506', false, true, 17978, 'TVA Collectée', 'TVA_COLLECTEE', false, 1, NULL, 0.000000);
INSERT INTO public.quittance_mensuelle_impot_detail VALUES (17980, 7056, NULL, 0.000000, 'admin', '2020-02-12 11:54:14.527', 'admin', '2020-02-21 10:35:10.508', false, true, 17978, 'TVA Déductible', 'TVA_DEDUCTIBLE', false, -1, NULL, 0.000000);
INSERT INTO public.quittance_mensuelle_impot_detail VALUES (17981, 7057, 17904, 0.000000, 'admin', '2020-02-12 11:54:14.534', 'admin', '2020-02-21 10:35:10.514', false, false, NULL, 'Droit de Timbre', 'DROIT_DE_TIMPBRE', false, 1, NULL, 0.000000);


--

--

INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18099, 7121, 17980, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.534', 'admin', '2020-02-21 10:35:10.514');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18100, 7122, 17981, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.535', 'admin', '2020-02-21 10:35:10.515');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18101, 17001, 17982, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.537', 'admin', '2020-02-21 10:35:10.516');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18102, 17002, 17982, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.538', 'admin', '2020-02-21 10:35:10.516');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18103, 7123, 17982, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.539', 'admin', '2020-02-21 10:35:10.517');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18104, 7124, 17982, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.556', 'admin', '2020-02-21 10:35:10.517');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18079, 7101, 17975, 100101.256000, 100101.256000, 'admin', '2020-02-12 11:54:14.516', 'admin', '2020-02-21 10:35:10.499');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18080, 7102, 17975, 1547.255000, 232.088250, 'admin', '2020-02-12 11:54:14.516', 'admin', '2020-02-21 10:35:10.499');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18081, 7103, 17975, 147.256000, 2.208840, 'admin', '2020-02-12 11:54:14.517', 'admin', '2020-02-21 10:35:10.5');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18082, 7104, 17975, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.518', 'admin', '2020-02-21 10:35:10.5');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18083, 7105, 17975, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.519', 'admin', '2020-02-21 10:35:10.501');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18084, 7106, 17975, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.52', 'admin', '2020-02-21 10:35:10.501');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18085, 7107, 17976, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.521', 'admin', '2020-02-21 10:35:10.502');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18086, 7108, 17976, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.522', 'admin', '2020-02-21 10:35:10.503');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18087, 7109, 17977, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.523', 'admin', '2020-02-21 10:35:10.504');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18088, 7110, 17979, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.525', 'admin', '2020-02-21 10:35:10.507');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18089, 7111, 17979, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.526', 'admin', '2020-02-21 10:35:10.507');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18090, 7112, 17979, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.526', 'admin', '2020-02-21 10:35:10.508');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18091, 7113, 17980, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.528', 'admin', '2020-02-21 10:35:10.509');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18092, 7114, 17980, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.529', 'admin', '2020-02-21 10:35:10.509');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18093, 7115, 17980, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.53', 'admin', '2020-02-21 10:35:10.509');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18094, 7116, 17980, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.53', 'admin', '2020-02-21 10:35:10.51');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18095, 7117, 17980, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.531', 'admin', '2020-02-21 10:35:10.511');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18096, 7118, 17980, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.532', 'admin', '2020-02-21 10:35:10.512');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18097, 7119, 17980, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.532', 'admin', '2020-02-21 10:35:10.512');
INSERT INTO public.quittance_mensuelle_impot_sous_detail VALUES (18098, 7120, 17980, 0.000000, 0.000000, 'admin', '2020-02-12 11:54:14.533', 'admin', '2020-02-21 10:35:10.512');


--

--

INSERT INTO public.region VALUES (1101, 'MAHDIA', 'MAHDIA', NULL, 'admin', NULL, NULL, NULL);
INSERT INTO public.region VALUES (1102, 'SOUSSE', 'SOUSSE', NULL, 'admin', NULL, NULL, NULL);
INSERT INTO public.region VALUES (1103, 'MONASTIR', 'MONASTIR', 'MONASTIR', 'admin', NULL, NULL, NULL);
INSERT INTO public.region VALUES (1104, 'TUNIS', 'TUNIS', NULL, 'admin', NULL, NULL, NULL);
INSERT INTO public.region VALUES (1105, 'ARIANA', 'ARIANA', 'ARIANA', 'admin', NULL, NULL, NULL);


--

--

INSERT INTO public.secteur_activite VALUES (1051, 'COMMERCE', 'Commerce', NULL, 'admin', NULL, NULL, NULL);
INSERT INTO public.secteur_activite VALUES (1052, 'SERVICE', 'Service', NULL, 'admin', NULL, NULL, NULL);
INSERT INTO public.secteur_activite VALUES (1053, 'INDUSTRIE', 'Industrie', NULL, 'admin', NULL, NULL, NULL);
INSERT INTO public.secteur_activite VALUES (1054, 'AUTRES', 'Autres', NULL, 'admin', NULL, NULL, NULL);


--

--

INSERT INTO public.ville VALUES (1201, 'LACHEBBA', '5170', 'LA CHEBBA', NULL, 1101, 'admin', NULL, NULL, NULL);



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

