import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    FicheClientComponent,
    FicheClientDetailComponent,
    FicheClientUpdateComponent,
    FicheClientDeletePopupComponent,
    FicheClientDeleteDialogComponent,
    ficheClientRoute,
    ficheClientPopupRoute
} from './';

const ENTITY_STATES = [...ficheClientRoute, ...ficheClientPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FicheClientComponent,
        FicheClientDetailComponent,
        FicheClientUpdateComponent,
        FicheClientDeleteDialogComponent,
        FicheClientDeletePopupComponent
    ],
    entryComponents: [FicheClientComponent, FicheClientUpdateComponent, FicheClientDeleteDialogComponent, FicheClientDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionFicheClientModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
