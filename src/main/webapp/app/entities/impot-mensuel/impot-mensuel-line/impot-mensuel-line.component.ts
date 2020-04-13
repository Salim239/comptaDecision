import {Component, Input, OnInit} from '@angular/core';
import {IImpotMensuelDetail, ImpotMensuelDetail} from 'app/shared/model/impot-mensuel-detail.model';
import {IImpotMensuel} from 'app/shared/model/impot-mensuel.model';

@Component({
  selector: 'jhi-impot-mensuel-line',
  templateUrl: './impot-mensuel-line.component.html',
  styles: []
})
export class ImpotMensuelLineComponent implements OnInit {
  @Input() impotMensuel: IImpotMensuel;
  impotMensuelDetailNew: IImpotMensuelDetail;

  newImpotMensuelDetail() {
    return new ImpotMensuelDetail(undefined, this.impotMensuel.impotMensuelDetails.length === 0 ? 1 : this.impotMensuel.impotMensuelDetails.length + 1);
  }

  isValidImpotMensuelDetail(impotMensuelDetail) {
    return impotMensuelDetail.code && impotMensuelDetail.code !== '' && impotMensuelDetail.libelle && impotMensuelDetail.libelle !== '' && impotMensuelDetail.triOrdre && impotMensuelDetail.triOrdre !== '';
  }

  /**
   * Recalculate tri ordre of impotMensuelDetail for elements since given index in the array
   * @param recalFromIndex
   */
  recalculateImpotMensuelDetailTriOrdre(recalFromIndex) {
      this.impotMensuel.impotMensuelDetails.forEach(impotMensuelDetail => {
              if (impotMensuelDetail.triOrdre - 1 > recalFromIndex) {
                  impotMensuelDetail.triOrdre = impotMensuelDetail.triOrdre - 1;
                  return impotMensuelDetail;
              }
          }
      );
  }

  addImpotMensuelDetail(impotMensuelDetailNew) {
    this.impotMensuel.impotMensuelDetails.push(impotMensuelDetailNew);
    this.impotMensuelDetailNew = this.newImpotMensuelDetail();
  }

  editImpotMensuelDetail(impotMensuelDetail, impotMensuelDetailIndex) {
    this.impotMensuelDetailNew = impotMensuelDetail;
    this.impotMensuel.impotMensuelDetails.splice(impotMensuelDetailIndex, 1);
  }

  deleteImpotMensuelDetail(impotMensuelDetailIndex) {
    this.impotMensuel.impotMensuelDetails.splice(impotMensuelDetailIndex, 1);
    this.recalculateImpotMensuelDetailTriOrdre(impotMensuelDetailIndex);
    this.impotMensuelDetailNew = this.newImpotMensuelDetail();
  }

  constructor() {

  }

  ngOnInit() {
    this.impotMensuelDetailNew = this.newImpotMensuelDetail();
  }

}
