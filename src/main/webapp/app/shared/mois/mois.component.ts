import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: 'jhi-app-mois',
    templateUrl: './mois.component.html'
})

export class MoisComponent implements OnInit {

    @Input() model: any;
    @Input() form: any;
    @Input() displayType: string;
    @Output() moisChange = new EventEmitter();
    @Input() disabled: boolean;

    constructor() {
    }

    ngOnInit() {
    }

    onChange() {
        this.moisChange.emit();
    }
}
