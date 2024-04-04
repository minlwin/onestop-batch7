import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SignInService } from '../../../services/sign-in.service';
import { SecurityContextService } from '../../../services/security-context.service';
import { ChangePasswordService } from '../../../services/change-password.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-pass',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './change-pass.component.html',
  styles: ``
})
export class ChangePassComponent {

  form:FormGroup

  constructor(builder:FormBuilder,
    private service:ChangePasswordService,
    private signInService:SignInService,
    private security:SecurityContextService,
    private router:Router) {
    this.form = builder.group({
      oldPass: ['', Validators.required],
      newPass: ['', Validators.required]
    })
  }

  changePassword() {
    if(this.form.valid) {
      this.service.changePassword(this.form.value).subscribe(result => {
        if(result.success) {
          const signInForm = {
            username: this.security.loginUser()?.loginId,
            password: this.form.get('newPass')?.value
          }
          this.signInService.signIn(signInForm).subscribe(result => {
            if(result.success) {
              this.security.loginUser.set(result.payload)
              this.router.navigate(['/home'])
            }
          })
        }
      })
    }
  }
}
