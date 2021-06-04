import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    VilleComponent,
    VilleLigneComponent,
    VilleUpdateComponent,
    VilleDeletePopupComponent,
    VilleDeleteDialogComponent,
    villeRoute,
    villePopupRoute
} from './';

const ENTITY_STATES = [...villeRoute, ...villePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [VilleComponent, VilleLigneComponent, VilleUpdateComponent, VilleDeleteDialogComponent, VilleDeletePopupComponent],
    entryComponents: [VilleComponent, VilleUpdateComponent, VilleDeleteDialogComponent, VilleDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionVilleModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
