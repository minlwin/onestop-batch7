import { Component, computed, input } from '@angular/core';
import { Pager } from '../../model/pager.model';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styles: ``
})
export class PaginationComponent {

  pageInfo = input.required<Pager>()
  show = computed<boolean>(() => this.pageInfo().total / this.pageInfo().size > 0)

}
