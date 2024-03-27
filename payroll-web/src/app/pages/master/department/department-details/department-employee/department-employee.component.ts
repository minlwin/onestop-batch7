import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';

@Component({
  selector: 'app-department-employee',
  standalone: true,
  imports: [CommonModule, WidgetsModule],
  templateUrl: './department-employee.component.html',
  styles: ``
})
export class DepartmentEmployeeComponent {

  list = input.required<any[]>()
}
