import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    DeclarationEmployeurAnnuelleComponent,
    DeclarationEmployeurAnnuelleDetailComponent,
    DeclarationEmployeurAnnuelleUpdateComponent,
    DeclarationEmployeurAnnuelleDeletePopupComponent,
    DeclarationEmployeurAnnuelleDeleteDialogComponent,
    declarationEmployeurAnnuelleRoute,
    declarationEmployeurAnnuellePopupRoute
} from './';

const ENTITY_STATES = [...declarationEmployeurAnnuelleRoute, ...declarationEmployeurAnnuellePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DeclarationEmployeurAnnuelleComponent,
        DeclarationEmployeurAnnuelleDetailComponent,
        DeclarationEmployeurAnnuelleUpdateComponent,
        DeclarationEmployeurAnnuelleDeleteDialogComponent,
        DeclarationEmployeurAnnuelleDeletePopupComponent
    ],
    entryComponents: [
        DeclarationEmployeurAnnuelleComponent,
        DeclarationEmployeurAnnuelleUpdateComponent,
        DeclarationEmployeurAnnuelleDeleteDialogComponent,
        DeclarationEmployeurAnnuelleDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionDeclarationEmployeurAnnuelleModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
