import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InventairesRoutingModule } from './inventaires-routing.module';
import { AddInventaireComponent } from './components/add-inventaire/add-inventaire.component';
import { EditInventaireComponent } from './components/edit-inventaire/edit-inventaire.component';
import { ListInventaireComponent } from './components/list-inventaire/list-inventaire.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AuthentificationModule } from "../../authentification/authentification.module";


@NgModule({
    declarations: [
        AddInventaireComponent,
        EditInventaireComponent,
        ListInventaireComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        InventairesRoutingModule,
        AuthentificationModule
    ]
})
export class InventairesModule { }
