import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { ComptaDecisionSharedLibsModule, ComptaDecisionSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';
import {AnneeComponent} from "app/shared/annee/annee.component";
import {MoisComponent} from "app/shared/mois/mois.component";

@NgModule({
    imports: [ComptaDecisionSharedLibsModule, ComptaDecisionSharedCommonModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective, AnneeComponent, MoisComponent],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent],
    exports: [ComptaDecisionSharedCommonModule, JhiLoginModalComponent,AnneeComponent, MoisComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionSharedModule {
    static forRoot() {
        return {
            ngModule: ComptaDecisionSharedModule
        };
    }
}
