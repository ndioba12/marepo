import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleGuard } from 'src/app/shared/helpers/guards/role.guard';
import { MainComponent } from 'src/app/shared/layouts/main/main.component';
import { AddEnregistrementMatComponent } from './components/add-enregistrement-mat/add-enregistrement-mat.component';
import { DetailEnregistrementMatComponent } from './components/detail-enregistrement-mat/detail-enregistrement-mat.component';
import { EditEnregistrementMatComponent } from './components/edit-enregistrement-mat/edit-enregistrement-mat.component';
import { ListEnregistrementMatComponent } from './components/list-enregistrement-mat/list-enregistrement-mat.component';

const routes: Routes = [
  { 
    path:"",
    component:MainComponent,
    children:[
      {
        path:"",
        component:ListEnregistrementMatComponent,
        canActivate: [RoleGuard],
        data: { role: ['ACH'] },
      },
      {
        path:"nouvel-enregistrement-materiel",
        component:AddEnregistrementMatComponent,
        canActivate: [RoleGuard],
        data: { role: ['ACH'] },
      },
      {
        path:":id/modifier-enregistrement-materiel",
        component:EditEnregistrementMatComponent,
        canActivate: [RoleGuard],
        data: { role: ['ACH'] },
      },
      {
        path:":id/detail-enregistrement-materiel",
        component:DetailEnregistrementMatComponent,
        canActivate: [RoleGuard],
        data: { role: ['ACH'] },
      },
      { path: "", redirectTo: "", pathMatch: "full" }
    ]
   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnregistrementMaterielRoutingModule { }
