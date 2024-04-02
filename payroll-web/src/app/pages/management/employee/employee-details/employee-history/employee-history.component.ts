import { Component, computed, input } from '@angular/core';

@Component({
  selector: 'app-employee-history',
  standalone: true,
  imports: [],
  templateUrl: './employee-history.component.html',
  styles: ``
})
export class EmployeeHistoryComponent {

  list = input.required<any[]>()
}
