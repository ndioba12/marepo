import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AllowInputDirective} from './helpers/directives/allow-input.directive';
import {UppercaseDirective} from './helpers/directives/uppercase.directive';
import { StatusDirective } from './helpers/directives/status.directive';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { StatutMaterielDirective } from './helpers/directives/statut-materiel.directive';
import { DisplayRoleDirective } from './helpers/directives/display-role.directive';



@NgModule({
  declarations: [
    AllowInputDirective,
    UppercaseDirective,
    StatusDirective,
    StatutMaterielDirective
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
    StatusDirective,
    StatutMaterielDirective
  ]

})
export class SharedModule { }
