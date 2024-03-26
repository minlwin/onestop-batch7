import { Component, computed, effect, input } from '@angular/core';
import { Information } from '../../../../model/information.model';

@Component({
  selector: 'app-department-info',
  standalone: true,
  imports: [],
  templateUrl: './department-info.component.html',
  styles: ``
})
export class DepartmentInfoComponent {

  data = input.required<any>()

  informations = computed<Information[]>(() => {
    const list:Information[] = []

    const dto = this.data()

    if(dto) {
      list.push({name: "Department Code", value: dto.code})
      list.push({name: "Department Name", value: dto.name})

      if(dto.description) {
        list.push({name: "Description", value: dto.description})
      }

      const hod = dto.hod

      if(hod) {
        list.push({name: "HOD Code", value: hod.code})
        list.push({name: "HOD Name", value: hod.name})
        list.push({name: "HOD Phone", value: hod.phone})
        list.push({name: "HOD Position", value: hod.positionName})
      }
    }

    return list
  })
}
