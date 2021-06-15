import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from 'app/core';

import {ComptaDecisionSharedModule} from 'app/shared';
import {
    ApplicationUserComponent,
    ApplicationUserDeleteDialogComponent,
    ApplicationUserDeletePopupComponent,
    ApplicationUserDetailComponent,
    applicationUserPopupRoute,
    applicationUserRoute,
    ApplicationUserUpdateComponent
} from './';

const ENTITY_STATES = [...applicationUserRoute, ...applicationUserPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApplicationUserComponent,
        ApplicationUserDetailComponent,
        ApplicationUserUpdateComponent,
        ApplicationUserDeleteDialogComponent,
        ApplicationUserDeletePopupComponent
    ],
    entryComponents: [
        ApplicationUserComponent,
        ApplicationUserUpdateComponent,
        ApplicationUserDeleteDialogComponent,
        ApplicationUserDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionApplicationUserModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
