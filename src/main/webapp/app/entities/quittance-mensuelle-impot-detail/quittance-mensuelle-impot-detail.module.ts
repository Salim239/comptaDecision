import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from 'app/core';

import {ComptaDecisionSharedModule} from 'app/shared';
import {
    QuittanceMensuelleImpotDetailComponent,
    QuittanceMensuelleImpotDetailDeleteDialogComponent,
    QuittanceMensuelleImpotDetailDeletePopupComponent,
    QuittanceMensuelleImpotDetailDetailComponent,
    quittanceMensuelleImpotDetailPopupRoute,
    quittanceMensuelleImpotDetailRoute,
    QuittanceMensuelleImpotDetailUpdateComponent
} from './';

const ENTITY_STATES = [...quittanceMensuelleImpotDetailRoute, ...quittanceMensuelleImpotDetailPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QuittanceMensuelleImpotDetailComponent,
        QuittanceMensuelleImpotDetailDetailComponent,
        QuittanceMensuelleImpotDetailUpdateComponent,
        QuittanceMensuelleImpotDetailDeleteDialogComponent,
        QuittanceMensuelleImpotDetailDeletePopupComponent
    ],
    entryComponents: [
        QuittanceMensuelleImpotDetailComponent,
        QuittanceMensuelleImpotDetailUpdateComponent,
        QuittanceMensuelleImpotDetailDeleteDialogComponent,
        QuittanceMensuelleImpotDetailDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionQuittanceMensuelleImpotDetailModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
