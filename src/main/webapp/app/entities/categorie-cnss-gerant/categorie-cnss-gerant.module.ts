import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    CategorieCnssGerantComponent,
    CategorieCnssGerantDeleteDialogComponent,
    CategorieCnssGerantDeletePopupComponent,
    CategorieCnssGerantDetailComponent,
    categorieCnssGerantPopupRoute,
    categorieCnssGerantRoute,
    CategorieCnssGerantUpdateComponent
} from './';

const ENTITY_STATES = [...categorieCnssGerantRoute, ...categorieCnssGerantPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CategorieCnssGerantComponent,
        CategorieCnssGerantDetailComponent,
        CategorieCnssGerantUpdateComponent,
        CategorieCnssGerantDeleteDialogComponent,
        CategorieCnssGerantDeletePopupComponent
    ],
    entryComponents: [
        CategorieCnssGerantComponent,
        CategorieCnssGerantUpdateComponent,
        CategorieCnssGerantDeleteDialogComponent,
        CategorieCnssGerantDeletePopupComponent
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
