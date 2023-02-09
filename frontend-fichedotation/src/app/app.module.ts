import {  NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/layouts/header/header.component';
import { MainComponent } from './shared/layouts/main/main.component';
import { NgbModule, NgbPaginationModule ,NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';
import { cessionServices } from './modules/gestion-des-actifs/cession/services/cession.services';
import { InventaireServices } from './modules/gestion-des-actifs/inventaires/services/iventaire.services';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialUiModule} from './shared/material-ui/material-ui.module';
import {AuthInterceptor, httpInterceptorProviders} from './shared/helpers/http/auth.interceptor';
import {AuthGuard} from './shared/helpers/guards/auth.guard';
import {RoleGuard} from './shared/helpers/guards/role.guard';
import {SharedModule} from './shared/shared.module';
import {SharedState} from './shared/state/shared.state';
import {SharedFacade} from './shared/state/shared.facade';
import {AppLoaderInterceptor} from './shared/helpers/http/loader.interceptor';
import {CommonModule} from '@angular/common';
import {ToastrModule} from 'ngx-toastr';
import {ErrorHandlerInterceptor} from './shared/helpers/http/error-handler.interceptor';

import { MainguestComponent } from './shared/layouts/mainguest/mainguest.component';
import { LoaderComponent } from './shared/layouts/loader/loader.component';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { PaginationPersonnalise } from './shared/helpers/others/personalizePagination';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    LoaderComponent,
    MainguestComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    NgbPaginationModule,
    NgbAlertModule,
    NoopAnimationsModule,
    MaterialUiModule,
    SharedModule,
    ToastrModule.forRoot({
      timeOut: 15000,
      positionClass: 'toast-top-left',
      closeButton: true,
      maxOpened: 1,
      progressBar: true,
      newestOnTop: true,
      preventDuplicates: true,
    }),
  ],
  providers: [
    cessionServices,
    InventaireServices,
    SharedFacade,
    SharedState,
    {provide: MatPaginatorIntl, useClass: PaginationPersonnalise},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorHandlerInterceptor, multi: true},
    httpInterceptorProviders, AppLoaderInterceptor, AuthGuard, RoleGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
