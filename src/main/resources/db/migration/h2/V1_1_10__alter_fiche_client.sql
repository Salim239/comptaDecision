CREATE TABLE public.centre_administratif (
    id bigint NOT NULL,
    type  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    telephone1  VARCHAR(255),
    telephone2  VARCHAR(255),
    telephone3  VARCHAR(255),
    fax  VARCHAR(255),
    email1  VARCHAR(255),
    email2  VARCHAR(255),
    email3  VARCHAR(255),
    adresse  VARCHAR(255),
    region_id bigint,
    ville_id bigint,
    code_postal  VARCHAR(5),
    banque VARCHAR(255),
    rib VARCHAR(255),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

Alter table public.fiche_client add column administration_cnss_id bigint;
Alter table public.fiche_client add column administration_fiscale_id bigint;
Alter table public.fiche_client add column administration_impot_id bigint;
Alter table public.fiche_client add column activite1_id bigint;
Alter table public.fiche_client add column activite2_id bigint;
Alter table public.fiche_client add column activite3_id bigint;
Alter table public.fiche_client add column secteur_activite1_id bigint;
Alter table public.fiche_client add column secteur_activite2_id bigint;
Alter table public.fiche_client add column secteur_activite3_id bigint;
ALTER TABLE public.fiche_client add column taux_cnss_accident numeric(6,3);

ALTER TABLE public.fiche_client  ADD CONSTRAINT fk_fiche_client_activite1_id FOREIGN KEY (activite1_id) REFERENCES public.activite(id);
ALTER TABLE public.fiche_client  ADD CONSTRAINT fk_fiche_client_activite2_id FOREIGN KEY (activite2_id) REFERENCES public.activite(id);
ALTER TABLE public.fiche_client  ADD CONSTRAINT fk_fiche_client_activite3_id FOREIGN KEY (activite3_id) REFERENCES public.activite(id);

ALTER TABLE public.fiche_client ADD CONSTRAINT fk_fiche_client_secteur_activite1_id FOREIGN KEY (secteur_activite1_id) REFERENCES public.secteur_activite(id);
ALTER TABLE public.fiche_client ADD CONSTRAINT fk_fiche_client_secteur_activite2_id FOREIGN KEY (secteur_activite2_id) REFERENCES public.secteur_activite(id);
ALTER TABLE public.fiche_client ADD CONSTRAINT fk_fiche_client_secteur_activite3_id FOREIGN KEY (secteur_activite3_id) REFERENCES public.secteur_activite(id);

ALTER TABLE public.fiche_client
    ADD CONSTRAINT fk_fiche_client_administration_cnss_id FOREIGN KEY (administration_cnss_id) REFERENCES public.centre_administratif(id);

ALTER TABLE public.fiche_client
    ADD CONSTRAINT fk_fiche_client_administration_fiscale_id FOREIGN KEY (administration_fiscale_id) REFERENCES public.centre_administratif(id);

ALTER TABLE public.fiche_client
    ADD CONSTRAINT fk_fiche_client_administration_impot_id FOREIGN KEY (administration_impot_id) REFERENCES public.centre_administratif(id);

ALTER TABLE public.centre_administratif
    ADD CONSTRAINT fk_centre_administratif_ville FOREIGN KEY (ville_id) REFERENCES public.ville(id);

ALTER TABLE public.centre_administratif
    ADD CONSTRAINT fk_centre_administratif_region FOREIGN KEY (region_id) REFERENCES public.region(id);

ALTER TABLE public.cnss add column type_declaration varchar(255) not null;
ALTER TABLE public.cnss add column montant_cnss_normal numeric(20,6) not null;
ALTER TABLE public.cnss add column montant_cnss_karama numeric(20,6) not null;
ALTER TABLE public.cnss add column montant_cnss_autre numeric(20,6);
ALTER TABLE public.cnss add column taux_cnss_normal numeric(6,3) not null;
ALTER TABLE public.cnss add column taux_cnss_accident numeric(6,3) not null;
ALTER TABLE public.cnss add column taux_cnss_karama numeric(6,3) not null;
ALTER TABLE public.cnss add column taux_cnss_autre numeric(6,3);

