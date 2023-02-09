import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainguestComponent } from 'src/app/shared/layouts/mainguest/mainguest.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { LoginComponent } from './components/login/login.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';

const routes: Routes = [
  {
    path: "",
    component: MainguestComponent,
    children: [
      { path: "login", component: LoginComponent, title: "Login"},
      { path: "forgot-password", component: ForgotPasswordComponent, title: "Forgot password" , data:{ animation:'isRight' } },
      { path: "reset-password", component: ResetPasswordComponent, title: "Reset password", data:{ animation:'isLeft' } },
      { path: "", redirectTo: "login", pathMatch: "full" },
      // /:token
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthentificationRoutingModule { }
