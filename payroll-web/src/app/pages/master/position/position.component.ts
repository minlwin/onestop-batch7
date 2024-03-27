import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { PositionService } from '../../../services/position.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-position',
  standalone: true,
  imports: [CommonModule, WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './position.component.html',
  styles: ``
})
export class PositionComponent {

  list = signal<any[]>([])
  form:FormGroup

  constructor(builder:FormBuilder, private service:PositionService) {
    this.form = builder.group({
      department: '',
      position: ''
    })

    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      if(result.success) {
        this.list.set(result.payload)
      }
    })
  }
}
