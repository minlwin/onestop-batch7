import { Component } from '@angular/core';
import { LeaveService } from '../../../services/leave.service';
import { WidgetsModule } from '../../../widgets/widgets.module';

@Component({
  selector: 'app-leave',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './leave.component.html',
  styles: ``
})
export class LeaveComponent {

  leaveTypes = ["Paid", "Un Paid"]
  leaveCategories = ["Sick Leave", "Family and Medical Leave", "Parental Leave", "Study Leave", "Others"]
  list:any[] = []

  constructor(service:LeaveService) {
    service.search().subscribe(result => this.list = result)
  }
}
