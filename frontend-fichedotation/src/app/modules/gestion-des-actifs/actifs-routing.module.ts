import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleGuard } from 'src/app/shared/helpers/guards/role.guard';

const routes: Routes = [
  // Profil Comptable
  {
    path: 'comptable',
    loadChildren:() => import("./enregistrement-comptable/enregistrement-comptable.module").then(
      (mod)=> mod.EnregistrementComptableModule),
    canActivate: [RoleGuard],
    data: { role: ['CPT', 'CSO'] }
  },

  // Profil Achats
  {
    path: 'liste-des-materiels-achats',
    loadChildren: () =>
      import('./enregistrement-materiel/enregistrement-materiel.module').then(
        (mod) => mod.EnregistrementMaterielModule),
    canActivate: [RoleGuard],
    data: { role: ['ACH', 'CSO'] }
  },
  // Profil Support Technique
  // {
  //   path: 'liste-des-materiels-tech',
  //   loadChildren:() => import("./referencement-materiel/referencement-materiel.module").then(
  //     mod=> mod.ReferencementMaterielModule),
  //   canActivate: [RoleGuard],
  //   data: { role: ['SET', 'CSO'] }
  // },

  // Profil Service Logistique ou logistique
  {
    path: 'liste-des-materiels',
    loadChildren:() => import("./referencement-materiel/referencement-materiel.module").then(
      (mod)=> mod.ReferencementMaterielModule),
    canActivate: [RoleGuard],
    data: { role: ['SET', 'SEL', 'CSO', 'ACH'] }
  },
  // Profil Personnel
  {
    path: 'liste-des-affectations',
    loadChildren: () =>
      import('./reception-materiel/reception-materiel.module').then(
        (mod) => mod.ReceptionMaterielModule),
    canActivate: [RoleGuard],
    data: { role: ['PER', 'CSO'] }
  },
  // Profil Controleur de Gestion
  {
    path: 'inventaires',
    loadChildren: () =>
      import('./inventaires/inventaires.module').then(
        (mod) => mod.InventairesModule),
    canActivate: [RoleGuard],
    data: { role: ['CTG', 'CSO'] }
  },
  // Profil CSO
  {
    path: 'cessions',
    loadChildren: () =>
      import('./cession/cession.module').then(
        (mod) => mod.CessionModule),
    canActivate: [RoleGuard],
    data: { role: ['CSO'] }
  },
  { path: '', redirectTo: 'liste-des-materiels', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ActifsRoutingModule {}
