import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    SecteurActiviteComponent,
    SecteurActiviteDetailComponent,
    SecteurActiviteUpdateComponent,
    SecteurActiviteDeletePopupComponent,
    SecteurActiviteDeleteDialogComponent,
    secteurActiviteRoute,
    secteurActivitePopupRoute
} from './';

const ENTITY_STATES = [...secteurActiviteRoute, ...secteurActivitePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SecteurActiviteComponent,
        SecteurActiviteDetailComponent,
        SecteurActiviteUpdateComponent,
        SecteurActiviteDeleteDialogComponent,
        SecteurActiviteDeletePopupComponent
    ],
    entryComponents: [
        SecteurActiviteComponent,
        SecteurActiviteUpdateComponent,
        SecteurActiviteDeleteDialogComponent,
        SecteurActiviteDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionSecteurActiviteModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
