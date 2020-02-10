import * as moment from 'moment';
import {accounting} from 'accounting';

export default class ComptaDecisionUtils {

    static getPreviousYears(anneeCreation: number) {
        let previousYears = [];
        var currentYear = moment().year();
        previousYears.push(currentYear);
        for (let i = 1; i <= (currentYear - anneeCreation); i++) {
            previousYears.push(currentYear - i);
        }
        return previousYears;
    }

    static formatCurrency(value) {
        if (!value) value = 0;
        return accounting.formatMoney( (""+value), '', 3, ' ', ',');
    }

    static parseCurrency(value) {
        if (!value) return 0;
        return +(("" + value).replace(",", "."));
    }
}