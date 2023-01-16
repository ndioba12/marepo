import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from 'src/app/shared/layouts/main/main.component';
import { AddCessionComponent } from './components/add-cession/add-cession.component';
import { EditCessionComponent } from './components/edit-cession/edit-cession.component';
import { ListCessionComponent } from './components/list-cession/list-cession.component';

const routes: Routes = [
  {
    path:"",
    component:MainComponent,
    children:[
      {
        path:"",
        component:ListCessionComponent
      },
      {
        path:":id/nouvelle-cession",
        component:AddCessionComponent
      },
      {
        path:":id/modifier-affectation",
        component:EditCessionComponent
      },


      { path: "", redirectTo: "", pathMatch: "full" }
    ]
   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CessionRoutingModule { }
