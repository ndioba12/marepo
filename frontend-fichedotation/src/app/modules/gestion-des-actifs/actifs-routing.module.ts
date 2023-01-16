import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  // Profil Comptable
  // {
  //   path: 'liste-des-materiels', loadChildren:() => import("./enregistrement-comptable/enregistrement-comptable.module").then(mod=> mod.EnregistrementComptableModule)
  // },
  // Profil Achats
  // {
  //   path: 'liste-des-materiels', loadChildren:() => import("./enregistrement-materiel/enregistrement-materiel.module").then(mod=> mod.EnregistrementMaterielModule)
  // },
  // Profil Support Technique ou Service Logistique
  {
    path: 'liste-des-materiels', loadChildren:() => import("./referencement-materiel/referencement-materiel.module").then(mod=> mod.ReferencementMaterielModule)
  },
  // Profil Personnel
  {
    path: 'liste-des-affectations', loadChildren:() => import("./reception-materiel/reception-materiel.module").then(mod=> mod.ReceptionMaterielModule)
  },
  // Profil Controleur de Gestion
  {
    path: 'inventaires', loadChildren:() => import("./inventaires/inventaires.module").then(mod=> mod.InventairesModule)
  },
  // Profil CSO
  {
    path: 'cessions', loadChildren:() => import("./cession/cession.module").then(mod=> mod.CessionModule)
  },
  { path: "", redirectTo: "liste-des-materiels", pathMatch: "full" }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActifsRoutingModule { }
