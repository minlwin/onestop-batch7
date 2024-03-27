import { Component } from '@angular/core';
import { AttendanceService } from '../../../services/attendance.service';
import { RouterLink } from '@angular/router';
import { WidgetsModule } from '../../../widgets/widgets.module';

@Component({
  selector: 'app-attendance',
  standalone: true,
  imports: [WidgetsModule, RouterLink],
  templateUrl: './attendance.component.html',
  styles: ``
})
export class AttendanceComponent {

  statusList = ['Attend', 'Leave', 'Late', 'Early Out', 'Absent']
  dataList:any[] = []

  constructor(service:AttendanceService) {
    service.search().subscribe(result => this.dataList = result)
  }
}
