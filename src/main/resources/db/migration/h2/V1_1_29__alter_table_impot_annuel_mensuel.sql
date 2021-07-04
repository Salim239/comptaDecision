ALTER TABLE public.impot_annuel ADD montant_negatif_accepte boolean default false;
UPDATE public.impot_annuel set montant_negatif_accepte = false;
UPDATE public.impot_annuel set montant_negatif_accepte = true where code in ('BALANCE_COMPTE', 'BALANCE_FISCALE');
