import { MenusService } from "../services/menus.service";

export abstract class AbstractComponent {

  constructor(protected menuService:MenusService) {}

  abstract readonly resource:string

  get write():boolean {
    return this.menuService.canWrite(this.resource)
  }

  get modify():boolean {
    return this.menuService.canModify(this.resource)
  }

  get permissionRead():boolean {
    return this.menuService.canRead('permission')
  }

  get permissionModify():boolean {
    return this.menuService.canModify('permission')
  }
}
