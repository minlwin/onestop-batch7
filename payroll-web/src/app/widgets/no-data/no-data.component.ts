import { Component, input } from '@angular/core';

@Component({
  selector: 'app-no-data',
  templateUrl: './no-data.component.html',
  styles: ``
})
export class NoDataComponent {
  domain = input.required()
}
