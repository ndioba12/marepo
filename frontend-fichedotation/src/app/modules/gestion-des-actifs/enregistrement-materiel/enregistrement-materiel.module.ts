import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnregistrementMaterielRoutingModule } from './enregistrement-materiel-routing.module';
import { ListEnregistrementMatComponent } from './components/list-enregistrement-mat/list-enregistrement-mat.component';
import { EditEnregistrementMatComponent } from './components/edit-enregistrement-mat/edit-enregistrement-mat.component';
import { DetailEnregistrementMatComponent } from './components/detail-enregistrement-mat/detail-enregistrement-mat.component';
import { AddEnregistrementMatComponent } from './components/add-enregistrement-mat/add-enregistrement-mat.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AuthentificationModule } from "../../authentification/authentification.module";
import { PopupResetPasswordComponent } from '../../authentification/components/popup-reset-password/popup-reset-password.component';

@NgModule({
    declarations: [
        ListEnregistrementMatComponent,
        EditEnregistrementMatComponent,
        DetailEnregistrementMatComponent,
        AddEnregistrementMatComponent,
        PopupResetPasswordComponent
    ],
    exports: [
        ListEnregistrementMatComponent,
        EditEnregistrementMatComponent,
        DetailEnregistrementMatComponent,
        AddEnregistrementMatComponent,
        PopupResetPasswordComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        EnregistrementMaterielRoutingModule,
        AuthentificationModule
    ]
})
export class EnregistrementMaterielModule { }
