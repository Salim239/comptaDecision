import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {NgbDateAdapter} from '@ng-bootstrap/ng-bootstrap';

import {NgbDateMomentAdapter} from './util/datepicker-adapter';
import {DigitOnlyDirective} from 'app/shared/directives/digit-only.directive';
import {SelectedRowDirective} from 'app/shared/directives/select-row.directive';
import {
    ComptaDecisionSharedCommonModule,
    ComptaDecisionSharedLibsModule,
    HasAnyAuthorityDirective,
    JhiLoginModalComponent
} from './';
import {AnneeComponent} from 'app/shared/annee/annee.component';
import {MoisComponent} from 'app/shared/mois/mois.component';
import {NewEntryComponent} from 'app/shared/new-entry-form/new-entry.component';
import {DecimalFormatterDirective} from 'app/shared/directives/decimal-formatter-directive';
import {Ng2SmartTableModule} from 'ng2-smart-table';

@NgModule({
    imports: [ComptaDecisionSharedLibsModule, ComptaDecisionSharedCommonModule, Ng2SmartTableModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective, AnneeComponent, MoisComponent, NewEntryComponent, DigitOnlyDirective, SelectedRowDirective, DecimalFormatterDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent],
    exports: [ComptaDecisionSharedCommonModule, JhiLoginModalComponent, AnneeComponent, MoisComponent, NewEntryComponent, HasAnyAuthorityDirective, DigitOnlyDirective, SelectedRowDirective, DecimalFormatterDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComptaDecisionSharedModule {
    static forRoot() {
        return {
            ngModule: ComptaDecisionSharedModule
        };
    }
}
