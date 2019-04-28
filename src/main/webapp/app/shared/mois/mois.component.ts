import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import ComptaDecisionUtils from "app/shared/util/compta-decision-utils";
import {IFicheClient} from "app/shared/model/fiche-client.model";
import {Moment} from "moment";
import * as _ from 'lodash';
import moment = require("moment");

@Component({
    selector: 'app-mois',
    templateUrl: './mois.component.html'
})

export class MoisComponent implements OnInit {

    @Input() model: any;
    @Input() form: any;
    @Output() moisChange = new EventEmitter();


    constructor() {
    }

    ngOnInit() {
    }

    onChange() {
        this.moisChange.emit();
    }


}
