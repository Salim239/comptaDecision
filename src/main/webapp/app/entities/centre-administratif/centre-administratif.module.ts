import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ComptaDecisionSharedModule } from 'app/shared';
import {} from './';
import { centreAdministratifPopupRoute, centreAdministratifRoute } from 'app/entities/centre-administratif/centre-administratif.route';
import { CentreAdministratifComponent } from 'app/entities/centre-administratif/centre-administratif.component';
import { CentreAdministratifLigneComponent } from 'app/entities/centre-administratif/centre-administratif-detail.component';
import {
    CentreAdministratifDeleteDialogComponent,
    CentreAdministratifDeletePopupComponent
} from 'app/entities/centre-administratif/centre-administratif-delete-dialog.component';
import { CentreAdministratifUpdateComponent } from 'app/entities/centre-administratif/centre-administratif-update.component';

const ENTITY_STATES = [...centreAdministratifRoute, ...centreAdministratifPopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CentreAdministratifComponent,
        CentreAdministratifLigneComponent,
        CentreAdministratifUpdateComponent,
        CentreAdministratifDeleteDialogComponent,
        CentreAdministratifDeletePopupComponent
    ],
    entryComponents: [
        CentreAdministratifComponent,
        CentreAdministratifUpdateComponent,
        CentreAdministratifDeleteDialogComponent,
        CentreAdministratifDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionCentreAdministratifModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
