import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
    selector: '[jhiDigitOnly]'
})
export class DigitOnlyDirective {
    private decimalCounter = 0;
    private negativeCounter = 0;
    private navigationKeys = [
        'Backspace',
        'Delete',
        'Tab',
        'Escape',
        'Enter',
        'Home',
        'End',
        'ArrowLeft',
        'ArrowRight',
        'Clear',
        'Copy',
        'Paste'
    ];
    @Input() negativeAmountAccepted? = false;
    @Input() decimal? = false;
    @Input() decimalSeparator? = '.'; //we replace automatically , by .
    inputElement: HTMLInputElement;
    startOperator: string = '';

    constructor(public el: ElementRef) {
        this.inputElement = el.nativeElement;
    }

    @HostListener('keydown', ['$event'])
    onKeyDown(e: KeyboardEvent) {
        if (
            this.navigationKeys.indexOf(e.key) > -1 || // Allow: navigation keys: backspace, delete, arrows etc.
            (e.key === 'a' && e.ctrlKey === true) || // Allow: Ctrl+A
            (e.key === 'c' && e.ctrlKey === true) || // Allow: Ctrl+C
            (e.key === 'v' && e.ctrlKey === true) || // Allow: Ctrl+V
            (e.key === 'x' && e.ctrlKey === true) || // Allow: Ctrl+X
            (e.key === 'a' && e.metaKey === true) || // Allow: Cmd+A (Mac)
            (e.key === 'c' && e.metaKey === true) || // Allow: Cmd+C (Mac)
            (e.key === 'v' && e.metaKey === true) || // Allow: Cmd+V (Mac)
            (e.key === 'x' && e.metaKey === true) || // Allow: Cmd+X (Mac)
            (this.negativeAmountAccepted && e.key === '-') || // Allow: Cmd+X (Mac)
            (this.decimal && e.key === this.decimalSeparator && this.decimalCounter < 1) || // Allow: only one decimal point
            (this.decimal && e.key === ',' && this.decimalCounter < 1) // Allow: only one decimal point
        ) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if (e.key === ' ' || isNaN(Number(e.key))) {
            e.preventDefault();
        }
    }

    @HostListener('keyup', ['$event'])
    onKeyUp(e: KeyboardEvent) {
        if (!this.decimal && !this.negativeAmountAccepted) {
            return;
        } else {
            if (this.decimal) {
                this.el.nativeElement.value = this.el.nativeElement.value.replace(',', '.');
                this.decimalCounter = this.el.nativeElement.value.split(this.decimalSeparator).length - 1;
            }
            if (this.negativeAmountAccepted) {
                this.negativeCounter = this.el.nativeElement.value.split('-').length - 1;
            }
        }
    }

    @HostListener('paste', ['$event'])
    onPaste(event: ClipboardEvent) {
        const pastedInput: string = event.clipboardData.getData('text/plain');
        this.pasteData(pastedInput);
        event.preventDefault();
    }

    @HostListener('drop', ['$event'])
    onDrop(event: DragEvent) {
        const textData = event.dataTransfer.getData('text');
        this.inputElement.focus();
        this.pasteData(textData);
        event.preventDefault();
    }

    private pasteData(pastedContent: string): void {
        const sanitizedContent = this.sanatizeInput(pastedContent);
        const pasted = document.execCommand('insertText', false, sanitizedContent);
        if (!pasted) {
            const { selectionStart: start, selectionEnd: end } = this.inputElement;
            this.inputElement.setRangeText(sanitizedContent, start, end, 'end');
        }
    }

    private sanatizeInput(input: string): string {
        //start operator
        const startOperator = this.negativeAmountAccepted ? '\\-' : '';

        const regex =
            this.decimal && this.isValidDecimal(input)
                ? new RegExp(`[^${startOperator}(0-9)${this.decimalSeparator}]`, 'g')
                : new RegExp(`[^${startOperator}(0-9)]`, 'g');

        let result = input.replace(regex, '');

        const maxLength = this.inputElement.maxLength;
        if (maxLength > 0) {
            // the input element has maxLength limit
            const allowedLength = maxLength - this.inputElement.value.length;
            result = allowedLength > 0 ? result.substring(0, allowedLength) : '';
        }
        return result;
    }

    private isValidDecimal(string: string): boolean {
        return string.split(this.decimalSeparator).length <= 2;
    }
}
