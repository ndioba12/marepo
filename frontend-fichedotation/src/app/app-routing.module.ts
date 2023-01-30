import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './shared/helpers/guards/auth.guard';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () =>
      import('./modules/authentification/authentification.module').then(
        (mod) => mod.AuthentificationModule
      ),
  },
  {
    path: 'gestion-des-actifs',
    loadChildren: () =>
      import('./modules/gestion-des-actifs/actifs.module').then(
        (mod) => mod.ActifsModule
      ),
    canActivate: [AuthGuard],

  },
  {
    path: 'gestion-des-utilisateurs',
    loadChildren: () =>
      import('./modules/utilisateurs/utilisateurs.module').then(
        (mod) => mod.UtilisateursModule
      ),
    canActivate: [AuthGuard],

  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./modules/dashboard/dashboard.module').then(
        (mod) => mod.DashboardModule
      ),
      canActivate: [AuthGuard],

  },
  { path: '', redirectTo: 'auth', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
