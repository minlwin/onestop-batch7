import { CommonModule } from '@angular/common';
import { Component, effect, input } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

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

  form:FormGroup

  constructor(builder:FormBuilder) {
    this.form = builder.group({

    })

    effect(() => {

    })
  }
}
