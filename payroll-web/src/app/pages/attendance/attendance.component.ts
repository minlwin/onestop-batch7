import { Component } from '@angular/core';
import { CardComponent } from '../../widgets/card/card.component';
import { AttendanceService } from '../../services/attendance.service';
import { PaginationComponent } from '../../widgets/pagination/pagination.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-attendance',
  standalone: true,
  imports: [CardComponent, PaginationComponent, RouterLink],
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
