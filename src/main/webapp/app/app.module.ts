import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MoonSharedModule } from 'app/shared/shared.module';
import { MoonCoreModule } from 'app/core/core.module';
import { MoonAppRoutingModule } from './app-routing.module';
import { MoonHomeModule } from './home/home.module';
import { MoonEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    MoonSharedModule,
    MoonCoreModule,
    MoonHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MoonEntityModule,
    MoonAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class MoonAppModule {}
