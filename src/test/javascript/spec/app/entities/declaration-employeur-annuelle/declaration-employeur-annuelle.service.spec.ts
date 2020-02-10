/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { DeclarationEmployeurAnnuelleService } from 'app/entities/declaration-employeur-annuelle/declaration-employeur-annuelle.service';
import { IDeclarationEmployeurAnnuelle, DeclarationEmployeurAnnuelle } from 'app/shared/model/declaration-employeur-annuelle.model';

describe('Service Tests', () => {
    describe('DeclarationEmployeurAnnuelle Service', () => {
        let injector: TestBed;
        let service: DeclarationEmployeurAnnuelleService;
        let httpMock: HttpTestingController;
        let elemDefault: IDeclarationEmployeurAnnuelle;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(DeclarationEmployeurAnnuelleService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new DeclarationEmployeurAnnuelle(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

            it('should create a DeclarationEmployeurAnnuelle', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new DeclarationEmployeurAnnuelle(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a DeclarationEmployeurAnnuelle', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        montantAnnexe1: 1,
                        montantAnnexe2: 1,
                        montantAnnexe3: 1,
                        montantAnnexe4: 1,
                        montantAnnexe5: 1,
                        montantAnnexe6: 1,
                        montantAnnexe7: 1,
                        montantAnnexe8: 1,
                        montantAnnexe9: 1,
                        montantAnnexe10: 1,
                        montantAnnexe11: 1,
                        montantAnnexe12: 1
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

            it('should return a list of DeclarationEmployeurAnnuelle', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        montantAnnexe1: 1,
                        montantAnnexe2: 1,
                        montantAnnexe3: 1,
                        montantAnnexe4: 1,
                        montantAnnexe5: 1,
                        montantAnnexe6: 1,
                        montantAnnexe7: 1,
                        montantAnnexe8: 1,
                        montantAnnexe9: 1,
                        montantAnnexe10: 1,
                        montantAnnexe11: 1,
                        montantAnnexe12: 1
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

            it('should delete a DeclarationEmployeurAnnuelle', async () => {
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
