import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { DepartmentService } from '../../../services/department.service';

@Component({
  selector: 'app-department',
  standalone: true,
  imports: [WidgetsModule, CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './department.component.html',
  styles: ``
})
export class DepartmentComponent {
  form:FormGroup
  list = signal<any[]>([])

  constructor(builder:FormBuilder, private service:DepartmentService) {
    this.form = builder.group({
      manager: '',
      department: ''
    })

    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      if(result.success) {
        this.list.set(result.payload)
      }
    })
  }
}
