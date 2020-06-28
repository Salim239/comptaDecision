import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { filter, map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IQuittanceMensuelleImpot, QuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';
import * as _ from 'lodash';
import { IQuittanceMensuelleImpotDetail } from 'app/shared/model/quittance-mensuelle-impot-detail.model';
import { IQuittanceMensuelleImpotSousDetail } from 'app/shared/model/quittance-mensuelle-impot-sous-detail.model';

type EntityResponseType = HttpResponse<IQuittanceMensuelleImpot>;
type EntityArrayResponseType = HttpResponse<IQuittanceMensuelleImpot[]>;

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleImpotService {
    public resourceUrl = SERVER_API_URL + 'api/quittance-mensuelle-impots';

    constructor(protected http: HttpClient) {}

    initEmpty(id, annee, mois, typeDeclaration): Observable<EntityResponseType> {
        return this.http
            .get<IQuittanceMensuelleImpot>(this.resourceUrl + `/init/${id}/${annee}/${mois}/${typeDeclaration}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    initByParams(ficheClientId: number): Observable<EntityResponseType> {
        return this.http
            .get<IQuittanceMensuelleImpot>(`${this.resourceUrl}/ficheClient/${ficheClientId}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    create(quittanceMensuelleImpot: IQuittanceMensuelleImpot): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(quittanceMensuelleImpot);
        return this.http
            .post<IQuittanceMensuelleImpot>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(quittanceMensuelleImpot: IQuittanceMensuelleImpot): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(quittanceMensuelleImpot);
        return this.http
            .put<IQuittanceMensuelleImpot>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IQuittanceMensuelleImpot>(`${this.resourceUrl}/${id}`, { observe: 'response' })
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
            .get<IQuittanceMensuelleImpot[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(quittanceMensuelleImpot: IQuittanceMensuelleImpot): IQuittanceMensuelleImpot {
        const copy: IQuittanceMensuelleImpot = Object.assign({}, quittanceMensuelleImpot, {
            datePaiement:
                quittanceMensuelleImpot.datePaiement != null && quittanceMensuelleImpot.datePaiement.isValid()
                    ? quittanceMensuelleImpot.datePaiement.format(DATE_FORMAT)
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
            res.body.forEach((quittanceMensuelleImpot: IQuittanceMensuelleImpot) => {
                quittanceMensuelleImpot.datePaiement =
                    quittanceMensuelleImpot.datePaiement != null ? moment(quittanceMensuelleImpot.datePaiement) : null;
            });
        }
        return res;
    }

    public save(quittance: IQuittanceMensuelleImpot): Observable<IQuittanceMensuelleImpot> {
        quittance = this.parseMontants(quittance);
        return quittance.id
            ? this.update(quittance).pipe(
                  filter((response: HttpResponse<QuittanceMensuelleImpot>) => response.ok),
                  map((quittanceMensuelleImpot: HttpResponse<QuittanceMensuelleImpot>) => this.formatMontants(quittanceMensuelleImpot.body))
              )
            : this.create(quittance).pipe(
                  filter((response: HttpResponse<QuittanceMensuelleImpot>) => response.ok),
                  map((quittanceMensuelleImpot: HttpResponse<QuittanceMensuelleImpot>) => this.formatMontants(quittanceMensuelleImpot.body))
              );
    }

    private findSousDetailByCode(
        codeSousDetail: string,
        quittanceDetail: IQuittanceMensuelleImpotDetail
    ): IQuittanceMensuelleImpotSousDetail {
        return _.find(quittanceDetail.quittanceMensuelleImpotSousDetails, function(sousDetail) {
            return sousDetail.impotMensuelDetailCode === codeSousDetail;
        });
    }

    public updateQuittanceModel(target: IQuittanceMensuelleImpot, source: IQuittanceMensuelleImpot) {
        const that = this;
        target.id = source.id;
        target.montantTotal = source.montantTotal;
        _.each(target.quittanceMensuelleImpotDetails, function(quittanceDetail) {
            let quittanceDetailRes = _.find(source.quittanceMensuelleImpotDetails, function(detail) {
                return detail.code === quittanceDetail.code;
            });
            quittanceDetail.id = quittanceDetailRes.id;
            quittanceDetail.montantTotal = quittanceDetailRes.montantTotal;
            quittanceDetail.montantReport = quittanceDetailRes.montantReport;
            quittanceDetail.montantReportCalc = quittanceDetailRes.montantReportCalc;
            _.each(quittanceDetail.quittanceMensuelleImpotSousDetails, function(quittanceSousDetail) {
                const resSousDetail = that.findSousDetailByCode(quittanceSousDetail.impotMensuelDetailCode, quittanceDetailRes);
                quittanceSousDetail.id = resSousDetail.id;
                quittanceSousDetail.montantBase = resSousDetail.montantBase;
                quittanceSousDetail.montantTotal = resSousDetail.montantTotal;
            });
            if (quittanceDetail.parent) {
                _.each(quittanceDetail.childQuittanceMensuelleImpotDetails, function(quittanceDetailChild) {
                    let quittanceDetailChildRes;
                    for (var detailRes of source.quittanceMensuelleImpotDetails) {
                        quittanceDetailChildRes = _.find(detailRes.childQuittanceMensuelleImpotDetails, function(child) {
                            return child.code === quittanceDetailChild.code;
                        });
                        if (quittanceDetailChildRes) break;
                    }
                    quittanceDetailChild.id = quittanceDetailChildRes.id;
                    quittanceDetailChild.montantTotal = quittanceDetailChildRes.montantTotal;
                    quittanceDetailChild.montantReport = quittanceDetailChildRes.montantReport;
                    quittanceDetailChild.montantReportCalc = quittanceDetailChildRes.montantReportCalc;
                    _.each(quittanceDetailChild.quittanceMensuelleImpotSousDetails, function(quittanceSousDetail) {
                        const resSousDetail = that.findSousDetailByCode(
                            quittanceSousDetail.impotMensuelDetailCode,
                            quittanceDetailChildRes
                        );
                        quittanceSousDetail.id = resSousDetail.id;
                        quittanceSousDetail.montantBase = resSousDetail.montantBase;
                        quittanceSousDetail.montantTotal = resSousDetail.montantTotal;
                    });
                });
            }
        });
    }

    private parseMontantsDetail(quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail) {
        quittanceMensuelleImpotDetail.montantTotal = ComptaDecisionUtils.parseCurrency(quittanceMensuelleImpotDetail.montantTotal);
        if (quittanceMensuelleImpotDetail.appliquerReportMontant) {
            quittanceMensuelleImpotDetail.montantReport = ComptaDecisionUtils.parseCurrency(quittanceMensuelleImpotDetail.montantReport);
            quittanceMensuelleImpotDetail.montantReportCalc = ComptaDecisionUtils.parseCurrency(
                quittanceMensuelleImpotDetail.montantReportCalc
            );
        }
        _.each(quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails, function(quittanceMensuelleImpotSousDetail) {
            quittanceMensuelleImpotSousDetail.montantBase = ComptaDecisionUtils.parseCurrency(
                quittanceMensuelleImpotSousDetail.montantBase
            );
            quittanceMensuelleImpotSousDetail.montantTotal = ComptaDecisionUtils.parseCurrency(
                quittanceMensuelleImpotSousDetail.montantTotal
            );
        });
    }

    private formatMontantsDetail(quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail) {
        quittanceMensuelleImpotDetail.montantTotal = ComptaDecisionUtils.formatCurrency(quittanceMensuelleImpotDetail.montantTotal);
        if (quittanceMensuelleImpotDetail.appliquerReportMontant) {
            quittanceMensuelleImpotDetail.montantReport = ComptaDecisionUtils.formatCurrency(quittanceMensuelleImpotDetail.montantReport);
            quittanceMensuelleImpotDetail.montantReportCalc = ComptaDecisionUtils.formatCurrency(
                quittanceMensuelleImpotDetail.montantReportCalc
            );
        }
        _.each(quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails, function(quittanceMensuelleImpotSousDetail) {
            quittanceMensuelleImpotSousDetail.montantBase = ComptaDecisionUtils.formatCurrency(
                quittanceMensuelleImpotSousDetail.montantBase
            );
            quittanceMensuelleImpotSousDetail.montantTotal = ComptaDecisionUtils.formatCurrency(
                quittanceMensuelleImpotSousDetail.montantTotal
            );
        });
    }

    public parseMontants(quittanceMensuelleImpot: QuittanceMensuelleImpot) {
        const that = this;
        _.each(quittanceMensuelleImpot.quittanceMensuelleImpotDetails, function(quittanceMensuelleImpotDetail) {
            that.parseMontantsDetail(quittanceMensuelleImpotDetail);
            _.each(quittanceMensuelleImpotDetail.childQuittanceMensuelleImpotDetails, function(childQuittanceMensuelleImpotDetail) {
                that.parseMontantsDetail(childQuittanceMensuelleImpotDetail);
            });
        });
        quittanceMensuelleImpot.montantTotal = ComptaDecisionUtils.parseCurrency(quittanceMensuelleImpot.montantTotal);
        return quittanceMensuelleImpot;
    }

    public formatMontants(quittanceMensuelleImpot: QuittanceMensuelleImpot) {
        const that = this;
        _.each(quittanceMensuelleImpot.quittanceMensuelleImpotDetails, function(quittanceMensuelleImpotDetail) {
            that.formatMontantsDetail(quittanceMensuelleImpotDetail);
            _.each(quittanceMensuelleImpotDetail.childQuittanceMensuelleImpotDetails, function(childQuittanceMensuelleImpotDetail) {
                that.formatMontantsDetail(childQuittanceMensuelleImpotDetail);
            });
        });
        quittanceMensuelleImpot.montantTotal = ComptaDecisionUtils.formatCurrency(quittanceMensuelleImpot.montantTotal);
        return quittanceMensuelleImpot;
    }
}
