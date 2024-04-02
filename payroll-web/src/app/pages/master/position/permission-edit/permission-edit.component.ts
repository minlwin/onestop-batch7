import { CommonModule } from '@angular/common';
import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PositionService } from '../../../../services/position.service';
import { PermissionService } from '../../../../services/permission.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-permission-edit',
  standalone: true,
  imports: [
    CommonModule, WidgetsModule, ReactiveFormsModule
  ],
  templateUrl: './permission-edit.component.html',
  styles: ``
})
export class PermissionEditComponent {

  code = input.required<string>()
  position = signal<any>(undefined)
  title = computed<string>(() => `Edit Permission for ${this.position()?.displayName}`)

  form:FormGroup

  constructor(builder:FormBuilder,
    positionService:PositionService,
    private service:PermissionService,
    private router:Router) {
    this.form = builder.group({
      posigionId: ['', Validators.required],
      permissions: builder.array([])
    })

    effect(() => {
      if(this.code()) {
        this.form.patchValue({posigionId: this.code()})
        positionService.findById(this.code()).subscribe(result => {
          if(result.success) {
            const {permission, ...position} = result.payload
            console.log(position)

            this.position.set(position)

            const array:any[] = permission
            array.forEach(a => {
              this.permissions.push(builder.group({
                resourceId: [a.resourceId, Validators.required],
                resource: a.resource,
                description: a.description,
                read: a.read,
                write: a.write,
                modify: a.modify
              }))
            })
          }
        })
      }
    })
  }

  get permissions() {
    return this.form.get('permissions') as FormArray
  }

  save() {
    if(this.form.valid) {
      this.service.save(this.form.value).subscribe(result => {
        if(result.success) {
          this.router.navigate(['/master/position/details'], {queryParams: {id: this.code()}})
        }
      })
    }
  }
}
