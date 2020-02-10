import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    DeclarationAnnuelleComponent,
    DeclarationAnnuelleDetailComponent,
    DeclarationAnnuelleUpdateComponent,
    DeclarationAnnuelleDeletePopupComponent,
    DeclarationAnnuelleDeleteDialogComponent,
    declarationAnnuelleRoute,
    declarationAnnuellePopupRoute
} from './';

const ENTITY_STATES = [...declarationAnnuelleRoute, ...declarationAnnuellePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DeclarationAnnuelleComponent,
        DeclarationAnnuelleDetailComponent,
        DeclarationAnnuelleUpdateComponent,
        DeclarationAnnuelleDeleteDialogComponent,
        DeclarationAnnuelleDeletePopupComponent
    ],
    entryComponents: [
        DeclarationAnnuelleComponent,
        DeclarationAnnuelleUpdateComponent,
        DeclarationAnnuelleDeleteDialogComponent,
        DeclarationAnnuelleDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionDeclarationAnnuelleModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
