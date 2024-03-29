import { Component, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LeaveTypeService } from '../../../../services/leave-type.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-leave-type-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './leave-type-edit.component.html',
  styles: ``
})
export class LeaveTypeEditComponent {

  edit = signal<boolean>(false)
  id = input<number>()

  form:FormGroup

  constructor(builder:FormBuilder, private service:LeaveTypeService, private router:Router) {
    this.form = builder.group({
      id: 0,
      name: ['', Validators.required],
      paidDays: 0,
      remark: ''
    })

    effect(() => {
      const idValue = this.id()

      if(idValue) {
        service.findById(idValue).subscribe(result => {
          if(result.success) {
            this.edit.set(true)
            this.form.patchValue(result.payload)
          }
        })
      }
    })
  }

  save() {
    if(this.form.valid) {
      this.service.save(this.edit(), this.form.value).subscribe(result => {
        if(result.success) {
          this.router.navigate(['/settings/leave-type/list'])
        }
      })
    }
  }

}
