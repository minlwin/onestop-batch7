import { Component, computed, effect, input, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PositionService } from '../../../../services/position.service';
import { CommonModule } from '@angular/common';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { PositionInfoComponent } from './position-info/position-info.component';
import { PositionPermissionComponent } from './position-permission/position-permission.component';
import { PositionEmployeesComponent } from './position-employees/position-employees.component';
import { MenusService } from '../../../../services/menus.service';
import { AbstractComponent } from '../../../../model/abstract.component';

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
export class PositionDetailsComponent extends AbstractComponent{

  id = input.required<string>()
  data = signal<any>(undefined)
  title = computed(() => this.data() ? this.data().displayName : 'Position Details')

  override resource: string = "position";

  constructor(service:PositionService, menuService:MenusService) {

    super(menuService)

    effect(() => {
      if(this.id()) {
        service.findById(this.id()).subscribe(result => {
          if(result.success) {
            this.data.set(result.payload)
          }
        })
      }
    })
  }
}
