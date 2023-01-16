import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {MainComponent} from './@shared/layouts/main/main.component';
import {SidebarComponent} from './@shared/layouts/sidebar/sidebar.component';
import {NavbarComponent} from './@shared/layouts/navbar/navbar.component';
import {FooterComponent} from './@shared/layouts/footer/footer.component';
import {MaterialUiModule} from './@shared/material-ui/material-ui.module';
import {AppRoutingModule} from './app-routing.module';
import {MainGuestComponent} from './@shared/layouts/main-guest/main-guest.component';
import {NgxMaskModule} from 'ngx-mask';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {PaginationPersonnalise} from './@shared/helpers/others/personalizePagination';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor, httpInterceptorProviders} from './@shared/helpers/http/auth-interceptor';
import {AuthGuard} from './@shared/helpers/guards/auth.guard';
import {CdkColumnDef} from '@angular/cdk/table';
import {RoleGuard} from './@shared/helpers/guards/role.guard';
import {SharedModule} from './@shared/shared.module';
import {FixedpluginModule} from './@shared/layouts/fixedplugin/fixedplugin.module';
import {SharedState} from './@shared/state/shared.state';
import {SharedFacade} from './@shared/state/shared.facade';
import {AppLoaderInterceptor} from './@shared/helpers/http/loader.interceptor';
import {LoaderComponent} from './@shared/layouts/loader/loader.component';
import {CommonModule, DatePipe, TitleCasePipe} from '@angular/common';
import {ToastrModule} from 'ngx-toastr';
import {MatPaginatorIntl} from '@angular/material/paginator';
import {ErrorHandlerInterceptor} from './@shared/helpers/http/error-handler.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    MainGuestComponent,
    LoaderComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    MaterialUiModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    ToastrModule.forRoot({
      timeOut: 15000,
      positionClass: 'toast-top-right',
      closeButton: true,
      maxOpened: 1,
      progressBar: true,
      newestOnTop: true,
      preventDuplicates: true,
    }),
    NgxMaskModule.forRoot(),
    SharedModule,
    FixedpluginModule
  ],
  exports: [],
  providers: [
    SharedFacade,
    SharedState,
    {provide: MatPaginatorIntl, useClass: PaginationPersonnalise},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorHandlerInterceptor, multi: true},
    DatePipe, TitleCasePipe, CdkColumnDef, httpInterceptorProviders, AppLoaderInterceptor, AuthGuard, RoleGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
