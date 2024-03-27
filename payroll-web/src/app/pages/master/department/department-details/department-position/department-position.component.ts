import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';

@Component({
  selector: 'app-department-position',
  standalone: true,
  imports: [CommonModule, WidgetsModule],
  templateUrl: './department-position.component.html',
  styles: ``
})
export class DepartmentPositionComponent {

  list = input.required<any[]>()
}
