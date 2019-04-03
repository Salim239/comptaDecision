import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    ImpotMensuelComponent,
    ImpotMensuelDetailComponent,
    ImpotMensuelUpdateComponent,
    ImpotMensuelDeletePopupComponent,
    ImpotMensuelDeleteDialogComponent,
    impotMensuelRoute,
    impotMensuelPopupRoute
} from './';

const ENTITY_STATES = [...impotMensuelRoute, ...impotMensuelPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImpotMensuelComponent,
        ImpotMensuelDetailComponent,
        ImpotMensuelUpdateComponent,
        ImpotMensuelDeleteDialogComponent,
        ImpotMensuelDeletePopupComponent
    ],
    entryComponents: [
        ImpotMensuelComponent,
        ImpotMensuelUpdateComponent,
        ImpotMensuelDeleteDialogComponent,
        ImpotMensuelDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionImpotMensuelModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
