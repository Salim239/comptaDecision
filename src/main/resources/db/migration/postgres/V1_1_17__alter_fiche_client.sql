alter table public.fiche_client drop column copie_cin_gerantContentType;
alter table public.fiche_client add column copie_cin_gerant_content_type varchar(255);

-- Finir script des autres tables et finir ajout champs fiche client
