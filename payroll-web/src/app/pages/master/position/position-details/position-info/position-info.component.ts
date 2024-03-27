import { Component, computed, input } from '@angular/core';
import { Information } from '../../../../../model/information.model';

@Component({
  selector: 'app-position-info',
  standalone: true,
  imports: [],
  templateUrl: './position-info.component.html',
  styles: ``
})
export class PositionInfoComponent {

  data = input.required<any>()

  informations = computed<Information[]>(() => {
    const list:Information[] = []

    const dto = this.data()

    if(dto) {
      list.push({name: 'Position Code', value: dto.id.code})
      list.push({name: 'Position Name', value: dto.displayName})
      list.push({name: 'Basic Salary', value: dto.basicSalary})
      list.push({name: 'OT Fees', value: dto.otFeesPerHour})
      list.push({name: 'Anual Leave', value: dto.anualLeaves})
    }

    return list
  })
}
