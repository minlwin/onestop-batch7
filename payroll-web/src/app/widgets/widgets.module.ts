import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card/card.component';
import { PaginationComponent } from './pagination/pagination.component';


@NgModule({
  declarations: [
    CardComponent,
    PaginationComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CardComponent,
    PaginationComponent
  ]
})
export class WidgetsModule { }
