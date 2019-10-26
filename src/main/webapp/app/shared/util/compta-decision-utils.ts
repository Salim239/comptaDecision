import * as moment from 'moment';

export default class ComptaDecisionUtils {

    static getPreviousYears(anneeCreation: number) {
        let previousYears = [];
        var currentYear = moment().year();
        for (let i = 0; i < (currentYear - anneeCreation); i++) {
            previousYears[i] = currentYear - (i + 1);
        }
        return previousYears;
    }
}