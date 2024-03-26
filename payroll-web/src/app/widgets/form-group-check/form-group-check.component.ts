import { Component, input } from '@angular/core';

@Component({
  selector: 'app-form-group-check',
  templateUrl: './form-group-check.component.html',
  styles: ``
})
export class FormGroupCheckComponent {

  label = input.required()
  vertical = input<boolean>(false)
  valid = input.required<boolean | undefined>()
}
