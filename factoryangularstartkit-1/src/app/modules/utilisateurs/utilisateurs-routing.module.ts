import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from '../../@shared/layouts/main/main.component';
import {ListUtilisateurComponent} from './components/list-utilisateur/list-utilisateur.component';
import {AjoutUtilisateurComponent} from './components/ajout-utilisateur/ajout-utilisateur.component';
import {EditUtilisateurComponent} from './components/edit-utilisateur/edit-utilisateur.component';
import {AuthGuard} from '../../@shared/helpers/guards/auth.guard';
import {DetailUtilisateurComponent} from './components/detail-utilisateur/detail-utilisateur.component';
import {RoleGuard} from '../../@shared/helpers/guards/role.guard';

const routes: Routes = [
  {
    path: '', component: MainComponent, canActivate: [AuthGuard],
    children: [
      {path: 'list', component: ListUtilisateurComponent, canActivate: [RoleGuard], data: {role: ['ADM','SAD']}},
      {path: 'add', component: AjoutUtilisateurComponent, canActivate: [RoleGuard], data: {role: ['SAD']}},
      {path: 'edit/:id', component: EditUtilisateurComponent, canActivate: [RoleGuard], data: {role: ['SAD']}},
      {path: 'details/:id', component: DetailUtilisateurComponent, canActivate: [RoleGuard], data: {role: ['ADM','SAD']}},
      {path: '', redirectTo: 'list', pathMatch: 'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UtilisateursRoutingModule {
}
