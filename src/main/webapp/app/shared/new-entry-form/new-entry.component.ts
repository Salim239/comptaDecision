import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import * as moment from 'moment';
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FicheClientService} from 'app/entities/fiche-client';
import {filter, map} from 'rxjs/operators';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {JhiAlertService} from 'ng-jhipster';
import {TypeDeclaration} from 'app/shared/model/quittance-mensuelle-impot.model';
import {TypeCnss} from 'app/shared/model/cnss.model';

@Component({
    selector: 'jhi-new-entry',
    templateUrl: './new-entry.component.html'
})

export class NewEntryComponent implements OnInit {

    @Input() ficheClients: IFicheClient[];
    @Output() submitCreationForm = new EventEmitter();
    @Input() hideTrimestre: boolean;
    @Input() hideMois: boolean;
    @Input() hideTypeDeclaration: boolean;
    @Input() hildeTypeCnss: boolean;
    @Input() hildeNumeroAcompte: boolean;

    typeCnssList: TypeCnss[];
    anneeList: number[];
    moisList: number[];
    trimestreList: number[];
    numeroAcompteList: number[];
    typeDeclarationList: TypeDeclaration[];
    selectedFicheClientId: number;
    selectedTrimestre: number;
    selectedAnnee: number;
    selectedMois: number;
    selectedNumeroAcompte: number;
    selectedTypeDeclaration: TypeDeclaration;
    selectedTypeCnss: TypeCnss;

    constructor(
        private jhiAlertService: JhiAlertService,
        private ficheClientService: FicheClientService) {
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    ngOnInit() {
        this.hildeTypeCnss = true;
        this.hildeNumeroAcompte = true;
        if (this.ficheClients) {
            this.initFormData();
        }
        this.ficheClientService.query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient[]>) => response.body)
            )
            .subscribe((res: IFicheClient[]) => {
                this.ficheClients = res;
                this.initFormData();
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    private initFormData() {
        this.moisList = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
        this.trimestreList = [1, 2, 3, 4];
        this.numeroAcompteList = [1, 2, 3];
        this.typeCnssList = [TypeCnss.CNSS_GENERALE, TypeCnss.CNSS_EMPLOYEUR];
        this.anneeList = ComptaDecisionUtils.getPreviousYears(moment(this.ficheClients[0].dateCreation).year());
        this.typeDeclarationList = [TypeDeclaration.DECLARATION_INITIALE, TypeDeclaration.DECLARATION_RECTIFICATIVE];
        this.selectedFicheClientId = this.ficheClients[0].id;
        this.selectedTypeDeclaration = this.typeDeclarationList[0];
        this.selectedTypeCnss = this.typeCnssList[0];
        this.selectedNumeroAcompte = this.numeroAcompteList[0];
        this.selectedAnnee = this.anneeList[0];
        this.selectedMois = this.moisList[0];
        this.selectedTrimestre = this.trimestreList[0];
    }

    updateAnneeMois() {
        this.ficheClientService.find(this.selectedFicheClientId)
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient>) => response.body)
            )
            .subscribe((res: IFicheClient) => {
                const ficheClient = res;
                // this.moisList = [1,2,3,4,5,6,7,8,9,10,11,12];
                this.anneeList = ComptaDecisionUtils.getPreviousYears(moment(ficheClient.dateCreation).year());
                this.selectedFicheClientId = ficheClient.id;
                this.selectedAnnee = this.anneeList[0];
                // this.selectedMois = this.moisList[0];
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    submit() {

        this.submitCreationForm.emit({
            'ficheClientId': this.selectedFicheClientId,
            'typeDeclaration': this.selectedTypeDeclaration,
            'annee': this.selectedAnnee,
            'mois': this.selectedMois,
            'trimestre': this.selectedTrimestre,
            'typeCnss': this.selectedTypeCnss,
            'numeroAcompte': this.selectedNumeroAcompte,
        });
    }
}
