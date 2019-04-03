/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ComptaDecisionTestModule } from '../../../test.module';
import { ImpotMensuelComponent } from 'app/entities/impot-mensuel/impot-mensuel.component';
import { ImpotMensuelService } from 'app/entities/impot-mensuel/impot-mensuel.service';
import { ImpotMensuel } from 'app/shared/model/impot-mensuel.model';

describe('Component Tests', () => {
    describe('ImpotMensuel Management Component', () => {
        let comp: ImpotMensuelComponent;
        let fixture: ComponentFixture<ImpotMensuelComponent>;
        let service: ImpotMensuelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [ImpotMensuelComponent],
                providers: []
            })
                .overrideTemplate(ImpotMensuelComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImpotMensuelComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImpotMensuelService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ImpotMensuel(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.impotMensuels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
