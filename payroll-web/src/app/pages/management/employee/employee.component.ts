import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { EmployeeService } from '../../../services/employee.service';
import { Pager } from '../../../model/pager.model';
import { RouterLink } from '@angular/router';
import { AbstractComponent } from '../../../model/abstract.component';
import { MenusService } from '../../../services/menus.service';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [WidgetsModule, CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './employee.component.html',
  styles: ``
})
export class EmployeeComponent extends AbstractComponent{

  form:FormGroup
  list = signal<any[]>([])
  page = signal<Pager | undefined>(undefined)

  override resource: string = "employee";

  constructor(builder:FormBuilder, private service:EmployeeService, menuService:MenusService) {

    super(menuService)
    this.form = builder.group({
      department: '',
      position: '',
      name: ''
    })

    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      if(result.success) {
        const {list, ... pager} = result.payload
        this.list.set(list)
        this.page.set(pager)
      }
    })
  }
}
