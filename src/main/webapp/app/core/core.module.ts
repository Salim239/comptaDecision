import { LOCALE_ID, NgModule } from '@angular/core';
import { DatePipe, registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Title } from '@angular/platform-browser';
import locale from '@angular/common/locales/fr';

import { FindLanguageFromKeyPipe } from 'app/shared';
import { NgbDateParserFormatter, NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';
import { CustomDateParserFormatter } from 'app/shared/util/datepicker-formatter';
import { CustomDatepickerI18n, I18n } from 'app/shared/util/datepicker-i18n';

@NgModule({
    imports: [HttpClientModule],
    exports: [],
    declarations: [],
    providers: [
        Title,
        {
            provide: LOCALE_ID,
            useValue: 'fr'
        },
        { provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter },
        I18n,
        { provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n },
        FindLanguageFromKeyPipe,
        DatePipe
    ]
})
export class ComptaDecisionCoreModule {
    constructor() {
        registerLocaleData(locale);
    }
}
