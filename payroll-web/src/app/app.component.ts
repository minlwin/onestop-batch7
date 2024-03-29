import { Component, computed, signal } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { SecurityContextService } from './services/security-context.service';
import menus from '../assets/model/menu-model.json'
import { MenuGroup } from './model/menu.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styles: [],
})
export class AppComponent {

  menus = signal<MenuGroup[]>(menus)

  anonymous = computed(() => !this.security.loginUser())
  activated = computed(() => this.security.loginUser()?.activated)

  showMenu = signal<boolean>(true)

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

  toggleMenu() {
    this.showMenu.update(state => !state)
  }
}
