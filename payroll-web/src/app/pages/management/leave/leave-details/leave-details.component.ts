import { Component } from '@angular/core';
import { AbstractComponent } from '../../../../model/abstract.component';
import { MenusService } from '../../../../services/menus.service';

@Component({
  selector: 'app-leave-details',
  standalone: true,
  imports: [],
  templateUrl: './leave-details.component.html',
  styles: ``
})
export class LeaveDetailsComponent extends AbstractComponent{

  override resource: string = "leave";

  constructor(menuService:MenusService) {
    super(menuService)
  }
}
