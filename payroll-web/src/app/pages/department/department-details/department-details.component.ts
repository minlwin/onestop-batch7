import { Component, computed, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { DepartmentService } from '../../../services/department.service';
import { CommonModule } from '@angular/common';
import { WidgetsModule } from '../../../widgets/widgets.module';
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

  data = signal<any>(undefined)
  title = computed(() => this.data() ? `${this.data().code} - ${this.data().name}` : 'Department Details')

  constructor(route:ActivatedRoute, service:DepartmentService) {
    route.queryParamMap.subscribe(map => {
      const code = map.get('code')

      if(code) {
        service.findById(code).subscribe(result => {
          if(result.success) {
            this.data.set(result.payload)
          }
        })
      }
    })
  }
}
