import {Component, Input, OnInit, Output} from "@angular/core";
import ComptaDecisionUtils from "app/shared/util/compta-decision-utils";
import {IFicheClient} from "app/shared/model/fiche-client.model";
import {Moment} from "moment";
import * as _ from 'lodash';
import moment = require("moment");

@Component({
    selector: 'app-annee',
    templateUrl: './annee.component.html'
})

export class AnneeComponent implements OnInit {

    currentYear: number;
    previousYears: number[];
    @Input() ficheClients: IFicheClient[];
    @Input() currentFicheClientId: number;
    @Input() currentFichClientDateCreation: Moment;
    @Input() model: any;
    @Input() form: any;


    constructor() {
    }

    ngOnInit() {
        this.currentYear = moment().year();
        this.getAnnesDeclaration(this.ficheClients, this.currentFicheClientId, this.currentFichClientDateCreation);
    }

    private getAnnesDeclaration(ficheclients: IFicheClient[], currentFicheClientId: number, currentFichClientDateCreation: Moment) {
        if (currentFicheClientId) {
            let ficheClient = _.find(ficheclients, function (ficheClient) {
                return currentFicheClientId === ficheClient.id;
            });
            if (ficheClient && ficheClient.dateCreation) {
                this.previousYears = ComptaDecisionUtils.getPreviousYears(moment(currentFichClientDateCreation).year(), this.currentYear);
            }
        } else {
            this.previousYears = ComptaDecisionUtils.getPreviousYears(this.currentYear - 5, this.currentYear);
        }
    }


}
