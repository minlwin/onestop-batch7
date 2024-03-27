import { Component, computed, effect, input, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DepartmentService } from '../../../../services/department.service';
import { CommonModule } from '@angular/common';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { DepartmentEmployeeComponent } from './department-employee/department-employee.component';
import { DepartmentPositionComponent } from './department-position/department-position.component';
import { DepartmentInfoComponent } from './department-info/department-info.component';

@Component({
  selector: 'app-department-details',
  standalone: true,
  imports: [CommonModule, WidgetsModule, RouterLink, DepartmentEmployeeComponent, DepartmentPositionComponent, DepartmentInfoComponent],
  templateUrl: './department-details.component.html',
  styles: ``
})
export class DepartmentDetailsComponent {

  code = input.required<string>()

  data = signal<any>(undefined)
  title = computed(() => this.data() ? `${this.data().code} - ${this.data().name}` : 'Department Details')

  constructor(service:DepartmentService) {

    effect(() => {
      if(this.code()) {
        service.findById(this.code()).subscribe(result => {
          if(result.success) {
            this.data.set(result.payload)
          }
        })
      }
    })
  }
}
