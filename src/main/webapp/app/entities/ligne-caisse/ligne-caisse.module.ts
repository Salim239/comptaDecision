import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from 'app/core';

import {ComptaDecisionSharedModule} from 'app/shared';
import {
    LigneCaisseComponent,
    LigneCaisseDeleteDialogComponent,
    LigneCaisseDeletePopupComponent,
    LigneCaisseDetailComponent,
    ligneCaissePopupRoute,
    ligneCaisseRoute,
    LigneCaisseUpdateComponent
} from './';

const ENTITY_STATES = [...ligneCaisseRoute, ...ligneCaissePopupRoute];

@NgModule({
    imports: [ComptaDecisionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LigneCaisseComponent,
        LigneCaisseDetailComponent,
        LigneCaisseUpdateComponent,
        LigneCaisseDeleteDialogComponent,
        LigneCaisseDeletePopupComponent
    ],
    entryComponents: [LigneCaisseComponent, LigneCaisseUpdateComponent, LigneCaisseDeleteDialogComponent, LigneCaisseDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionLigneCaisseModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
