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

ALTER TABLE public.centre_administratif
    ADD CONSTRAINT fk_centre_administratif_ville FOREIGN KEY (ville_id) REFERENCES public.ville(id);

ALTER TABLE public.centre_administratif
    ADD CONSTRAINT fk_centre_administratif_region FOREIGN KEY (region_id) REFERENCES public.region(id);
