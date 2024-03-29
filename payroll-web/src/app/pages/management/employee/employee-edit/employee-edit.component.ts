import { CommonModule } from '@angular/common';
import { Component, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmployeeService } from '../../../../services/employee.service';

@Component({
  selector: 'app-employee-edit',
  standalone: true,
  imports: [CommonModule, WidgetsModule, ReactiveFormsModule,RouterLink],
  templateUrl: './employee-edit.component.html',
  styles: ``
})
export class EmployeeEditComponent {

  edit = signal<boolean>(false)
  code = input<string>()

  departments = signal<any[]>([])
  positions = signal<any[]>([])

  form:FormGroup

  constructor(builder:FormBuilder,
    private service:EmployeeService, private router:Router) {
    this.form = builder.group({
      name: ['', Validators.required],
      department: ['', Validators.required],
      position: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      gender: ['', Validators.required],
      dob: ['', Validators.required],
      assignDate: ['', Validators.required],
      status: ['', Validators.required],
      remark: [''],
    })
  }

  save() {
    if(this.form.valid) {
      this.service.create(this.form.value).subscribe(result => {
        if(result.success) {
          this.router.navigate(['../', 'details'], {queryParams: {code: result.payload.id}})
        }
      })
    }
  }
}
