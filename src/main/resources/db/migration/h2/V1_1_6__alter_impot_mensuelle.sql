update public.impot_mensuel_detail set impot_mensuel_detail.valeur_modifiable = true where type_valeur = 'MONTANT' AND valeur is null;
