import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { EmployeeService } from '../../../../services/employee.service';
import { PositionPermissionComponent } from '../../../master/position/position-details/position-permission/position-permission.component';
import { EmployeeInfoComponent } from './employee-info/employee-info.component';
import { EmployeeHistoryComponent } from './employee-history/employee-history.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  standalone: true,
  imports: [
    WidgetsModule,
    EmployeeInfoComponent,
    EmployeeHistoryComponent,
    PositionPermissionComponent,
    RouterLink],
  templateUrl: './employee-details.component.html',
  styles: ``
})
export class EmployeeDetailsComponent {

  code = input.required<string>()
  history = signal<any[]>([])
  permissions = signal<any[]>([])
  info = signal<any>(undefined)

  title = computed(() => `${this.info()?.code} - ${this.info()?.name}`)

  constructor(service:EmployeeService) {

    effect(() => {
      if(this.code()) {
        service.findById(this.code()).subscribe(result => {
          if(result.success) {
            const {history, permissions, ...info} = result.payload
            this.history.set(history)
            this.permissions.set(permissions)
            this.info.set(info)
          }
        })
      }
    })
  }
}
