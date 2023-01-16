import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReceptionMaterielRoutingModule } from './reception-materiel-routing.module';
import { DetailReceptionComponent } from './components/detail-reception/detail-reception.component';
import { ListReceptionComponent } from './components/list-reception/list-reception.component';
import { FormsModule } from '@angular/forms';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from "../../../shared/shared.module";
import { AuthentificationModule } from "../../authentification/authentification.module";


@NgModule({
    declarations: [
        DetailReceptionComponent,
        ListReceptionComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        NgbPaginationModule,
        ReceptionMaterielRoutingModule,
        SharedModule,
        AuthentificationModule
    ]
})
export class ReceptionMaterielModule { }
