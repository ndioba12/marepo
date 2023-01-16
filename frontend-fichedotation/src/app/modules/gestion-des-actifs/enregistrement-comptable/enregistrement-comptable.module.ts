import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnregistrementComptableRoutingModule } from './enregistrement-comptable-routing.module';
import { AddEnregistrementComptComponent } from './components/add-enregistrement-compt/add-enregistrement-compt.component';
import { EditEnregistrementComptComponent } from './components/edit-enregistrement-compt/edit-enregistrement-compt.component';
import { ListEnregistrementComptComponent } from './components/list-enregistrement-compt/list-enregistrement-compt.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AuthentificationModule } from "../../authentification/authentification.module";


@NgModule({
    declarations: [
        AddEnregistrementComptComponent,
        EditEnregistrementComptComponent,
        ListEnregistrementComptComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        EnregistrementComptableRoutingModule,
        AuthentificationModule
    ]
})
export class EnregistrementComptableModule { }
