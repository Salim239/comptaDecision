import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    QuittanceMensuelleLigneComponent,
    QuittanceMensuelleLigneDeleteDialogComponent,
    QuittanceMensuelleLigneDeletePopupComponent,
    QuittanceMensuelleLigneLigneComponent,
    quittanceMensuelleLignePopupRoute,
    quittanceMensuelleLigneRoute,
    QuittanceMensuelleLigneUpdateComponent
} from './';

const ENTITY_STATES = [...quittanceMensuelleLigneRoute, ...quittanceMensuelleLignePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QuittanceMensuelleLigneComponent,
        QuittanceMensuelleLigneLigneComponent,
        QuittanceMensuelleLigneUpdateComponent,
        QuittanceMensuelleLigneDeleteDialogComponent,
        QuittanceMensuelleLigneDeletePopupComponent
    ],
    entryComponents: [
        QuittanceMensuelleLigneComponent,
        QuittanceMensuelleLigneUpdateComponent,
        QuittanceMensuelleLigneDeleteDialogComponent,
        QuittanceMensuelleLigneDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionQuittanceMensuelleLigneModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
