alter table public.quittance_mensuelle_impot_detail ALTER COLUMN quittance_mensuelle_impot_id rename to quittance_mensuelle_id;
alter table public.quittance_mensuelle_impot_detail ALTER COLUMN parent_quittance_mensuelle_impot_detail_id rename to parent_quittance_mensuelle_ligne_id;
alter table public.quittance_mensuelle_impot_sous_detail ALTER COLUMN impot_mensuel_detail_id rename to impot_mensuel_ligne_id;
alter table public.quittance_mensuelle_impot_sous_detail ALTER COLUMN quittance_mensuelle_impot_detail_id rename to quittance_mensuelle_detail_id;
alter table public.declaration_annuelle_detail rename to declaration_annuelle_ligne;
alter table public.impot_annuel_detail rename to impot_annuel_ligne;
alter table public.impot_mensuel_detail rename to impot_mensuel_ligne;
alter table public.quittance_mensuelle_impot rename to quittance_mensuelle;
alter table public.quittance_mensuelle_impot_detail rename to quittance_mensuelle_ligne;
alter table public.quittance_mensuelle_impot_sous_detail rename to quittance_mensuelle_sous_ligne;









