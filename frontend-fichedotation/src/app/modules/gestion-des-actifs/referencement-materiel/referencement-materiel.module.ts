import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReferencementMaterielRoutingModule } from './referencement-materiel-routing.module';
import { AddReferencementComponent } from './components/add-referencement/add-referencement.component';
import { EditReferencementComponent } from './components/edit-referencement/edit-referencement.component';
import { ListReferencementComponent } from './components/list-referencement/list-referencement.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { DetailsMaterielComponent } from './components/details-materiel/details-materiel.component';
import { AddAffectationComponent } from './components/add-affectation/add-affectation.component';
import { AddRecuperationComponent } from './components/add-recuperation/add-recuperation.component';
import { FormsModule } from '@angular/forms';
import { NgbAccordionModule, NgbAlertModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EditAffectationComponent } from './components/edit-affectation/edit-affectation.component';
import { EditRecuperationComponent } from './components/edit-recuperation/edit-recuperation.component';
import { AuthentificationModule } from "../../authentification/authentification.module";


@NgModule({
    declarations: [
        AddReferencementComponent,
        EditReferencementComponent,
        ListReferencementComponent,
        DetailsMaterielComponent,
        AddAffectationComponent,
        EditAffectationComponent,
        AddRecuperationComponent,
        EditRecuperationComponent,
    ],
    exports: [
        AddReferencementComponent,
        EditReferencementComponent,
        ListReferencementComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        NgbModule,
        NgbAlertModule,
        NgbAccordionModule,
        SharedModule,
        ReferencementMaterielRoutingModule,
        AuthentificationModule
    ]
})
export class ReferencementMaterielModule { }
