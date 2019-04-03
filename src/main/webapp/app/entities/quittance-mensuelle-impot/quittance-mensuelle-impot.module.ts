import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    QuittanceMensuelleImpotComponent,
    QuittanceMensuelleImpotDetailComponent,
    QuittanceMensuelleImpotUpdateComponent,
    QuittanceMensuelleImpotDeletePopupComponent,
    QuittanceMensuelleImpotDeleteDialogComponent,
    quittanceMensuelleImpotRoute,
    quittanceMensuelleImpotPopupRoute
} from './';

const ENTITY_STATES = [...quittanceMensuelleImpotRoute, ...quittanceMensuelleImpotPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QuittanceMensuelleImpotComponent,
        QuittanceMensuelleImpotDetailComponent,
        QuittanceMensuelleImpotUpdateComponent,
        QuittanceMensuelleImpotDeleteDialogComponent,
        QuittanceMensuelleImpotDeletePopupComponent
    ],
    entryComponents: [
        QuittanceMensuelleImpotComponent,
        QuittanceMensuelleImpotUpdateComponent,
        QuittanceMensuelleImpotDeleteDialogComponent,
        QuittanceMensuelleImpotDeletePopupComponent
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
