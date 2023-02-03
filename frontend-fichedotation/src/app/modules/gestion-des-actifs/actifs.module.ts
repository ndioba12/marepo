import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActifsRoutingModule } from './actifs-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    SharedModule,
    ActifsRoutingModule,
    ReactiveFormsModule,
  ]
})
export class ActifsModule { }
