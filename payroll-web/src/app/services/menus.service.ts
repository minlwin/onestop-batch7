import { Injectable, computed } from '@angular/core';
import { MenuGroup } from '../model/menu.model';
import menuGroups from '../../assets/model/menu-model.json';
import { SecurityContextService } from './security-context.service';

@Injectable({
  providedIn: 'root'
})
export class MenusService {

  private menus:MenuGroup[] = menuGroups
  private user = computed(() => this.security.loginUser())

  private permissions = computed<PermissionMap | undefined>(() => {
    const loginUser = this.user()
    if(loginUser) {
      const result:PermissionMap = {}
      const array:Permission[] = loginUser['permissions']
      array.forEach(a => result[a.resource.toLowerCase()] = a)
      return result
    }

    return undefined
  })

  constructor(private security:SecurityContextService) {
  }

  getMenu() {
    if(!this.user()?.activated) {
      return []
    }

    if(this.isAdmin(this.user()!.authorities)) {
      return [...this.menus]
    }

    return this.getEmployeeMenu()
  }

  private isAdmin(authorities:string[]):boolean {
    return authorities.filter(a => a == 'Admin').pop() != undefined
  }

  private getEmployeeMenu():MenuGroup[] {

    const result:MenuGroup[] = []
    const permissionMap = this.permissions()
    if(permissionMap) {
      this.menus.forEach(group => {
        const items = group.items
          .filter(item => permissionMap[item.type]?.enable)
        if(items.length > 0) {
          result.push({title: group.title, items: items})
        }
      })
    }

    return result
  }

}

export interface Permission {
  readonly resource: string
  readonly resourceType: string
  readonly resourceUrl: string
  readonly read: boolean
  readonly write: boolean
  readonly modify: boolean
  readonly enable: boolean
}

export interface PermissionMap {
  [name:string] : Permission
}
