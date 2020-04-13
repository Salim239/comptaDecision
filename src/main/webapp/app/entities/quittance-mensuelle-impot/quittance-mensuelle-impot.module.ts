import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from 'app/core';

import {ComptaDecisionSharedModule} from 'app/shared';
import {
    QuittanceMensuelleImpotComponent,
    QuittanceMensuelleImpotDeleteDialogComponent,
    QuittanceMensuelleImpotDeletePopupComponent,
    QuittanceMensuelleImpotDetailComponent,
    quittanceMensuelleImpotPopupRoute,
    quittanceMensuelleImpotRoute,
    QuittanceMensuelleImpotUpdateComponent
} from './';
import {QuittanceMensuelleImpotDetailListComponent} from './quittance-mensuelle-impot-detail/quittance-mensuelle-impot-detail-list.component';
import {QuittanceMensuelleImpotDetailCalcMontantTotalComponent} from './quittance-mensuelle-impot-detail/quittance-mensuelle-impot-detail-calc-montant-total.component';

const ENTITY_STATES = [...quittanceMensuelleImpotRoute, ...quittanceMensuelleImpotPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QuittanceMensuelleImpotComponent,
        QuittanceMensuelleImpotDetailComponent,
        QuittanceMensuelleImpotUpdateComponent,
        QuittanceMensuelleImpotDeleteDialogComponent,
        QuittanceMensuelleImpotDeletePopupComponent,
        QuittanceMensuelleImpotDetailListComponent,
        QuittanceMensuelleImpotDetailCalcMontantTotalComponent
    ],
    entryComponents: [
        QuittanceMensuelleImpotComponent,
        QuittanceMensuelleImpotUpdateComponent,
        QuittanceMensuelleImpotDeleteDialogComponent,
        QuittanceMensuelleImpotDeletePopupComponent,
        QuittanceMensuelleImpotDetailListComponent,
        QuittanceMensuelleImpotDetailCalcMontantTotalComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionQuittanceMensuelleImpotModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
