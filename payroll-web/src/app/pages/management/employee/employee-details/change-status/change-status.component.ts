import { Component, EventEmitter, Output, ViewChild, effect, input, signal } from '@angular/core';
import { ModalDialogComponent } from '../../../../../widgets/modal-dialog/modal-dialog.component';
import { EMPLOYEE_STATUS } from '../../../../../model/constants.model';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmployeeService } from '../../../../../services/employee.service';

@Component({
  selector: 'app-change-status',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './change-status.component.html',
  styles: ``
})
export class ChangeStatusComponent {

  @ViewChild(ModalDialogComponent)
  dialog!:ModalDialogComponent

  code = input.required<string>()
  status = input.required<string>()

  statuses = signal<string[]>(EMPLOYEE_STATUS)

  @Output()
  onSave = new EventEmitter

  form:FormGroup

  constructor(builder:FormBuilder,
    private service:EmployeeService) {

    this.form = builder.group({
      status: ['', Validators.required],
      remark: ['', Validators.required]
    })

    effect(() => {
      if(this.status()) {
        this.form.patchValue({status : this.status()})
      }
    })
  }

  show() {
    this.dialog.show()
  }

  save() {
    if(this.form.valid) {
      this.service.updatePosition(this.code(), this.form.value).subscribe(result => {
        if(result.success) {
          this.dialog.hide()
          this.onSave.emit(result.success)
        }
      })
    }
  }

}
