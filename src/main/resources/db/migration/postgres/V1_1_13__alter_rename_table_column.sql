alter table impot_annuel_ligne rename column impot_mensuel_detail_id to impot_mensuel_ligne_id;
alter table impot_annuel_ligne rename constraint fk_impot_annuel_impot_mensuel_detail_id to fk_impot_annuel_impot_mensuel_ligne_id;
alter table impot_mensuel_ligne rename constraint pk_impot_mensuel_detail to pk_impot_mensuel_ligne;
alter table impot_mensuel_ligne rename constraint fk_impot_mensuel_detail_impot_mensuel_id to fk_impot_mensuel_ligne_impot_mensuel_id;





