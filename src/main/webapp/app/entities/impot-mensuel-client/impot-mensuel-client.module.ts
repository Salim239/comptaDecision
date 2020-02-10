import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    ImpotMensuelClientComponent,
    ImpotMensuelClientDetailComponent,
    ImpotMensuelClientUpdateComponent,
    ImpotMensuelClientDeletePopupComponent,
    ImpotMensuelClientDeleteDialogComponent,
    impotMensuelClientRoute,
    impotMensuelClientPopupRoute
} from './';

const ENTITY_STATES = [...impotMensuelClientRoute, ...impotMensuelClientPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImpotMensuelClientComponent,
        ImpotMensuelClientDetailComponent,
        ImpotMensuelClientUpdateComponent,
        ImpotMensuelClientDeleteDialogComponent,
        ImpotMensuelClientDeletePopupComponent
    ],
    entryComponents: [
        ImpotMensuelClientComponent,
        ImpotMensuelClientUpdateComponent,
        ImpotMensuelClientDeleteDialogComponent,
        ImpotMensuelClientDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionImpotMensuelClientModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
