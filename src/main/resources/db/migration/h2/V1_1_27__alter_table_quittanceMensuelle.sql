alter table public.quittance_mensuelle add column montant_taxes NUMERIC(20, 6);
alter table public.quittance_mensuelle add column montant_penalite NUMERIC(20, 6);
update public.quittance_mensuelle set montant_penalite = 0 where 1 = 1;
update public.quittance_mensuelle set montant_taxes = montant_total where 1 = 1;
