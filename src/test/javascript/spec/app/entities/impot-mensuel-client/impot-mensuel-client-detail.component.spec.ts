/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { ImpotMensuelClientDetailComponent } from 'app/entities/impot-mensuel-client/impot-mensuel-client-detail.component';
import { ImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';

describe('Component Tests', () => {
    describe('ImpotMensuelClient Management Detail Component', () => {
        let comp: ImpotMensuelClientDetailComponent;
        let fixture: ComponentFixture<ImpotMensuelClientDetailComponent>;
        const route = ({ data: of({ impotMensuelClient: new ImpotMensuelClient(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [ImpotMensuelClientDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ImpotMensuelClientDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImpotMensuelClientDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.impotMensuelClient).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
