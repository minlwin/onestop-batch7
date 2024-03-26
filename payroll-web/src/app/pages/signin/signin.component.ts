import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SignInService } from '../../services/sign-in.service';
import { SecurityContextService } from '../../services/security-context.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [CommonModule, WidgetsModule, ReactiveFormsModule],
  templateUrl: './signin.component.html',
  styles: ``
})
export class SigninComponent {

  form:FormGroup

  constructor(builder:FormBuilder,
    private service:SignInService,
    private security:SecurityContextService,
    private router:Router) {
    this.form = builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  signIn() {
    if(this.form.valid) {
      this.service.signIn(this.form.value).subscribe(result => {
        console.log(result)
        if(result.success) {
          this.security.loginUser.set(result.payload)
          this.router.navigate(['/home'])
        }
      })
    }
  }
}
