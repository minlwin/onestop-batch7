import { Component, computed, input } from '@angular/core';
import { Pager } from '../../model/pager.model';

@Component({
  selector: 'app-list-view',
  templateUrl: './list-view.component.html',
  styles: ``
})
export class ListViewComponent {

  title = input.required<string>()
  icon = input.required<string>()
  pager = input<Pager | undefined>(undefined)
  showPager = computed<boolean>(() => this.pager() != undefined)
}
