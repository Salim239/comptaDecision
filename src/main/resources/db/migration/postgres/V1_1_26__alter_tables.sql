CREATE TABLE public.paiement (
    id bigint NOT NULL,
    libelle varchar(255) not null,
    mode_paiement varchar(255) not null, -- cheque, virement, caisse, ...
    numero_preuve_paiement varchar(255)   not null, -- num cheque, virement, operation caisse ...
    id_module_associe bigint, -- num cheque, virement, operation caisse ...
    module_associe bigint, -- num cheque, virement, operation caisse ...
    fiche_client_id bigint,
    date_paiement date not null,
    montant_paye numeric(20,6) not null,
    montant_du numeric(20,6),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
    ligne_caisse_id                   bigint,
    cnss_id                           bigint,
    quittance_mensuelle_id            bigint,
    declaration_annuelle_id           bigint,
    declaration_employeur_annuelle_id bigint,
    acompte_provisionnel_id           bigint
);

ALTER TABLE public.paiement ADD CONSTRAINT pk_paiement PRIMARY KEY (id);
ALTER TABLE public.paiement ADD CONSTRAINT fk_paiement_ligne_caisse_id FOREIGN KEY (ligne_caisse_id) references public.ligne_caisse(id);
ALTER TABLE public.paiement ADD CONSTRAINT fk_paiement_fiche_client_id FOREIGN KEY (fiche_client_id) references public.fiche_client(id);
ALTER TABLE public.paiement ADD CONSTRAINT fk_paiement_cnss_id FOREIGN KEY (cnss_id) references public.cnss(id);
ALTER TABLE public.paiement ADD CONSTRAINT fk_paiement_quittance_mensuelle_id FOREIGN KEY (quittance_mensuelle_id) references public.quittance_mensuelle(id);
ALTER TABLE public.paiement ADD CONSTRAINT fk_paiement_declaration_annuelle_id FOREIGN KEY (declaration_annuelle_id) references public.declaration_annuelle(id);
ALTER TABLE public.paiement ADD CONSTRAINT fk_paiement_declaration_employeur_annuelle_id FOREIGN KEY (declaration_employeur_annuelle_id) references public.declaration_employeur_annuelle(id);
ALTER TABLE public.paiement ADD CONSTRAINT fk_paiement_acompte_provisionnel_id FOREIGN KEY (acompte_provisionnel_id) references public.acompte_provisionnel(id);

ALTER TABLE public.ligne_caisse ADD COLUMN paiement_id bigint;
ALTER TABLE public.ligne_caisse ADD constraint fk_ligne_caisse_paiement_id FOREIGN KEY (paiement_id) references paiement(id);

