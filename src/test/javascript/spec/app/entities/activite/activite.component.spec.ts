/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ComptaDecisionTestModule } from '../../../test.module';
import { ActiviteComponent } from 'app/entities/activite/activite.component';
import { ActiviteService } from 'app/entities/activite/activite.service';
import { Activite } from 'app/shared/model/activite.model';

describe('Component Tests', () => {
    describe('Activite Management Component', () => {
        let comp: ActiviteComponent;
        let fixture: ComponentFixture<ActiviteComponent>;
        let service: ActiviteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [ActiviteComponent],
                providers: []
            })
                .overrideTemplate(ActiviteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ActiviteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActiviteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Activite(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.activites[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
