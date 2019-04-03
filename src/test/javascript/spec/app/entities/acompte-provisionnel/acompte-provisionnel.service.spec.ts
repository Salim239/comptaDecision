/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AcompteProvisionnelService } from 'app/entities/acompte-provisionnel/acompte-provisionnel.service';
import { IAcompteProvisionnel, AcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';

describe('Service Tests', () => {
    describe('AcompteProvisionnel Service', () => {
        let injector: TestBed;
        let service: AcompteProvisionnelService;
        let httpMock: HttpTestingController;
        let elemDefault: IAcompteProvisionnel;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AcompteProvisionnelService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new AcompteProvisionnel(0, 0, 0, currentDate, 'AAAAAAA', 0, 0, 0, 0, 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        date: currentDate.format(DATE_FORMAT)
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

            it('should create a AcompteProvisionnel', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        date: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        date: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new AcompteProvisionnel(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a AcompteProvisionnel', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        numero: 1,
                        date: currentDate.format(DATE_FORMAT),
                        numeroQuittance: 'BBBBBB',
                        montantBase: 1,
                        montantAcompteProvisionnel: 1,
                        montantReportAnterieur: 1,
                        montantRetenueSource: 1,
                        montantNet: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        date: currentDate
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

            it('should return a list of AcompteProvisionnel', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        numero: 1,
                        date: currentDate.format(DATE_FORMAT),
                        numeroQuittance: 'BBBBBB',
                        montantBase: 1,
                        montantAcompteProvisionnel: 1,
                        montantReportAnterieur: 1,
                        montantRetenueSource: 1,
                        montantNet: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        date: currentDate
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

            it('should delete a AcompteProvisionnel', async () => {
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
