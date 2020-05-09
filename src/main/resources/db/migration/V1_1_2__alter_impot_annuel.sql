ALTER TABLE public.impot_annuel add column calculate_from_impot_annuel boolean default false;
CREATE TABLE public.formule_impot_annuel (
    id bigint NOT NULL,
    impot_annuel_parent_id bigint NOT NULL,
    impot_annuel_enfant_id bigint NOT NULL,
    coefficient numeric(6,2) not null,
    impot_mensuel_id bigint NOT NULL,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp,
);

ALTER TABLE public.formule_impot_annuel ADD CONSTRAINT pk_formule_impot_annuel PRIMARY KEY (id);
ALTER TABLE public.formule_impot_annuel
    ADD CONSTRAINT fk_formule_impot_annuel_parent FOREIGN KEY (impot_annuel_parent_id) REFERENCES public.formule_impot_annuel(id);
    ALTER TABLE public.formule_impot_annuel
    ADD CONSTRAINT fk_formule_impot_annuel_enfant FOREIGN KEY (impot_annuel_parent_id) REFERENCES public.formule_impot_annuel(id);


