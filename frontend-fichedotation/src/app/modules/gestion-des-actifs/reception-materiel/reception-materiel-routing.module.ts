import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from 'src/app/shared/layouts/main/main.component';
import { DetailReceptionComponent } from './components/detail-reception/detail-reception.component';
import { ListReceptionComponent } from './components/list-reception/list-reception.component';

const routes: Routes = [
  { 
    path:"",
    component:MainComponent,
    children:[
      {
        path:"",
        component:ListReceptionComponent
      },
      {
        path:":id/detail-materiel",
        component:DetailReceptionComponent
      },
      { path: "", redirectTo: "", pathMatch: "full" }
    ]
   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReceptionMaterielRoutingModule { }
