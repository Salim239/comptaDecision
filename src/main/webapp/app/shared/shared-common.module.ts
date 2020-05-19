import {NgModule} from '@angular/core';

import {ComptaDecisionSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent} from './';
import {BusinessAlertComponent} from "app/shared/business-alert/business-alert.component";

@NgModule({
    imports: [ComptaDecisionSharedLibsModule],
    declarations: [FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, BusinessAlertComponent],
    exports: [ComptaDecisionSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, BusinessAlertComponent]
})
export class ComptaDecisionSharedCommonModule {}
