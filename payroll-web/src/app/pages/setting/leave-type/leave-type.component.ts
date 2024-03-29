import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Pager } from '../../../model/pager.model';
import { LeaveTypeService } from '../../../services/leave-type.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-leave-type',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './leave-type.component.html',
  styles: ``
})
export class LeaveTypeComponent {

  form:FormGroup
  list = signal<any[]>([])
  page = signal<Pager | undefined>(undefined)

  constructor(builder:FormBuilder, private service:LeaveTypeService) {
    this.form = builder.group({
      name: ''
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
