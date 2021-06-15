ALTER TABLE public.cnss
    ADD CONSTRAINT pk_cnss PRIMARY KEY (id);
-- cabinet comptable
create table public.cabinet_comptable(
                                         id                      bigint       not null        constraint pk_cabinet_comptable            primary key,
                                         code                    varchar(255) not null,
                                         nombre_license          integer,
                                         fiche_client_cabinet_id bigint        constraint ux_cabinet_comptable_fiche_client_cabinet_id            unique
                                             constraint fk_cabinet_comptable_fiche_client_cabinet_id            references fiche_client
);

-- application user
create table public.application_user(
                                        id                   bigint       not null        constraint pk_application_user primary key,
                                        matricule            varchar(255),
                                        poste                varchar(255) not null,
                                        date_embauche        varchar(255),
                                        cabinet_comptable_id bigint constraint fk_application_user_cabinet_comptable_id references cabinet_comptable
);

-- application_user_fiche_client
create table public.application_user_fiche_client(
                                                     id                  bigint not null       constraint pk_application_user_fiche_client            primary key,
                                                     fiche_client_id     bigint not null       constraint fk_application_user_fiche_client_fiche_client_id            references public.fiche_client,
                                                     application_user_id bigint not null        constraint fk_application_user_fiche_client_application_user_id            references application_user
);

-- Caisse
create table public.caisse(
                              id              bigint not null        constraint pk_caisse            primary key,
                              montant_total   numeric(10, 2),
                              montant_report  numeric(10, 2),
                              cloturee        boolean,
                              fiche_client_id bigint not null        constraint  fk_caisse_fiche_client_id              references fiche_client
);

-- ligne caisse
create table public.ligne_caisse(
                                    id                      bigint       not null        constraint pk_ligne_caisse            primary key,
                                    type_operation          integer      not null,
                                    libelle                 varchar(255) not null,
                                    montant_operation       numeric(10, 2),
                                    quittance_mensuelle_id  bigint        constraint fk_ligne_caisse_quittance_mensuelle_id            references quittance_mensuelle,
                                    declaration_annuelle_id bigint        constraint fk_ligne_caisse_declaration_annuelle_id           references declaration_annuelle,
                                    cnss_id                 bigint        constraint fk_ligne_caisse_cnss_id            references cnss,
                                    caisse_id               bigint        constraint fk_ligne_caisse_caisse_id            references caisse
);

-- cnss
alter table public.cnss add column montant_penalite numeric(10,2) default 0;
--fiche client
alter table public.fiche_client add column taux_cnss_karama numeric(6,3);
alter table public.fiche_client add column taux_cnss_normal numeric(6,3);
alter table public.fiche_client add column etiquettes varchar(255);
alter table public.fiche_client add column prenomGerant varchar(255);
alter table public.fiche_client add column nomGerant varchar(255);
alter table public.fiche_client add column dateNaissanceGerant timestamp;
alter table public.fiche_client add column cinGerant varchar(255);
alter table public.fiche_client add column dateDelivranceCINGerant timestamp;
alter table public.fiche_client add column adresseGerant varchar(255);
alter table public.fiche_client add column telephoneGerant1 varchar(255);
alter table public.fiche_client add column telephoneGerant2 varchar(255);
alter table public.fiche_client add column emailGerant varchar(255);
alter table public.fiche_client add column copieCINGerant bytea;
alter table public.fiche_client add column copieCINGerantContentType varchar(255);
alter table public.fiche_client add column telephone2 varchar(255);
alter table public.fiche_client add column telephone3 varchar(255);
alter table public.fiche_client add column email2 varchar(255);
alter table public.fiche_client add column email3 varchar(255);
alter table public.fiche_client add column cabinet_comptable_id bigint;
alter table public.fiche_client add constraint fk_fiche_cabinet_comptable_id   foreign key (cabinet_comptable_id) references cabinet_comptable (id);

-- Finir script des autres tables et finir ajout champs fiche client
