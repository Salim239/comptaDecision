import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'region',
                loadChildren: './region/region.module#ComptaDecisionRegionModule'
            },
            {
                path: 'ville',
                loadChildren: './ville/ville.module#ComptaDecisionVilleModule'
            },
            {
                path: 'centre-administratif',
                loadChildren: './centre-administratif/centre-administratif.module#ComptaDecisionCentreAdministratifModule'
            },
            {
                path: 'secteur-activite',
                loadChildren: './secteur-activite/secteur-activite.module#ComptaDecisionSecteurActiviteModule'
            },
            {
                path: 'categorie-cnss-gerant',
                loadChildren: './categorie-cnss-gerant/categorie-cnss-gerant.module#ComptaDecisionCategorieCnssGerantModule'
            },
            {
                path: 'activite',
                loadChildren: './activite/activite.module#ComptaDecisionActiviteModule'
            },
            {
                path: 'impot-mensuel',
                loadChildren: './impot-mensuel/impot-mensuel.module#ComptaDecisionImpotMensuelModule'
            },
            {
                path: 'impot-annuel',
                loadChildren: './impot-annuel/impot-annuel.module#ComptaDecisionImpotAnnuelModule'
            },
            {
                path: 'fiche-client',
                loadChildren: './fiche-client/fiche-client.module#ComptaDecisionFicheClientModule'
            },
            {
                path: 'cnss',
                loadChildren: './cnss/cnss.module#ComptaDecisionCnssModule'
            },
            {
                path: 'impot-mensuel-client',
                loadChildren: './impot-mensuel-client/impot-mensuel-client.module#ComptaDecisionImpotMensuelClientModule'
            },
            {
                path: 'quittance-mensuelle',
                loadChildren: './quittance-mensuelle/quittance-mensuelle.module#ComptaDecisionQuittanceMensuelleModule'
            },
            {
                path: 'quittance-mensuelle-ligne',
                loadChildren: './quittance-mensuelle-ligne/quittance-mensuelle-ligne.module#ComptaDecisionQuittanceMensuelleLigneModule'
            },
            {
                path: 'declaration-annuelle',
                loadChildren: './declaration-annuelle/declaration-annuelle.module#ComptaDecisionDeclarationAnnuelleModule'
            },
            {
                path: 'declaration-employeur-annuelle',
                loadChildren:
                    './declaration-employeur-annuelle/declaration-employeur-annuelle.module#ComptaDecisionDeclarationEmployeurAnnuelleModule'
            },
            {
                path: 'acompte-provisionnel',
                loadChildren: './acompte-provisionnel/acompte-provisionnel.module#ComptaDecisionAcompteProvisionnelModule'
            },
            {
                path: 'application-user',
                loadChildren: './application-user/application-user.module#ComptaDecisionApplicationUserModule'
            },
            {
                path: 'application-user-fiche-client',
                loadChildren:
                    './application-user-fiche-client/application-user-fiche-client.module#ComptaDecisionApplicationUserFicheClientModule'
            },
            {
                path: 'cabinet-comptable',
                loadChildren: './cabinet-comptable/cabinet-comptable.module#ComptaDecisionCabinetComptableModule'
            },
            {
                path: 'caisse',
                loadChildren: './caisse/caisse.module#ComptaDecisionCaisseModule'
            },
            {
                path: 'ligne-caisse',
                loadChildren: './ligne-caisse/ligne-caisse.module#ComptaDecisionLigneCaisseModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionEntityModule {}
