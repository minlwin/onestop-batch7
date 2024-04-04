import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Pager } from '../../../model/pager.model';
import { LeaveTypeService } from '../../../services/leave-type.service';
import { RouterLink } from '@angular/router';
import { MenusService } from '../../../services/menus.service';
import { AbstractComponent } from '../../../model/abstract.component';

@Component({
  selector: 'app-leave-type',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './leave-type.component.html',
  styles: ``
})
export class LeaveTypeComponent extends AbstractComponent{

  form:FormGroup
  list = signal<any[]>([])
  page = signal<Pager | undefined>(undefined)

  override resource: string = "leave type";

  constructor(builder:FormBuilder, private service:LeaveTypeService, menuService:MenusService) {

    super(menuService)

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
