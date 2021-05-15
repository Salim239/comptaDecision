CREATE TABLE public.categorie_cnss_gerant (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    montant_cotisation_cnss numeric(6,3) NOT NULL,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

drop table public.cnss;
CREATE TABLE public.cnss (
    id bigint NOT NULL,
    annee integer NOT NULL,
    trimestre integer NOT NULL,
    jhi_date date,
    numero_quittance  VARCHAR(255),
    montant_salaire_brut_normal numeric(20,6),
    montant_salaire_brut_karama numeric(20,6),
    montant_total_salaire_brut nUMERIC(20,6),
    montant_cnss_normal numeric(20,6),
    montant_cnss_karama numeric(20,6),
    montant_total_cnss numeric(20,6),
    taux_cnss_normal numeric(10,2),
    taux_cnss_normal_accident numeric(10,2),
    taux_cnss_karama numeric(10,2),
    taux_cnss_karama_accident numeric(10,2),
    fiche_client_id bigint,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
    type_cnss  VARCHAR(255)
);

ALTER TABLE public.categorie_cnss_gerant add constraint cnss_gerant_pk PRIMARY KEY (id);
ALTER TABLE public.cnss add column taux_cnss_accident_karama numeric(6,3);
alter table public.fiche_client add column categorie_cnss_gerant_id bigint;
ALTER TABLE public.fiche_client  ADD CONSTRAINT fk_fiche_client_categorie_cnss_gerant FOREIGN KEY (categorie_cnss_gerant_id) REFERENCES public.categorie_cnss_gerant(id);
