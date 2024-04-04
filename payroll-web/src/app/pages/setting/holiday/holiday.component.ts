import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { HOLIDAY_TYPES, HolidayType } from '../../../model/holiday-type.model';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Pager } from '../../../model/pager.model';
import { HolidayService } from '../../../services/holiday.service';
import { MenusService } from '../../../services/menus.service';
import { AbstractComponent } from '../../../model/abstract.component';

const RESOURCE = 'holiday'

@Component({
  selector: 'app-holiday',
  standalone: true,
  imports: [CommonModule ,WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './holiday.component.html',
  styles: ``
})
export class HolidayComponent extends AbstractComponent{

  types = signal<HolidayType[]>(HOLIDAY_TYPES)

  form:FormGroup

  list = signal<any[]>([])
  page = signal<Pager | undefined>(undefined)

  override resource: string = RESOURCE;

  constructor(builder:FormBuilder, private service:HolidayService, menuService:MenusService) {

    super(menuService)

    this.form = builder.group({
      type: '',
      from: '',
      to: ''
    })

    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      if(result.success) {
        const {list, ... pager} = result.payload
        this.list.set(list)
        this.page.set(pager)
      }
    })
  }
}
