/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { ImpotMensuelClientUpdateComponent } from 'app/entities/impot-mensuel-client/impot-mensuel-client-update.component';
import { ImpotMensuelClientService } from 'app/entities/impot-mensuel-client/impot-mensuel-client.service';
import { ImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';

describe('Component Tests', () => {
    describe('ImpotMensuelClient Management Update Component', () => {
        let comp: ImpotMensuelClientUpdateComponent;
        let fixture: ComponentFixture<ImpotMensuelClientUpdateComponent>;
        let service: ImpotMensuelClientService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [ImpotMensuelClientUpdateComponent]
            })
                .overrideTemplate(ImpotMensuelClientUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImpotMensuelClientUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImpotMensuelClientService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ImpotMensuelClient(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.impotMensuelClient = entity;
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
                    const entity = new ImpotMensuelClient();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.impotMensuelClient = entity;
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
