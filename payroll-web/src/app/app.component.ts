import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { SecurityContextService } from './services/security-context.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.component.html',
  styles: [],
})
export class AppComponent {
  title = 'payroll-web';

  constructor(
    private security:SecurityContextService,
    router:Router) {
    if(security.loginUser) {
      if(security.loginUser.activated) {

      } else {
        router.navigate(['/change-pass'])
      }
    } else {
      router.navigate(['/signin'])
    }
  }

  get anonymous() {
    return !this.security.loginUser
  }

  get activated() {
    return this.security.loginUser?.activated
  }
}
