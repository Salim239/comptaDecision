/* tslint:disable max-line-length */
import {getTestBed, TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {map, take} from 'rxjs/operators';
import * as moment from 'moment';
import {DATE_FORMAT} from 'app/shared/constants/input.constants';
import {QuittanceMensuelleImpotService} from 'app/entities/quittance-mensuelle-impot/quittance-mensuelle-impot.service';
import {
    IQuittanceMensuelleImpot,
    QuittanceMensuelleImpot,
    TypeDeclaration
} from 'app/shared/model/quittance-mensuelle-impot.model';

describe('Service Tests', () => {
    describe('QuittanceMensuelleImpot Service', () => {
        let injector: TestBed;
        let service: QuittanceMensuelleImpotService;
        let httpMock: HttpTestingController;
        let elemDefault: IQuittanceMensuelleImpot;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(QuittanceMensuelleImpotService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new QuittanceMensuelleImpot(0, 0, 0, TypeDeclaration.DECLARATION_INITIALE, 'AAAAAAA', currentDate, 0);
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

            it('should create a QuittanceMensuelleImpot', async () => {
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
                    .create(new QuittanceMensuelleImpot(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a QuittanceMensuelleImpot', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        mois: 1,
                        numeroQuittance: 'BBBBBB',
                        datePaiement: currentDate.format(DATE_FORMAT),
                        montantTotal: 1
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

            it('should return a list of QuittanceMensuelleImpot', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        mois: 1,
                        numeroQuittance: 'BBBBBB',
                        datePaiement: currentDate.format(DATE_FORMAT),
                        montantTotal: 1
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

            it('should delete a QuittanceMensuelleImpot', async () => {
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
