import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    ActiviteComponent,
    ActiviteDetailComponent,
    ActiviteUpdateComponent,
    ActiviteDeletePopupComponent,
    ActiviteDeleteDialogComponent,
    activiteRoute,
    activitePopupRoute
} from './';

const ENTITY_STATES = [...activiteRoute, ...activitePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ActiviteComponent,
        ActiviteDetailComponent,
        ActiviteUpdateComponent,
        ActiviteDeleteDialogComponent,
        ActiviteDeletePopupComponent
    ],
    entryComponents: [ActiviteComponent, ActiviteUpdateComponent, ActiviteDeleteDialogComponent, ActiviteDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionActiviteModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
