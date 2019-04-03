/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { FicheClientDetailComponent } from 'app/entities/fiche-client/fiche-client-detail.component';
import { FicheClient } from 'app/shared/model/fiche-client.model';

describe('Component Tests', () => {
    describe('FicheClient Management Detail Component', () => {
        let comp: FicheClientDetailComponent;
        let fixture: ComponentFixture<FicheClientDetailComponent>;
        const route = ({ data: of({ ficheClient: new FicheClient(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [FicheClientDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FicheClientDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FicheClientDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ficheClient).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
