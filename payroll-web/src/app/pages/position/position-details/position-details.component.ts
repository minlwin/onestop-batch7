import { Component, computed, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { PositionService } from '../../../services/position.service';
import { CommonModule } from '@angular/common';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { PositionInfoComponent } from './position-info/position-info.component';
import { PositionPermissionComponent } from './position-permission/position-permission.component';
import { PositionEmployeesComponent } from './position-employees/position-employees.component';

@Component({
  selector: 'app-position-details',
  standalone: true,
  imports: [
    CommonModule, WidgetsModule, RouterLink,
    PositionInfoComponent,
    PositionPermissionComponent,
    PositionEmployeesComponent
  ],
  templateUrl: './position-details.component.html',
  styles: ``
})
export class PositionDetailsComponent {

  data = signal<any>(undefined)

  title = computed(() => this.data() ? this.data().displayName : 'Position Details')

  constructor(route:ActivatedRoute, service:PositionService) {
    route.queryParamMap.subscribe(map => {
      const id = map.get("id")

      if(id) {
        service.findById(id).subscribe(result => {
          if(result.success) {
            this.data.set(result.payload)
          }
        })
      }
    })
  }

}
