import { AfterViewInit, Component, input } from '@angular/core';

declare const bootstrap:any

@Component({
  selector: 'app-modal-dialog',
  templateUrl: './modal-dialog.component.html',
  styles: ``
})
export class ModalDialogComponent implements AfterViewInit{

  title = input.required<string>()
  dialogId = input.required<string>()

  dialog:any

  ngAfterViewInit(): void {
    this.dialog = new bootstrap.Modal(`#${this.dialogId()}`)
  }

  show() {
    this.dialog?.show()
  }

  hide() {
    this.dialog?.hide()
  }
}
