/* tslint:disable max-line-length */
import {getTestBed, TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {map, take} from 'rxjs/operators';
import * as moment from 'moment';
import {DATE_FORMAT} from 'app/shared/constants/input.constants';
import {DeclarationAnnuelleService} from 'app/entities/declaration-annuelle/declaration-annuelle.service';
import {DeclarationAnnuelle, IDeclarationAnnuelle, TypeDeclaration} from 'app/shared/model/declaration-annuelle.model';

describe('Service Tests', () => {
    describe('DeclarationAnnuelle Service', () => {
        let injector: TestBed;
        let service: DeclarationAnnuelleService;
        let httpMock: HttpTestingController;
        let elemDefault: IDeclarationAnnuelle;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(DeclarationAnnuelleService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new DeclarationAnnuelle(0, TypeDeclaration.DECLARATION_INITIALE, 0, currentDate, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        datePaiement: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a DeclarationAnnuelle', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        datePaiement: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datePaiement: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new DeclarationAnnuelle(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a DeclarationAnnuelle', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        datePaiement: currentDate.format(DATE_FORMAT),
                        numeroQuittance: 'BBBBBB',
                        montantChiffreAffaireHT: 1,
                        montantChiffreAffaireExport: 1,
                        montantChiffreAffaireImpot: 1,
                        montantChiffreAffaireTTC: 1,
                        montantResultatComptable: 1,
                        montantDeductionCommune: 1,
                        montantAutreDeduction: 1,
                        montantBaseImposable: 1,
                        montantImpotLiquide: 1,
                        montantAcompteProvisionnel: 1,
                        montantRetenueSource: 1,
                        montantNetAPaye: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        datePaiement: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of DeclarationAnnuelle', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        datePaiement: currentDate.format(DATE_FORMAT),
                        numeroQuittance: 'BBBBBB',
                        montantChiffreAffaireHT: 1,
                        montantChiffreAffaireExport: 1,
                        montantChiffreAffaireImpot: 1,
                        montantChiffreAffaireTTC: 1,
                        montantResultatComptable: 1,
                        montantDeductionCommune: 1,
                        montantAutreDeduction: 1,
                        montantBaseImposable: 1,
                        montantImpotLiquide: 1,
                        montantAcompteProvisionnel: 1,
                        montantRetenueSource: 1,
                        montantNetAPaye: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datePaiement: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a DeclarationAnnuelle', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
