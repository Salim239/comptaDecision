import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    QuittanceMensuelleComponent,
    QuittanceMensuelleDeleteDialogComponent,
    QuittanceMensuelleDeletePopupComponent,
    QuittanceMensuelleDetailComponent,
    quittanceMensuellePopupRoute,
    quittanceMensuelleRoute,
    QuittanceMensuelleUpdateComponent
} from './';
import { QuittanceMensuelleLigneListComponent } from './quittance-mensuelle-ligne/quittance-mensuelle-ligne-list.component';
import { QuittanceMensuelleLigneCalcMontantTotalComponent } from './quittance-mensuelle-ligne/quittance-mensuelle-ligne-calc-montant-total.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';

const ENTITY_STATES = [...quittanceMensuelleRoute, ...quittanceMensuellePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES), Ng2SmartTableModule],
    declarations: [
        QuittanceMensuelleComponent,
        QuittanceMensuelleDetailComponent,
        QuittanceMensuelleUpdateComponent,
        QuittanceMensuelleDeleteDialogComponent,
        QuittanceMensuelleDeletePopupComponent,
        QuittanceMensuelleLigneListComponent,
        QuittanceMensuelleLigneCalcMontantTotalComponent
    ],
    entryComponents: [
        QuittanceMensuelleComponent,
        QuittanceMensuelleUpdateComponent,
        QuittanceMensuelleDeleteDialogComponent,
        QuittanceMensuelleDeletePopupComponent,
        QuittanceMensuelleLigneListComponent,
        QuittanceMensuelleLigneCalcMontantTotalComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionQuittanceMensuelleModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
