export default class ComptaDecisionUtils {

    static getPreviousYears(anneeCreation: number, currentYear: number) {
        let previousYears = [];
        for (let i = 0; i < (currentYear - anneeCreation); i++) {
            previousYears[i] = currentYear - (i + 1);
        }
        return previousYears;
    }
}