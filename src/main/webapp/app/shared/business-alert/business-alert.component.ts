import {Component, Input} from '@angular/core';
import {BusinessAlert} from "app/shared/model/business-alert.model";

@Component({
    selector: 'jhi-business-alert',
    templateUrl: './business-alert.html'
})
export class BusinessAlertComponent  {

    @Input() businessAlerts: BusinessAlert[];

    // alerts: BusinessAlert[];

    constructor() {
        // this.alerts = _.remove(this.businessAlerts, function(businessAlert) {
        //     return businessAlert === null || businessAlert === undefined;
        // });
        // this.reset();

    }

    close(alert: BusinessAlert) {
        this.businessAlerts.splice(this.businessAlerts.indexOf(alert), 1);
    }


    // reset() {
    //     this.businessAlerts = Array.from(this.businessAlerts);
    // }


}
