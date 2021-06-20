alter table public.acompte_provisionnel add column montant_penalite numeric(20,6) default 0;
alter table public.declaration_annuelle drop column montant_penalite;
alter table public.declaration_annuelle add column montant_penalite numeric(20,6) default 0;
alter table public.fiche_client drop column montant_frais_cabinet;
alter table public.fiche_client add column montant_frais_cabinet numeric(20,6);
