import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';

@Component({
  selector: 'app-position-permission',
  standalone: true,
  imports: [CommonModule, WidgetsModule],
  templateUrl: './position-permission.component.html',
  styles: ``
})
export class PositionPermissionComponent {

  list = input.required<any[]>()
}
