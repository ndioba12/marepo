import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from 'src/app/shared/layouts/main/main.component';
import { AddInventaireComponent } from './components/add-inventaire/add-inventaire.component';
import { EditInventaireComponent } from './components/edit-inventaire/edit-inventaire.component';
import { ListInventaireComponent } from './components/list-inventaire/list-inventaire.component';

const routes: Routes = [

  {
    path:"",
    component:MainComponent,
    children:[
      {
        path:"",
        component:ListInventaireComponent
      },
      {
        path:":id/nouvelle-inventaire",
        component:AddInventaireComponent
      },
      {
        path:":id/modifier-inventaite",
        component:EditInventaireComponent
      },


      { path: "", redirectTo: "", pathMatch: "full" }
    ]
   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InventairesRoutingModule { }
