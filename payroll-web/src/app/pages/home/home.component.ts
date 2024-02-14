import { Component } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './home.component.html',
  styles: ``
})
export class HomeComponent {

}
