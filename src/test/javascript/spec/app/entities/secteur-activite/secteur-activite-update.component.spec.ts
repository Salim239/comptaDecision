/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { SecteurActiviteUpdateComponent } from 'app/entities/secteur-activite/secteur-activite-update.component';
import { SecteurActiviteService } from 'app/entities/secteur-activite/secteur-activite.service';
import { SecteurActivite } from 'app/shared/model/secteur-activite.model';

describe('Component Tests', () => {
    describe('SecteurActivite Management Update Component', () => {
        let comp: SecteurActiviteUpdateComponent;
        let fixture: ComponentFixture<SecteurActiviteUpdateComponent>;
        let service: SecteurActiviteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [SecteurActiviteUpdateComponent]
            })
                .overrideTemplate(SecteurActiviteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SecteurActiviteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SecteurActiviteService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SecteurActivite(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.secteurActivite = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SecteurActivite();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.secteurActivite = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
