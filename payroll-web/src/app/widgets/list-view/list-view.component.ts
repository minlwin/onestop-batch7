import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-list-view',
  templateUrl: './list-view.component.html',
  styles: ``
})
export class ListViewComponent {

  @Input()
  title = ""

  @Input()
  icon = ""
}
