/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { QuittanceMensuelleImpotDetailComponent } from 'app/entities/quittance-mensuelle-impot/quittance-mensuelle-impot-detail.component';
import { QuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';

describe('Component Tests', () => {
    describe('QuittanceMensuelleImpot Management Detail Component', () => {
        let comp: QuittanceMensuelleImpotDetailComponent;
        let fixture: ComponentFixture<QuittanceMensuelleImpotDetailComponent>;
        const route = ({ data: of({ quittanceMensuelleImpot: new QuittanceMensuelleImpot(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [QuittanceMensuelleImpotDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(QuittanceMensuelleImpotDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(QuittanceMensuelleImpotDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.quittanceMensuelleImpot).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
