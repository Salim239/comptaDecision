import ComptaDecisionUtils from "app/shared/util/compta-decision-utils";
import {IFicheClient} from "app/shared/model/fiche-client.model";
import * as moment from "moment";
import {Component, Input, OnInit} from "@angular/core";

@Component({
    selector: 'app-annee',
    templateUrl: './annee.component.html'
})

export class AnneeComponent implements OnInit {

    selectedYear: number;
    years: number[];
    @Input() displayType: string; //row or empty
    @Input() model: any;
    @Input() form: any;


    constructor() {
    }

    ngOnInit() {
        this.getAnnesDeclaration(this.model.ficheClient);
    }

    private getAnnesDeclaration(ficheclient: IFicheClient) {
        if (ficheclient && ficheclient.id && ficheclient.dateCreation) {
                this.years = ComptaDecisionUtils.getPreviousYears(moment(ficheclient.dateCreation).year());
        } else {
            this.selectedYear = moment().year();
            this.years = [this.selectedYear];
        }
    }


}
