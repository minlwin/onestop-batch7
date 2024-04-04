import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { MenusService } from '../../../services/menus.service';
import { AbstractComponent } from '../../../model/abstract.component';

@Component({
  selector: 'app-payroll',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './payroll.component.html',
  styles: ``
})
export class PayrollComponent extends AbstractComponent{

  override resource: string = "payroll";

  constructor(menuService:MenusService) {
    super(menuService)
  }
}
