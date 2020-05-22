import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MoonSharedModule } from 'app/shared/shared.module';
import { OffreComponent } from './offre.component';
import { OffreDetailComponent } from './offre-detail.component';
import { OffreUpdateComponent } from './offre-update.component';
import { OffreDeleteDialogComponent } from './offre-delete-dialog.component';
import { offreRoute } from './offre.route';

@NgModule({
  imports: [MoonSharedModule, RouterModule.forChild(offreRoute)],
  declarations: [OffreComponent, OffreDetailComponent, OffreUpdateComponent, OffreDeleteDialogComponent],
  entryComponents: [OffreDeleteDialogComponent],
})
export class MoonOffreModule {}
