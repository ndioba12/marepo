import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActifsRoutingModule } from './actifs-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    SharedModule,
    ActifsRoutingModule
  ]
})
export class ActifsModule { }
