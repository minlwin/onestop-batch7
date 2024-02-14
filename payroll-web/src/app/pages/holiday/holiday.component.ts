import { Component } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';

@Component({
  selector: 'app-holiday',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './holiday.component.html',
  styles: ``
})
export class HolidayComponent {

  types = ['Weekends', 'Gazetted Holiday', 'Special Holiday']
}
