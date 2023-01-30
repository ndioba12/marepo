import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleGuard } from 'src/app/shared/helpers/guards/role.guard';
import { MainComponent } from 'src/app/shared/layouts/main/main.component';
import { AddUtilisateurComponent } from './components/add-utilisateur/add-utilisateur.component';
import { EditUtilisateurComponent } from './components/edit-utilisateur/edit-utilisateur.component';
import { ListUtilisateurComponent } from './components/list-utilisateur/list-utilisateur.component';
import { MonCompteComponent } from './components/mon-compte/mon-compte.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: ListUtilisateurComponent,
        canActivate: [RoleGuard],
        data: { role: ['ADM', 'SAD'] }
      },
      {
        path: 'nouvel-utilisateur',
        component: AddUtilisateurComponent,
        canActivate: [RoleGuard],
        data: { role: ['ADM','SAD'] }
      },
      {
        path: ':id/modifier-utilisateur',
        component: EditUtilisateurComponent,
        canActivate: [RoleGuard],
        data: { role: ['ADM','SAD'] }
      },
      {
        path: 'mon-compte',
        component: MonCompteComponent,
      },
      { path: '', redirectTo: '', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UtilisateursRoutingModule {}
