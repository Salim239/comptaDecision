alter table public.fiche_client drop column lieu_delivrance_cin_gerant;
alter table public.fiche_client add column lieu_delivrance_cin_gerant varchar(100);
