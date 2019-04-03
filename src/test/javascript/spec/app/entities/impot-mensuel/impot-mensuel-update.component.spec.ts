/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { ImpotMensuelUpdateComponent } from 'app/entities/impot-mensuel/impot-mensuel-update.component';
import { ImpotMensuelService } from 'app/entities/impot-mensuel/impot-mensuel.service';
import { ImpotMensuel } from 'app/shared/model/impot-mensuel.model';

describe('Component Tests', () => {
    describe('ImpotMensuel Management Update Component', () => {
        let comp: ImpotMensuelUpdateComponent;
        let fixture: ComponentFixture<ImpotMensuelUpdateComponent>;
        let service: ImpotMensuelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [ImpotMensuelUpdateComponent]
            })
                .overrideTemplate(ImpotMensuelUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImpotMensuelUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImpotMensuelService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ImpotMensuel(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.impotMensuel = entity;
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
                    const entity = new ImpotMensuel();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.impotMensuel = entity;
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
