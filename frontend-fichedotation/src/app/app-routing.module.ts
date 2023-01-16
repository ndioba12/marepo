import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: "auth", loadChildren: () => import('./modules/authentification/authentification.module').then(mod => mod.AuthentificationModule) },
  { path: "gestion-des-actifs", loadChildren: () => import('./modules/gestion-des-actifs/actifs.module').then(mod => mod.ActifsModule) },
  { path: "gestion-des-utilisateurs", loadChildren: () => import('./modules/utilisateurs/utilisateurs.module').then(mod => mod.UtilisateursModule) },
  { path: "", redirectTo: "auth", pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
