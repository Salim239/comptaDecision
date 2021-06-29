import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    PaiementComponent,
    PaiementDeleteDialogComponent,
    PaiementDeletePopupComponent,
    PaiementDetailComponent,
    paiementPopupRoute,
    paiementRoute,
    PaiementUpdateComponent
} from './';

const ENTITY_STATES = [...paiementRoute, ...paiementPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PaiementComponent,
        PaiementDetailComponent,
        PaiementUpdateComponent,
        PaiementDeleteDialogComponent,
        PaiementDeletePopupComponent
    ],
    entryComponents: [PaiementComponent, PaiementUpdateComponent, PaiementDeleteDialogComponent, PaiementDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionPaiementModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
