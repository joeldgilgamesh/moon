import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MoonSharedModule } from 'app/shared/shared.module';
import { ActualiteComponent } from './actualite.component';
import { ActualiteDetailComponent } from './actualite-detail.component';
import { ActualiteUpdateComponent } from './actualite-update.component';
import { ActualiteDeleteDialogComponent } from './actualite-delete-dialog.component';
import { actualiteRoute } from './actualite.route';

@NgModule({
  imports: [MoonSharedModule, RouterModule.forChild(actualiteRoute)],
  declarations: [ActualiteComponent, ActualiteDetailComponent, ActualiteUpdateComponent, ActualiteDeleteDialogComponent],
  entryComponents: [ActualiteDeleteDialogComponent],
})
export class MoonActualiteModule {}
