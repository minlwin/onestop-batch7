import { Component, EventEmitter, Output, ViewChild, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { ModalDialogComponent } from '../../../../../widgets/modal-dialog/modal-dialog.component';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PositionCodeService } from '../../../../../services/position-code.service';
import { EmployeeService } from '../../../../../services/employee.service';

@Component({
  selector: 'app-change-position',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './change-position.component.html',
  styles: ``
})
export class ChangePositionComponent {

  @ViewChild(ModalDialogComponent)
  dialog!:ModalDialogComponent

  form:FormGroup

  code = input.required<string>()
  position = input.required<string>()
  department = input.required<string>()

  positions = signal<any[]>([])

  @Output()
  onSave = new EventEmitter

  constructor(builder:FormBuilder,
    codeService:PositionCodeService,
    private service:EmployeeService) {
    this.form = builder.group({
      position: ['', Validators.required],
      remark: ['', Validators.required]
    })

    effect(() => {
      if(this.position()) {
        this.form.patchValue({position : this.position()})
      }

      if(this.department()) {
        codeService.findForUse(this.department()).subscribe(result => {
          if(result.success) {
            this.positions.set(result.payload)
          }
        })
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
