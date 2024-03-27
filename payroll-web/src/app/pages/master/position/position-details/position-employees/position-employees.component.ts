import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';

@Component({
  selector: 'app-position-employees',
  standalone: true,
  imports: [CommonModule, WidgetsModule],
  templateUrl: './position-employees.component.html',
  styles: ``
})
export class PositionEmployeesComponent {

  list = input.required<any[]>()

}
