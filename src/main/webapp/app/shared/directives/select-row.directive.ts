import {Directive, ElementRef, HostBinding, HostListener, OnInit} from '@angular/core';

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
        // const checkbox = this.elementRef.nativeElement.;
        // if (checkbox.isSelected) {
        //     checkbox.setAttribute('selected', 'selected');
        // } else {
        //     checkbox.removeAttribute('selected')
        // }
        // if (this.color === 'white') {
        //     this.color = '#2fa4e7';
        // } else {
        //     this.color = 'white';
        // }
        // if (this.backgroundColor == 'white') {
        //     this.backgroundColor = '#2fa4e7';
        // } else {
        //     this.backgroundColor = 'white'
        // }
        // _.each(this.elementRef.nativeElement.closest('tr'), function(tr) {
        //     tr.this.backgroundColor = 'white';
        //     tr.color = 'black';
        // });
    }
}
