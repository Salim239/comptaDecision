Alter table public.fiche_client add column numero_etablissement_secondaire varchar(3);
Alter table public.fiche_client add column code_categorie varchar(1);
Alter table public.fiche_client add column code_tva varchar(1);
ALTER TABLE public.activite add column secteur_activite_id bigint;

ALTER TABLE public.activite  ADD CONSTRAINT fk_activite_secteur_activite FOREIGN KEY (secteur_activite_id) REFERENCES public.secteur_activite(id);
