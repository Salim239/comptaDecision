/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ComptaDecisionTestModule } from '../../../test.module';
import { SecteurActiviteComponent } from 'app/entities/secteur-activite/secteur-activite.component';
import { SecteurActiviteService } from 'app/entities/secteur-activite/secteur-activite.service';
import { SecteurActivite } from 'app/shared/model/secteur-activite.model';

describe('Component Tests', () => {
    describe('SecteurActivite Management Component', () => {
        let comp: SecteurActiviteComponent;
        let fixture: ComponentFixture<SecteurActiviteComponent>;
        let service: SecteurActiviteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [SecteurActiviteComponent],
                providers: []
            })
                .overrideTemplate(SecteurActiviteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SecteurActiviteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SecteurActiviteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SecteurActivite(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.secteurActivites[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
