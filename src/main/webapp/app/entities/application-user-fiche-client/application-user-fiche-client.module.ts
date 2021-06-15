import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from 'app/core';

import {ComptaDecisionSharedModule} from 'app/shared';
import {
    ApplicationUserFicheClientComponent,
    ApplicationUserFicheClientDeleteDialogComponent,
    ApplicationUserFicheClientDeletePopupComponent,
    ApplicationUserFicheClientDetailComponent,
    applicationUserFicheClientPopupRoute,
    applicationUserFicheClientRoute,
    ApplicationUserFicheClientUpdateComponent
} from './';

const ENTITY_STATES = [...applicationUserFicheClientRoute, ...applicationUserFicheClientPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApplicationUserFicheClientComponent,
        ApplicationUserFicheClientDetailComponent,
        ApplicationUserFicheClientUpdateComponent,
        ApplicationUserFicheClientDeleteDialogComponent,
        ApplicationUserFicheClientDeletePopupComponent
    ],
    entryComponents: [
        ApplicationUserFicheClientComponent,
        ApplicationUserFicheClientUpdateComponent,
        ApplicationUserFicheClientDeleteDialogComponent,
        ApplicationUserFicheClientDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionApplicationUserFicheClientModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
