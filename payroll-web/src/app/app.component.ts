import { Component, computed, effect, signal } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { SecurityContextService } from './services/security-context.service';
import { MenuGroup } from './model/menu.model';
import { MenusService } from './services/menus.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styles: [],
})
export class AppComponent {

  menus = computed<MenuGroup[]>(() => {
    if(this.security.activated()) {
      return this.menuService.getMenu()
    }

    return []
  })

  anonymous = computed(() => !this.security.loginUser())
  activated = computed(() => this.security.loginUser()?.activated)

  showMenu = computed<boolean | undefined>(() => !this.anonymous() && this.activated())
  expended = signal<boolean>(true)

  constructor(
    private menuService:MenusService,
    private security:SecurityContextService,
    private router:Router) {
    if(!security.loginUser()) {
      router.navigate(['/signin'])
    }

  }

  signOut() {
    this.security.loginUser.set(undefined)
    this.router.navigate(['/signin'])
  }

  toggleMenu() {
    this.expended.update(state => !state)
  }
}
