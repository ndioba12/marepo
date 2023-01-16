import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnregistrementMaterielRoutingModule } from './enregistrement-materiel-routing.module';
import { ListEnregistrementMatComponent } from './components/list-enregistrement-mat/list-enregistrement-mat.component';
import { EditEnregistrementMatComponent } from './components/edit-enregistrement-mat/edit-enregistrement-mat.component';
import { DetailEnregistrementMatComponent } from './components/detail-enregistrement-mat/detail-enregistrement-mat.component';
import { AddEnregistrementMatComponent } from './components/add-enregistrement-mat/add-enregistrement-mat.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AuthentificationModule } from "../../authentification/authentification.module";


@NgModule({
    declarations: [
        ListEnregistrementMatComponent,
        EditEnregistrementMatComponent,
        DetailEnregistrementMatComponent,
        AddEnregistrementMatComponent
    ],
    exports: [
        ListEnregistrementMatComponent,
        EditEnregistrementMatComponent,
        DetailEnregistrementMatComponent,
        AddEnregistrementMatComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        EnregistrementMaterielRoutingModule,
        AuthentificationModule
    ]
})
export class EnregistrementMaterielModule { }
