import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IActualite } from 'app/shared/model/actualite.model';

@Component({
  selector: 'jhi-actualite-detail',
  templateUrl: './actualite-detail.component.html',
})
export class ActualiteDetailComponent implements OnInit {
  actualite: IActualite | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ actualite }) => (this.actualite = actualite));
  }

  previousState(): void {
    window.history.back();
  }
}
