alter table public.caisse add column created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL;
alter table public.caisse add column created_date timestamp;
alter table public.caisse add column  last_modified_by  VARCHAR(50);
alter table public.caisse add column  last_modified_date timestamp;
alter table public.cabinet_comptable add column created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL;
alter table public.cabinet_comptable add column created_date timestamp;
alter table public.cabinet_comptable add column  last_modified_by  VARCHAR(50);
alter table public.cabinet_comptable add column  last_modified_date timestamp;
alter table public.application_user add column created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL;
alter table public.application_user add column created_date timestamp;
alter table public.application_user add column  last_modified_by  VARCHAR(50);
alter table public.application_user add column  last_modified_date timestamp;
alter table public.impot_mensuel_ligne add column  date_debut timestamp;
alter table public.impot_mensuel_ligne add column  date_fin timestamp;