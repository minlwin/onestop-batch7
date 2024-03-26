import { Component, input } from '@angular/core';

@Component({
  selector: 'app-page-title',
  templateUrl: './page-title.component.html',
  styles: ``
})
export class PageTitleComponent {

  title = input.required<string>()
  icon = input.required<string>()

}
