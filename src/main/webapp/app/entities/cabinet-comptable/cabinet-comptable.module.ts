import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from 'app/core';

import {ComptaDecisionSharedModule} from 'app/shared';
import {
    CabinetComptableComponent,
    CabinetComptableDeleteDialogComponent,
    CabinetComptableDeletePopupComponent,
    CabinetComptableDetailComponent,
    cabinetComptablePopupRoute,
    cabinetComptableRoute,
    CabinetComptableUpdateComponent
} from './';

const ENTITY_STATES = [...cabinetComptableRoute, ...cabinetComptablePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CabinetComptableComponent,
        CabinetComptableDetailComponent,
        CabinetComptableUpdateComponent,
        CabinetComptableDeleteDialogComponent,
        CabinetComptableDeletePopupComponent
    ],
    entryComponents: [
        CabinetComptableComponent,
        CabinetComptableUpdateComponent,
        CabinetComptableDeleteDialogComponent,
        CabinetComptableDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionCabinetComptableModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
