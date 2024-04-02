import { Component, computed, input } from '@angular/core';

@Component({
  selector: 'app-employee-info',
  standalone: true,
  imports: [],
  templateUrl: './employee-info.component.html',
  styles: ``
})
export class EmployeeInfoComponent {

  employee = input.required<any>()

  informations = computed(() => {
    const list:any[] = []

    if(this.employee()) {
      const data = this.employee()

      list.push({name: 'Employee Code', value: data.code})
      list.push({name: 'Employee Name', value: data.name})
      list.push({name: 'Department', value: data.department})
      list.push({name: 'Position', value: data.positionName})
      list.push({name: 'Status', value: data.status})

      list.push({name: 'Phone', value: data.phone})
      list.push({name: 'Email Address', value: data.email})
      list.push({name: 'Date of Birth', value: data.dateOfBirth})
      list.push({name: 'Gender', value: data.gender})


      list.push({name: 'Assign At', value: data.assignAt})

      if(data.status == 'Permenant') {
        list.push({name: 'Provation Pass At', value: data.provationPassAt})
      }

      if(data.status == 'Retired') {
        list.push({name: 'Retired At', value: data.retiredAt})
      }

      if(data.remark) {
        list.push({name: 'Remark', value: data.remark})
      }
    }
    return list
  })

}
