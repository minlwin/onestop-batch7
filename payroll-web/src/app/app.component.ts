import { Component, computed } from '@angular/core';
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

  anonymous = computed(() => !this.security.loginUser())
  activated = computed(() => this.security.loginUser()?.activated)

  constructor(
    private security:SecurityContextService,
    private router:Router) {
    if(security.loginUser()) {
      if(security.loginUser()?.activated) {

      } else {
        router.navigate(['/change-pass'])
      }
    } else {
      router.navigate(['/signin'])
    }
  }

  signOut() {
    this.security.loginUser.set(undefined)
    this.router.navigate(['/signin'])
  }
}
