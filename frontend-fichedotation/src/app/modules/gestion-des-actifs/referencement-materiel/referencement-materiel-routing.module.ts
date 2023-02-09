import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleGuard } from 'src/app/shared/helpers/guards/role.guard';
import { MainComponent } from 'src/app/shared/layouts/main/main.component';
import { AddAffectationComponent } from './components/add-affectation/add-affectation.component';
import { AddRecuperationComponent } from './components/add-recuperation/add-recuperation.component';
import { AddReferencementComponent } from './components/add-referencement/add-referencement.component';
import { DetailsMaterielComponent } from './components/details-materiel/details-materiel.component';
import { ListReferencementComponent } from './components/list-referencement/list-referencement.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: ListReferencementComponent,
        canActivate: [RoleGuard],
        data: { role: ['SET', 'SEL', 'CSO'] },
      },
      {
        path: ':id/nouveau-renferencement',
        component: AddReferencementComponent,
        canActivate: [RoleGuard],
        data: { role: ['SET', 'SEL'] },
      },
      {
        path: ':id/recuperation',
        component: AddRecuperationComponent,
        canActivate: [RoleGuard],
        data: { role: ['SET', 'SEL'] },
      },
      {
        path: ':id/nouvel-affectation',
        component: AddAffectationComponent,
        canActivate: [RoleGuard],
        data: { role: ['SET', 'SEL'] },
      },
      {
        path: ':id/details-renferencement',
        component: DetailsMaterielComponent,
        canActivate: [RoleGuard],
        data: { role: ['SET', 'SEL'] },
      },
      { path: '', redirectTo: '', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReferencementMaterielRoutingModule {}
