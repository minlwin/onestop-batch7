import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card/card.component';
import { PaginationComponent } from './pagination/pagination.component';
import { FormGroupComponent } from './form-group/form-group.component';
import { ListViewComponent } from './list-view/list-view.component';
import { PageTitleComponent } from './page-title/page-title.component';


@NgModule({
  declarations: [
    CardComponent,
    PaginationComponent,
    FormGroupComponent,
    ListViewComponent,
    PageTitleComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CardComponent,
    PaginationComponent,
    FormGroupComponent,
    ListViewComponent,
    PageTitleComponent
  ]
})
export class WidgetsModule { }