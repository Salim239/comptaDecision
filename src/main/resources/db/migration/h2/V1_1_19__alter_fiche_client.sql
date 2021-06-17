alter table public.fiche_client add column montant_frais_cabinet numeric(10,2);
alter table public.fiche_client add column monbre_mois_frais_cabinet numeric(2);
alter table public.fiche_client add column lieu_delivrance_cin_gerant timestamp;
alter table public.fiche_client add column lieu_naissance_gerant timestamp;
