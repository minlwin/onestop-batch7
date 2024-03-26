import { Component, input } from '@angular/core';

@Component({
  selector: 'app-input-group',
  templateUrl: './input-group.component.html',
  styles: ``
})
export class InputGroupComponent {
  valid = input.required<boolean>()
}
