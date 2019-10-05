/* tslint:disable max-line-length */
import {getTestBed, TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {map, take} from 'rxjs/operators';
import {QuittanceMensuelleImpotDetailService} from 'app/entities/quittance-mensuelle-impot-detail/quittance-mensuelle-impot-detail.service';
import {
    IQuittanceMensuelleImpotDetail,
    QuittanceMensuelleImpotDetail
} from 'app/shared/model/quittance-mensuelle-impot-detail.model';

describe('Service Tests', () => {
    describe('QuittanceMensuelleImpotDetail Service', () => {
        let injector: TestBed;
        let service: QuittanceMensuelleImpotDetailService;
        let httpMock: HttpTestingController;
        let elemDefault: IQuittanceMensuelleImpotDetail;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(QuittanceMensuelleImpotDetailService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new QuittanceMensuelleImpotDetail(0, 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a QuittanceMensuelleImpotDetail', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new QuittanceMensuelleImpotDetail(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a QuittanceMensuelleImpotDetail', async () => {
                const returnedFromService = Object.assign(
                    {
                        montantPaye: 1
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of QuittanceMensuelleImpotDetail', async () => {
                const returnedFromService = Object.assign(
                    {
                        montantPaye: 1
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
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

            it('should delete a QuittanceMensuelleImpotDetail', async () => {
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
