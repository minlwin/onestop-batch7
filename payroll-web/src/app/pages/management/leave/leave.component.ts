import { Component } from '@angular/core';
import { LeaveService } from '../../../services/leave.service';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { AbstractComponent } from '../../../model/abstract.component';
import { MenusService } from '../../../services/menus.service';

@Component({
  selector: 'app-leave',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './leave.component.html',
  styles: ``
})
export class LeaveComponent extends AbstractComponent{

  leaveTypes = ["Paid", "Un Paid"]
  leaveCategories = ["Sick Leave", "Family and Medical Leave", "Parental Leave", "Study Leave", "Others"]
  list:any[] = []

  override resource: string = "leaves";

  constructor(service:LeaveService, menuService:MenusService) {
    super(menuService)
    service.search().subscribe(result => this.list = result)
  }
}
