import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    CnssComponent,
    CnssDetailComponent,
    CnssUpdateComponent,
    CnssDeletePopupComponent,
    CnssDeleteDialogComponent,
    cnssRoute,
    cnssPopupRoute
} from './';

const ENTITY_STATES = [...cnssRoute, ...cnssPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CnssComponent, CnssDetailComponent, CnssUpdateComponent, CnssDeleteDialogComponent, CnssDeletePopupComponent],
    entryComponents: [CnssComponent, CnssUpdateComponent, CnssDeleteDialogComponent, CnssDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionCnssModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
