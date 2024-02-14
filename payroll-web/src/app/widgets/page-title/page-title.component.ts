import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-page-title',
  templateUrl: './page-title.component.html',
  styles: ``
})
export class PageTitleComponent {

  @Input()
  title = ""

  @Input()
  icon = ""

}
