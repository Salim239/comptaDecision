import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    QuittanceMensuelleImpotLineComponent,
    QuittanceMensuelleImpotLineDetailComponent,
    QuittanceMensuelleImpotLineUpdateComponent,
    QuittanceMensuelleImpotLineDeletePopupComponent,
    QuittanceMensuelleImpotLineDeleteDialogComponent,
    quittanceMensuelleImpotLineRoute,
    quittanceMensuelleImpotLinePopupRoute
} from './';

const ENTITY_STATES = [...quittanceMensuelleImpotLineRoute, ...quittanceMensuelleImpotLinePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QuittanceMensuelleImpotLineComponent,
        QuittanceMensuelleImpotLineDetailComponent,
        QuittanceMensuelleImpotLineUpdateComponent,
        QuittanceMensuelleImpotLineDeleteDialogComponent,
        QuittanceMensuelleImpotLineDeletePopupComponent
    ],
    entryComponents: [
        QuittanceMensuelleImpotLineComponent,
        QuittanceMensuelleImpotLineUpdateComponent,
        QuittanceMensuelleImpotLineDeleteDialogComponent,
        QuittanceMensuelleImpotLineDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionQuittanceMensuelleImpotLineModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
