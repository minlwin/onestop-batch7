import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card/card.component';
import { PaginationComponent } from './pagination/pagination.component';
import { FormGroupComponent } from './form-group/form-group.component';
import { ListViewComponent } from './list-view/list-view.component';


@NgModule({
  declarations: [
    CardComponent,
    PaginationComponent,
    FormGroupComponent,
    ListViewComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CardComponent,
    PaginationComponent,
    FormGroupComponent,
    ListViewComponent
  ]
})
export class WidgetsModule { }
