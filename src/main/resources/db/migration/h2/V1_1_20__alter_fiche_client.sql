alter table public.fiche_client drop column lieu_naissance_gerant;
alter table public.fiche_client add column lieu_naissance_gerant varchar(100);
