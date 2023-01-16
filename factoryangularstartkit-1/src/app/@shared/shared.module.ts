import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AllowInputDirective} from './helpers/directives/allow-input.directive';
import {UppercaseDirective} from './helpers/directives/uppercase.directive';



@NgModule({
  declarations: [
    AllowInputDirective,
    UppercaseDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    UppercaseDirective,
    AllowInputDirective
  ]
})
export class SharedModule { }
