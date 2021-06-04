import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    AcompteProvisionnelComponent,
    AcompteProvisionnelLigneComponent,
    AcompteProvisionnelUpdateComponent,
    AcompteProvisionnelDeletePopupComponent,
    AcompteProvisionnelDeleteDialogComponent,
    acompteProvisionnelRoute,
    acompteProvisionnelPopupRoute
} from './';

const ENTITY_STATES = [...acompteProvisionnelRoute, ...acompteProvisionnelPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AcompteProvisionnelComponent,
        AcompteProvisionnelLigneComponent,
        AcompteProvisionnelUpdateComponent,
        AcompteProvisionnelDeleteDialogComponent,
        AcompteProvisionnelDeletePopupComponent
    ],
    entryComponents: [
        AcompteProvisionnelComponent,
        AcompteProvisionnelUpdateComponent,
        AcompteProvisionnelDeleteDialogComponent,
        AcompteProvisionnelDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionAcompteProvisionnelModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
