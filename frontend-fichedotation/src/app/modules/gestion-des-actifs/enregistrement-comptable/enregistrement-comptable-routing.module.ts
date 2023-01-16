import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from 'src/app/shared/layouts/main/main.component';
import { AddEnregistrementComptComponent } from './components/add-enregistrement-compt/add-enregistrement-compt.component';
import { EditEnregistrementComptComponent } from './components/edit-enregistrement-compt/edit-enregistrement-compt.component';
import { ListEnregistrementComptComponent } from './components/list-enregistrement-compt/list-enregistrement-compt.component';

const routes: Routes = [
  { 
    path:"",
    component:MainComponent,
    children:[
      {
        path:"",
        component:ListEnregistrementComptComponent
      },
      {
        path:":id/nouvel-enregistrement-comptable",
        component:AddEnregistrementComptComponent
      },
      {
        path:":id/modifier-enregistrement-comptable",
        component:EditEnregistrementComptComponent
      },
      { path: "", redirectTo: "", pathMatch: "full" }
    ]
   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnregistrementComptableRoutingModule { }
