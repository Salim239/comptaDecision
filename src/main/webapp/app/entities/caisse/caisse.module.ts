import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from 'app/core';

import {ComptaDecisionSharedModule} from 'app/shared';
import {
    CaisseComponent,
    CaisseDeleteDialogComponent,
    CaisseDeletePopupComponent,
    CaisseDetailComponent,
    caissePopupRoute,
    caisseRoute,
    CaisseUpdateComponent
} from './';

const ENTITY_STATES = [...caisseRoute, ...caissePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CaisseComponent, CaisseDetailComponent, CaisseUpdateComponent, CaisseDeleteDialogComponent, CaisseDeletePopupComponent],
    entryComponents: [CaisseComponent, CaisseUpdateComponent, CaisseDeleteDialogComponent, CaisseDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionCaisseModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
