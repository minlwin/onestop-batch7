import { CommonModule } from '@angular/common';
import { Component, computed, effect, input, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { DepartmentService } from '../../../../services/department.service';
import { PositionCodeService } from '../../../../services/position-code.service';
import { PositionService } from '../../../../services/position.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-position-edit',
  standalone: true,
  imports: [CommonModule, WidgetsModule, ReactiveFormsModule],
  templateUrl: './position-edit.component.html',
  styles: ``
})
export class PositionEditComponent {

  code = input.required<string>()
  edit = computed<boolean>(() => this.code() != undefined)

  departments = signal<any[]>([])
  positionCodes = signal<any[]>([])

  form:FormGroup

  constructor(builder:FormBuilder,
    departmentService:DepartmentService,
    positionCodeService:PositionCodeService,
    private service:PositionService,
    private router:Router) {

    this.form = builder.group({
      department: ['', Validators.required],
      position: ['', Validators.required],
      basicSalary: [0, [Validators.required, Validators.min(99999)]],
      otPerHour: [0, [Validators.required, Validators.min(1000)]],
      anualLeaves: [0, [Validators.required, Validators.min(5)]]
    })

    this.form.get('department')?.valueChanges.subscribe(value => {
      this.positionCodes.set([])

      positionCodeService.findForCreate(value).subscribe(result => {
        if(result.success) {
          this.positionCodes.set(result.payload)
          this.form.patchValue({position : ''})
        }
      })
    })

    departmentService.search({}).subscribe(result => {
      if(result.success) {
        this.departments.set(result.payload)
        this.form.patchValue({position : ''})
      }
    })

    effect(() => {
      const id = this.code()

      if(id) {
        service.findById(id).subscribe(result => {
          if(result.success) {
            const data = result.payload

            this.form.patchValue({department: data.id.departmentCode})
            this.form.patchValue({position: data.id.positionCode})
            this.form.patchValue({basicSalary: data.basicSalary})
            this.form.patchValue({otPerHour: data.otFeesPerHour})
            this.form.patchValue({anualLeaves: data.anualLeaves})
          }
        })
      }
    })
  }

  save() {
    if(this.form.valid) {
      this.service.save(this.edit(), this.form.value).subscribe(result => {
        if(result.success) {
          this.router.navigate(['/master', 'position', 'details'], {queryParams: {id: result.payload.id}})
        }
      })
    }
  }
}
