----------------------
--- Tables taches
----------------------

-- QUITTANCE, DECLARATION ANNUELLE, ...
CREATE TABLE public.categorie_tache (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

-- Créer, A faire, en cours, termine, ...
CREATE TABLE public.statut_tache (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);


CREATE TABLE public.tache (
    id bigint NOT NULL,
    code  VARCHAR(255) NOT NULL,
    libelle  VARCHAR(255) NOT NULL,
    priorite  VARCHAR(255), -- BLOQUANT, URGENT, NORMAL, FAIBLE
    description  VARCHAR(255),
    estimation_initiale VARCHAR(255),
    estimation_mise_a_jour VARCHAR(255),
    pourcentage_avancement numeric(6,3),
    validation boolean,
    attribution VARCHAR(50),
    rapporteur VARCHAR(50),
    statut_tache_id bigint not  null ,
    categorie_tache_id bigint not null,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    date_debut_planifie timestamp,
    date_rappel timestamp,
    date_echeance_planifie timestamp,
    date_cloture timestamp,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

alter table public.tache add constraint tache_categorie_tache_fk foreign key(categorie_tache_id) references categorie_tache(id);
alter table public.tache add constraint tache_categorie_statut_fk foreign key(statut_tache_id) references statut_tache(id);

CREATE TABLE public.changement_categorie_tache (
    id bigint NOT NULL,
    categorie_tache_id bigint NOT NULL,
    tache_id bigint NOT NULL,
    description VARCHAR(255),
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

alter table public.changement_categorie_tache add constraint changement_categorie_tache_categorie_tache_fk foreign key(categorie_tache_id) references public.categorie_tache(id);
alter table public.changement_categorie_tache add constraint changement_categorie_tache_tache_fk foreign key(tache_id) references tache(id);

CREATE TABLE public.changement_statut_tache (
    id bigint NOT NULL,
    statut_tache_id bigint NOT NULL,
    tache_id bigint NOT NULL,
    description VARCHAR(255),
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

alter table public.changement_statut_tache add constraint changement_statut_tache_categorie_tache_fk foreign key(statut_tache_id) references public.statut_tache(id);
alter table public.changement_statut_tache add constraint changement_statut_tache_tache_fk foreign key(tache_id) references tache(id);

CREATE TABLE public.liaison_tache (
    id bigint NOT NULL,
    tache_source_id bigint NOT NULL,
    tache_cible_id bigint NOT NULL,
    type_liaison  VARCHAR(255) NOT NULL, --LIEE, SOUS-TACHE
    description VARCHAR(255),
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

alter table public.liaison_tache add constraint liaison_tache_tache_cible_fk foreign key(tache_cible_id) references tache(id);
alter table public.liaison_tache add constraint liaison_tache_tache_source_fk foreign key(tache_source_id) references tache(id);

CREATE TABLE public.journal_travail_tache (
    id bigint NOT NULL,
    description_travail  VARCHAR(255),
    temps_travaille VARCHAR(255),
    raf_estime VARCHAR(255), --Reste à faire estimé
    statut_tache_id bigint NOT NULL,
    tache_id bigint NOT NULL,
    created_by  VARCHAR(50) DEFAULT 'admin' NOT NULL,
    created_date timestamp,
    last_modified_by  VARCHAR(50),
    last_modified_date timestamp
);

alter table public.changement_journal_travail_tache add constraint journal_travail_tache_statut_tache_fk foreign key(statut_tache_id) references statut_tache(id);
alter table public.changement_journal_travail_tache add constraint journal_travail_tache_tache_fk foreign key(tache_id) references tache(id);

