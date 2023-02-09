import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AllowInputDirective} from './helpers/directives/allow-input.directive';
import {UppercaseDirective} from './helpers/directives/uppercase.directive';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { DisplayRoleDirective } from './helpers/directives/display-role.directive';
import { EtatMaterielDirective } from './helpers/directives/etat-materiel.directive';
import { StatusMaterielDirective } from './helpers/directives/status-materiel.directive';



@NgModule({
  declarations: [
    AllowInputDirective,
    UppercaseDirective,
    DisplayRoleDirective,
    EtatMaterielDirective,
    StatusMaterielDirective
  ],
  imports: [
    CommonModule,
    FormsModule,
    NgbPaginationModule,
  ],
  exports: [
    UppercaseDirective,
    AllowInputDirective,
    FormsModule,
    NgbPaginationModule,
    EtatMaterielDirective,
    StatusMaterielDirective,
    DisplayRoleDirective
  ]

})
export class SharedModule { }
