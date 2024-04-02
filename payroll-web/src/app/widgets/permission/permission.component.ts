import { Component, input } from '@angular/core';

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styles: ``
})
export class PermissionComponent {

  state = input.required<boolean>()
}
