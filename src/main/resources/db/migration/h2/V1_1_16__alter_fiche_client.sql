--fiche client
alter table public.fiche_client drop column prenomGerant;
alter table public.fiche_client drop column nomGerant;
alter table public.fiche_client drop column dateNaissanceGerant ;
alter table public.fiche_client drop column cinGerant;
alter table public.fiche_client drop column dateDelivranceCINGerant ;
alter table public.fiche_client drop column adresseGerant;
alter table public.fiche_client drop column telephoneGerant1;
alter table public.fiche_client drop column telephoneGerant2;
alter table public.fiche_client drop column emailGerant;
alter table public.fiche_client drop column copieCINGerant ;
alter table public.fiche_client drop column copieCINGerantContentType ;

alter table public.fiche_client add column prenom_gerant varchar(255);
alter table public.fiche_client add column nom_gerant varchar(255);
alter table public.fiche_client add column dateNaissance_gerant timestamp;
alter table public.fiche_client add column cin_gerant varchar(255);
alter table public.fiche_client add column date_delivrance_cin_gerant timestamp;
alter table public.fiche_client add column adresse_gerant varchar(255);
alter table public.fiche_client add column telephone_gerant1 varchar(255);
alter table public.fiche_client add column telephone_gerant2 varchar(255);
alter table public.fiche_client add column email_gerant varchar(255);
alter table public.fiche_client add column copie_cin_gerant bytea;
alter table public.fiche_client add column copie_cin_gerantContentType varchar(255);

-- Finir script des autres tables et finir ajout champs fiche client
