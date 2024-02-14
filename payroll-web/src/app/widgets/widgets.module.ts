import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card/card.component';
import { PaginationComponent } from './pagination/pagination.component';
import { FormGroupComponent } from './form-group/form-group.component';


@NgModule({
  declarations: [
    CardComponent,
    PaginationComponent,
    FormGroupComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CardComponent,
    PaginationComponent,
    FormGroupComponent
  ]
})
export class WidgetsModule { }
