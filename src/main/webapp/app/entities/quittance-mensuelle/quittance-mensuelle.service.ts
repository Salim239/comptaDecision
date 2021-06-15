import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_FORMAT} from 'app/shared/constants/input.constants';
import {filter, map} from 'rxjs/operators';
import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IQuittanceMensuelle, QuittanceMensuelle} from 'app/shared/model/quittance-mensuelle.model';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';
import * as _ from 'lodash';
import {IQuittanceMensuelleLigne} from 'app/shared/model/quittance-mensuelle-ligne.model';
import {IQuittanceMensuelleSousLigne} from 'app/shared/model/quittance-mensuelle-sous-ligne.model';

type EntityResponseType = HttpResponse<IQuittanceMensuelle>;
type EntityArrayResponseType = HttpResponse<IQuittanceMensuelle[]>;

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleService {
    public resourceUrl = SERVER_API_URL + 'api/quittance-mensuelles';

    constructor(protected http: HttpClient) {}

    initEmpty(id, annee, mois, typeDeclaration): Observable<EntityResponseType> {
        return this.http
            .get<IQuittanceMensuelle>(this.resourceUrl + `/init/${id}/${annee}/${mois}/${typeDeclaration}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    initByParams(ficheClientId: number): Observable<EntityResponseType> {
        return this.http
            .get<IQuittanceMensuelle>(`${this.resourceUrl}/ficheClient/${ficheClientId}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    create(quittanceMensuelle: IQuittanceMensuelle): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(quittanceMensuelle);
        return this.http
            .post<IQuittanceMensuelle>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(quittanceMensuelle: IQuittanceMensuelle): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(quittanceMensuelle);
        return this.http
            .put<IQuittanceMensuelle>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IQuittanceMensuelle>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // print(id: number): Observable<EntityResponseType> {
    //
    //     return this.http
    //         .get<any>(`${this.resourceUrl}/${id}/print`, { observe: 'response',
    //         responseType: 'Blob'});
    // }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IQuittanceMensuelle[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(quittanceMensuelle: IQuittanceMensuelle): IQuittanceMensuelle {
        const copy: IQuittanceMensuelle = Object.assign({}, quittanceMensuelle, {
            datePaiement:
                quittanceMensuelle.datePaiement != null && quittanceMensuelle.datePaiement.isValid()
                    ? quittanceMensuelle.datePaiement.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datePaiement = res.body.datePaiement != null ? moment(res.body.datePaiement) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((quittanceMensuelle: IQuittanceMensuelle) => {
                quittanceMensuelle.datePaiement = quittanceMensuelle.datePaiement != null ? moment(quittanceMensuelle.datePaiement) : null;
            });
        }
        return res;
    }

    public save(quittance: IQuittanceMensuelle): Observable<IQuittanceMensuelle> {
        quittance = this.parseMontants(quittance);
        return quittance.id
            ? this.update(quittance).pipe(
                  filter((response: HttpResponse<QuittanceMensuelle>) => response.ok),
                  map((quittanceMensuelle: HttpResponse<QuittanceMensuelle>) => this.formatMontants(quittanceMensuelle.body))
              )
            : this.create(quittance).pipe(
                  filter((response: HttpResponse<QuittanceMensuelle>) => response.ok),
                  map((quittanceMensuelle: HttpResponse<QuittanceMensuelle>) => this.formatMontants(quittanceMensuelle.body))
              );
    }

    private findSousLigneByCode(codeSousLigne: string, quittanceLigne: IQuittanceMensuelleLigne): IQuittanceMensuelleSousLigne {
        return _.find(quittanceLigne.quittanceMensuelleSousLignes, function(sousLigne) {
            return sousLigne.impotMensuelLigneCode === codeSousLigne;
        });
    }

    public updateQuittanceModel(target: IQuittanceMensuelle, source: IQuittanceMensuelle) {
        const that = this;
        target.id = source.id;
        target.montantTotal = source.montantTotal;
        _.each(target.quittanceMensuelleLignes, function(quittanceLigne) {
            const quittanceLigneRes = _.find(source.quittanceMensuelleLignes, function(detail) {
                return detail.code === quittanceLigne.code;
            });
            quittanceLigne.id = quittanceLigneRes.id;
            quittanceLigne.montantTotal = quittanceLigneRes.montantTotal;
            quittanceLigne.montantReport = quittanceLigneRes.montantReport;
            quittanceLigne.montantReportCalc = quittanceLigneRes.montantReportCalc;
            _.each(quittanceLigne.quittanceMensuelleSousLignes, function(quittanceSousLigne) {
                const resSousLigne = that.findSousLigneByCode(quittanceSousLigne.impotMensuelLigneCode, quittanceLigneRes);
                quittanceSousLigne.id = resSousLigne.id;
                quittanceSousLigne.montantBase = resSousLigne.montantBase;
                quittanceSousLigne.montantTotal = resSousLigne.montantTotal;
            });
            if (quittanceLigne.parent) {
                _.each(quittanceLigne.childQuittanceMensuelleLignes, function(quittanceLigneChild) {
                    let quittanceLigneChildRes;
                    for (const detailRes of source.quittanceMensuelleLignes) {
                        quittanceLigneChildRes = _.find(detailRes.childQuittanceMensuelleLignes, function(child) {
                            return child.code === quittanceLigneChild.code;
                        });
                        if (quittanceLigneChildRes) break;
                    }
                    quittanceLigneChild.id = quittanceLigneChildRes.id;
                    quittanceLigneChild.montantTotal = quittanceLigneChildRes.montantTotal;
                    quittanceLigneChild.montantReport = quittanceLigneChildRes.montantReport;
                    quittanceLigneChild.montantReportCalc = quittanceLigneChildRes.montantReportCalc;
                    _.each(quittanceLigneChild.quittanceMensuelleSousLignes, function(quittanceSousLigne) {
                        const resSousLigne = that.findSousLigneByCode(quittanceSousLigne.impotMensuelLigneCode, quittanceLigneChildRes);
                        quittanceSousLigne.id = resSousLigne.id;
                        quittanceSousLigne.montantBase = resSousLigne.montantBase;
                        quittanceSousLigne.montantTotal = resSousLigne.montantTotal;
                    });
                });
            }
        });
    }

    private parseMontantsLigne(quittanceMensuelleLigne: IQuittanceMensuelleLigne) {
        quittanceMensuelleLigne.montantTotal = ComptaDecisionUtils.parseCurrency(quittanceMensuelleLigne.montantTotal);
        if (quittanceMensuelleLigne.appliquerReportMontant) {
            quittanceMensuelleLigne.montantReport = ComptaDecisionUtils.parseCurrency(quittanceMensuelleLigne.montantReport);
            quittanceMensuelleLigne.montantReportCalc = ComptaDecisionUtils.parseCurrency(quittanceMensuelleLigne.montantReportCalc);
        }
        _.each(quittanceMensuelleLigne.quittanceMensuelleSousLignes, function(quittanceMensuelleSousLigne) {
            quittanceMensuelleSousLigne.montantBase = ComptaDecisionUtils.parseCurrency(quittanceMensuelleSousLigne.montantBase);
            quittanceMensuelleSousLigne.montantTotal = ComptaDecisionUtils.parseCurrency(quittanceMensuelleSousLigne.montantTotal);
        });
    }

    private formatMontantsLigne(quittanceMensuelleLigne: IQuittanceMensuelleLigne) {
        quittanceMensuelleLigne.montantTotal = ComptaDecisionUtils.formatCurrency(quittanceMensuelleLigne.montantTotal);
        if (quittanceMensuelleLigne.appliquerReportMontant) {
            quittanceMensuelleLigne.montantReport = ComptaDecisionUtils.formatCurrency(quittanceMensuelleLigne.montantReport);
            quittanceMensuelleLigne.montantReportCalc = ComptaDecisionUtils.formatCurrency(quittanceMensuelleLigne.montantReportCalc);
        }
        _.each(quittanceMensuelleLigne.quittanceMensuelleSousLignes, function(quittanceMensuelleSousLigne) {
            quittanceMensuelleSousLigne.montantBase = ComptaDecisionUtils.formatCurrency(quittanceMensuelleSousLigne.montantBase);
            quittanceMensuelleSousLigne.montantTotal = ComptaDecisionUtils.formatCurrency(quittanceMensuelleSousLigne.montantTotal);
        });
    }

    public parseMontants(quittanceMensuelle: QuittanceMensuelle) {
        const that = this;
        _.each(quittanceMensuelle.quittanceMensuelleLignes, function(quittanceMensuelleLigne) {
            that.parseMontantsLigne(quittanceMensuelleLigne);
            _.each(quittanceMensuelleLigne.childQuittanceMensuelleLignes, function(childQuittanceMensuelleLigne) {
                that.parseMontantsLigne(childQuittanceMensuelleLigne);
            });
        });
        quittanceMensuelle.montantTotal = ComptaDecisionUtils.parseCurrency(quittanceMensuelle.montantTotal);
        return quittanceMensuelle;
    }

    public formatMontants(quittanceMensuelle: QuittanceMensuelle) {
        const that = this;
        _.each(quittanceMensuelle.quittanceMensuelleLignes, function(quittanceMensuelleLigne) {
            that.formatMontantsLigne(quittanceMensuelleLigne);
            _.each(quittanceMensuelleLigne.childQuittanceMensuelleLignes, function(childQuittanceMensuelleLigne) {
                that.formatMontantsLigne(childQuittanceMensuelleLigne);
            });
        });
        quittanceMensuelle.montantTotal = ComptaDecisionUtils.formatCurrency(quittanceMensuelle.montantTotal);
        return quittanceMensuelle;
    }
}
