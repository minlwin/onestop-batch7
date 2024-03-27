import { CommonModule } from '@angular/common';
import { Component, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DepartmentService } from '../../../../services/department.service';

@Component({
  selector: 'app-department-edit',
  standalone: true,
  imports: [CommonModule, WidgetsModule, ReactiveFormsModule],
  templateUrl: './department-edit.component.html',
  styles: ``
})
export class DepartmentEditComponent {

  code = input<string>()
  edit = signal(false)

  form:FormGroup

  constructor(
    builder:FormBuilder,
    private service:DepartmentService,
    private router:Router) {

      this.form = builder.group({
      code: ['', Validators.required],
      name: ['', Validators.required],
      description: ''
    })

    effect(() => {
      const id = this.code()
      if(id) {
        this.edit.set(true)
        service.findById(id).subscribe(result => {
          if(result.success) {
            const {manager, employees, ...updateData} = result.payload
            this.form.patchValue(updateData)
          }
        })
      }
    })
  }

  save() {
    if(this.form.valid) {
      this.service.save(this.edit(), this.form.value).subscribe(result => {
        if(result.success) {
          this.router.navigate(['/master', 'department', 'details'], {queryParams: {
            code: result.payload.id
          }})
        }
      })
    }
  }
}
