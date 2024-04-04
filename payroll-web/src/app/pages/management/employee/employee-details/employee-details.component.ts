import { Component, ViewChild, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { EmployeeService } from '../../../../services/employee.service';
import { PositionPermissionComponent } from '../../../master/position/position-details/position-permission/position-permission.component';
import { EmployeeInfoComponent } from './employee-info/employee-info.component';
import { EmployeeHistoryComponent } from './employee-history/employee-history.component';
import { RouterLink } from '@angular/router';
import { ChangePositionComponent } from './change-position/change-position.component';
import { ChangeStatusComponent } from './change-status/change-status.component';
import { AbstractComponent } from '../../../../model/abstract.component';
import { MenusService } from '../../../../services/menus.service';

@Component({
  selector: 'app-employee-details',
  standalone: true,
  imports: [
    WidgetsModule,
    EmployeeInfoComponent,
    EmployeeHistoryComponent,
    PositionPermissionComponent,
    ChangePositionComponent,
    ChangeStatusComponent,
    RouterLink],
  templateUrl: './employee-details.component.html',
  styles: ``
})
export class EmployeeDetailsComponent extends AbstractComponent{

  code = input.required<string>()
  history = signal<any[]>([])
  permissions = signal<any[]>([])
  info = signal<any>(undefined)

  title = computed(() => `${this.info()?.code} - ${this.info()?.name}`)

  @ViewChild(ChangePositionComponent)
  postionChangeDialog!:ChangePositionComponent

  @ViewChild(ChangeStatusComponent)
  statusChangeDialog!:ChangeStatusComponent

  override resource: string = "employee";

  constructor(private service:EmployeeService, menuService:MenusService) {

    super(menuService)

    effect(() => {
      this.relaod()
    })
  }

  changePosition() {
    this.postionChangeDialog.show()
  }

  changeStatus() {
    this.statusChangeDialog.show()
  }

  onChange(event:any) {
    if(event) {
      this.relaod()
    }
  }

  private relaod() {
    if(this.code()) {
      this.service.findById(this.code()).subscribe(result => {
        if(result.success) {
          const {history, permissions, ...info} = result.payload
          this.history.set(history)
          this.permissions.set(permissions)
          this.info.set(info)
        }
      })
    }
}
}
