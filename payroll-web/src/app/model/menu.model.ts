export interface MenuItem {
  type:string
  name: string
  path: string
}

export interface MenuGroup {
  title: string
  items: MenuItem[]
}
