import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AuthentificationRoutingModule} from './authentification-routing.module';
import {LoginComponent} from './components/login/login.component';
import {ResetPasswordComponent} from './components/reset-password/reset-password.component';
import {ForgotPasswordComponent} from './components/forgot-password/forgot-password.component';
import {NgxMaskModule} from 'ngx-mask';
import {MaterialUiModule} from '../../@shared/material-ui/material-ui.module';
import {RegisterComponent} from './components/register/register.component';


@NgModule({
  declarations: [
    LoginComponent,
    ResetPasswordComponent,
    ForgotPasswordComponent,
    RegisterComponent
  ],
  imports: [
    CommonModule,
    AuthentificationRoutingModule,
    NgxMaskModule,
    MaterialUiModule
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
})
export class AuthentificationModule {
}
