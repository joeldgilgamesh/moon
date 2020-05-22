import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActualite } from 'app/shared/model/actualite.model';
import { ActualiteService } from './actualite.service';

@Component({
  templateUrl: './actualite-delete-dialog.component.html',
})
export class ActualiteDeleteDialogComponent {
  actualite?: IActualite;

  constructor(protected actualiteService: ActualiteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.actualiteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('actualiteListModification');
      this.activeModal.close();
    });
  }
}
