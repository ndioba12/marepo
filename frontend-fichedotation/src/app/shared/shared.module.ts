import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatusDirective } from './helpers/directives/status.directive';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { StatutMaterielDirective } from './helpers/directives/statut-materiel.directive';

@NgModule({

    declarations: [
        StatusDirective,
        StatutMaterielDirective
    ],
    imports: [
        CommonModule,
        FormsModule,
        NgbPaginationModule,
    ],
    exports: [
        FormsModule,
        NgbPaginationModule,
        StatusDirective,
        StatutMaterielDirective
    ],

})
export class SharedModule { }
