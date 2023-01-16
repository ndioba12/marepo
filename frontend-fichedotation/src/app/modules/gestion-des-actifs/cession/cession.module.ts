import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CessionRoutingModule } from './cession-routing.module';
import { ListCessionComponent } from './components/list-cession/list-cession.component';
import { AddCessionComponent } from './components/add-cession/add-cession.component';
import { EditCessionComponent } from './components/edit-cession/edit-cession.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AuthentificationModule } from "../../authentification/authentification.module";


@NgModule({
    declarations: [
        ListCessionComponent,
        AddCessionComponent,
        EditCessionComponent,
    ],
    imports: [
        CommonModule,
        SharedModule,
        CessionRoutingModule,
        AuthentificationModule
    ]
})
export class CessionModule { }
