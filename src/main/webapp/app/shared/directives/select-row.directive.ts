import {Directive, Input, HostListener, ElementRef, OnInit, HostBinding} from '@angular/core';
import { NgModel } from '@angular/forms';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';

@Directive({
    selector: '[jhiSelectedRow]'
})

export class SelectedRowDirective implements OnInit {

    private el: HTMLInputElement;
    @HostBinding('style.color') color: string;
    @HostBinding('style.background-color') backgroundColor: string;

    constructor(private elementRef: ElementRef) {
    }

    ngOnInit() {
    }

    @HostListener('click') newColor() {
        if (this.color === 'black') {
            this.color = 'white';
        } else {
            this.color = 'white';
        }
        if (this.backgroundColor == 'black') {
            this.backgroundColor = 'grey';
        } else {
            this.backgroundColor = 'black'
        }
    }
}
