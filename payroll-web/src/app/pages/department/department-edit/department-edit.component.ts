import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentService } from '../../../services/department.service';

@Component({
  selector: 'app-department-edit',
  standalone: true,
  imports: [CommonModule, WidgetsModule, ReactiveFormsModule],
  templateUrl: './department-edit.component.html',
  styles: ``
})
export class DepartmentEditComponent {

  edit = signal(false)
  form:FormGroup

  constructor(
    builder:FormBuilder,
    route:ActivatedRoute,
    private service:DepartmentService,
    private router:Router) {
    this.form = builder.group({
      code: ['', Validators.required],
      name: ['', Validators.required],
      description: ''
    })

    route.queryParamMap.subscribe(map => {
      const code = map.get('code')

      if(code) {
        this.edit.set(true)
        service.findById(code).subscribe(result => {
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
          this.router.navigate(['/department', 'details'], {queryParams: {
            code: result.payload.id
          }})
        }
      })
    }
  }
}
