import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { MenusService } from '../../../services/menus.service';
import { AbstractComponent } from '../../../model/abstract.component';

@Component({
  selector: 'app-allowance',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './allowance.component.html',
  styles: ``
})
export class AllowanceComponent extends AbstractComponent{

  override resource: string = "allowance";

  constructor(menuService:MenusService) {
    super(menuService)
  }
}
