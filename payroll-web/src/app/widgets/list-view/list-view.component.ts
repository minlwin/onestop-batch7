import { Component, Input, input } from '@angular/core';

@Component({
  selector: 'app-list-view',
  templateUrl: './list-view.component.html',
  styles: ``
})
export class ListViewComponent {

  title = input.required<string>()
  icon = input.required<string>()

  showPager = input<boolean>(false)
}
