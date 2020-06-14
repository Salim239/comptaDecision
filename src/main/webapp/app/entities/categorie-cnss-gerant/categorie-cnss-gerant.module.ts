import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    CategorieCnssGerantComponent,
    CategorieCnssGerantDetailComponent,
    CategorieCnssGerantUpdateComponent,
    categorieCnssGerantDeletePopupComponent,
    CategorieCnssGerantDeleteDialogComponent,
    categorieCnssGerantRoute,
    categorieCnssGerantPopupRoute
} from './';

const ENTITY_STATES = [...categorieCnssGerantRoute, ...categorieCnssGerantPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CategorieCnssGerantComponent,
        CategorieCnssGerantDetailComponent,
        CategorieCnssGerantUpdateComponent,
        CategorieCnssGerantDeleteDialogComponent,
        categorieCnssGerantDeletePopupComponent
    ],
    entryComponents: [
        CategorieCnssGerantComponent,
        CategorieCnssGerantUpdateComponent,
        CategorieCnssGerantDeleteDialogComponent,
        categorieCnssGerantDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionCategorieCnssGerantModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
