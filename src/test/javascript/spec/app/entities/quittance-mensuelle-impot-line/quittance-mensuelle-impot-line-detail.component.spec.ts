/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { QuittanceMensuelleImpotLineDetailComponent } from 'app/entities/quittance-mensuelle-impot-line/quittance-mensuelle-impot-line-detail.component';
import { QuittanceMensuelleImpotLine } from 'app/shared/model/quittance-mensuelle-impot-line.model';

describe('Component Tests', () => {
    describe('QuittanceMensuelleImpotLine Management Detail Component', () => {
        let comp: QuittanceMensuelleImpotLineDetailComponent;
        let fixture: ComponentFixture<QuittanceMensuelleImpotLineDetailComponent>;
        const route = ({ data: of({ quittanceMensuelleImpotLine: new QuittanceMensuelleImpotLine(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [QuittanceMensuelleImpotLineDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(QuittanceMensuelleImpotLineDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(QuittanceMensuelleImpotLineDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.quittanceMensuelleImpotLine).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
