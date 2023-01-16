import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from 'src/app/shared/layouts/main/main.component';
import { AddAffectationComponent } from './components/add-affectation/add-affectation.component';
import { AddReferencementComponent } from './components/add-referencement/add-referencement.component';
import { DetailsMaterielComponent } from './components/details-materiel/details-materiel.component';
import { EditReferencementComponent } from './components/edit-referencement/edit-referencement.component';
import { ListReferencementComponent } from './components/list-referencement/list-referencement.component';

const routes: Routes = [
  { 
    path:"",
    component:MainComponent,
    children:[
      {
        path:"",
        component:ListReferencementComponent
      },
      {
        path:":id/nouveau-renferencement",
        component:AddReferencementComponent
      },
      {
        path:":id/nouvel-affectation",
        component:AddAffectationComponent
      },
      {
        path:":id/details-renferencement",
        component:DetailsMaterielComponent
      },
      { path: "", redirectTo: "", pathMatch: "full" }
    ]
   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReferencementMaterielRoutingModule { }
