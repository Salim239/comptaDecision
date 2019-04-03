import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

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
                path: 'secteur-activite',
                loadChildren: './secteur-activite/secteur-activite.module#ComptaDecisionSecteurActiviteModule'
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
                path: 'quittance-mensuelle-impot',
                loadChildren: './quittance-mensuelle-impot/quittance-mensuelle-impot.module#ComptaDecisionQuittanceMensuelleImpotModule'
            },
            {
                path: 'quittance-mensuelle-impot-line',
                loadChildren:
                    './quittance-mensuelle-impot-line/quittance-mensuelle-impot-line.module#ComptaDecisionQuittanceMensuelleImpotLineModule'
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
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionEntityModule {}
