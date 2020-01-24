import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {NgbDateAdapter} from '@ng-bootstrap/ng-bootstrap';

import {NgbDateMomentAdapter} from './util/datepicker-adapter';
import {
    ComptaDecisionSharedCommonModule,
    ComptaDecisionSharedLibsModule,
    HasAnyAuthorityDirective,
    JhiLoginModalComponent
} from './';
import {AnneeComponent} from "app/shared/annee/annee.component";
import {MoisComponent} from "app/shared/mois/mois.component";
import {NewEntryComponent} from "app/shared/new-entry-form/new-entry.component";

@NgModule({
    imports: [ComptaDecisionSharedLibsModule, ComptaDecisionSharedCommonModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective, AnneeComponent, MoisComponent, NewEntryComponent],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent],
    exports: [ComptaDecisionSharedCommonModule, JhiLoginModalComponent, AnneeComponent, MoisComponent, NewEntryComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionSharedModule {
    static forRoot() {
        return {
            ngModule: ComptaDecisionSharedModule
        };
    }
}
