import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { AbstractComponent } from '../../../model/abstract.component';
import { MenusService } from '../../../services/menus.service';

@Component({
  selector: 'app-deduction',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './deduction.component.html',
  styles: ``
})
export class DeductionComponent extends AbstractComponent{

  override resource: string = "allowance";

  constructor(menuService:MenusService) {
    super(menuService)
  }

}
