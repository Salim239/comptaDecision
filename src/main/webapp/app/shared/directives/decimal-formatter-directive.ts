import { Directive, Input, HostListener, ElementRef, OnInit } from "@angular/core";
import { NgModel } from "@angular/forms";
import ComptaDecisionUtils from "app/shared/util/compta-decision-utils";



@Directive({
    selector: "[decimalFormatter][ngModel]",
    providers: [NgModel]
})
export class DecimalFormatterDirective implements OnInit {

    private el: HTMLInputElement;
    private decimals: string;

    constructor(private elementRef: ElementRef,
                private ngModel: NgModel) {

        this.el = this.elementRef.nativeElement;

    }

    ngOnInit() {

        console.log(this.el.value, this.decimals);
        this.el.value = ComptaDecisionUtils.formatCurrency(this.el.value);
    }

    @HostListener("focus", ["$event.target.value"])
    onFocus(value) {

        console.log(this.el.value, this.decimals);
        this.el.value = this.ngModel.viewModel; //Display the raw value on the model
    }

    @HostListener("blur", ["$event.target.value"])
    onBlur(value) {

        console.log(this.el.value, this.decimals);
        this.el.value = ComptaDecisionUtils.formatCurrency(this.el.value);
        this.ngModel.viewModel = ComptaDecisionUtils.parseCurrency(this.ngModel.viewModel );
    }

}