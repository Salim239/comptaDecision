/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { ImpotMensuelDetailComponent } from 'app/entities/impot-mensuel/impot-mensuel-detail.component';
import { ImpotMensuel } from 'app/shared/model/impot-mensuel.model';

describe('Component Tests', () => {
    describe('ImpotMensuel Management Detail Component', () => {
        let comp: ImpotMensuelDetailComponent;
        let fixture: ComponentFixture<ImpotMensuelDetailComponent>;
        const route = ({ data: of({ impotMensuel: new ImpotMensuel(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [ImpotMensuelDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ImpotMensuelDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImpotMensuelDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.impotMensuel).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
