import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/layouts/header/header.component';
import { MainComponent } from './shared/layouts/main/main.component';
import { NgbModule, NgbPaginationModule ,NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';
import { cessionServices } from './modules/gestion-des-actifs/cession/services/cession.services';
import { InventaireServices } from './modules/gestion-des-actifs/inventaires/services/iventaire.services';
import { MainguestComponent } from './shared/layouts/mainguest/mainguest.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    MainguestComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgbModule,
    NgbPaginationModule,
    NgbAlertModule
  ],
  providers: [
    cessionServices,
    InventaireServices,

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
