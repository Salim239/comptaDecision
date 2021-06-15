alter table public.fiche_client drop column dateNaissance_gerant;
alter table public.fiche_client add column date_naissance_gerant timestamp;

-- Finir script des autres tables et finir ajout champs fiche client
