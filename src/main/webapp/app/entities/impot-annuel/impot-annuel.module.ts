import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {
    ImpotAnnuelComponent,
    ImpotAnnuelDeleteDialogComponent,
    ImpotAnnuelDeletePopupComponent,
    ImpotAnnuelDetailComponent,
    impotAnnuelPopupRoute,
    impotAnnuelRoute,
    ImpotAnnuelUpdateComponent
} from './';

const ENTITY_STATES = [...impotAnnuelRoute, ...impotAnnuelPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImpotAnnuelComponent,
        ImpotAnnuelDetailComponent,
        ImpotAnnuelUpdateComponent,
        ImpotAnnuelDeleteDialogComponent,
        ImpotAnnuelDeletePopupComponent
    ],
    entryComponents: [ImpotAnnuelComponent, ImpotAnnuelUpdateComponent, ImpotAnnuelDeleteDialogComponent, ImpotAnnuelDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionImpotAnnuelModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
