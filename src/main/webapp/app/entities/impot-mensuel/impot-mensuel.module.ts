import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import { ImpotMensuelLineComponent } from './impot-mensuel-line/impot-mensuel-line.component';
import {
    ImpotMensuelComponent,
    ImpotMensuelDeleteDialogComponent,
    ImpotMensuelDeletePopupComponent,
    ImpotMensuelLigneComponent,
    impotMensuelPopupRoute,
    impotMensuelRoute,
    ImpotMensuelUpdateComponent
} from './';

const ENTITY_STATES = [...impotMensuelRoute, ...impotMensuelPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImpotMensuelComponent,
        ImpotMensuelLigneComponent,
        ImpotMensuelUpdateComponent,
        ImpotMensuelDeleteDialogComponent,
        ImpotMensuelDeletePopupComponent,
        ImpotMensuelLineComponent
    ],
    entryComponents: [
        ImpotMensuelComponent,
        ImpotMensuelUpdateComponent,
        ImpotMensuelDeleteDialogComponent,
        ImpotMensuelDeletePopupComponent,
        ImpotMensuelLineComponent
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
