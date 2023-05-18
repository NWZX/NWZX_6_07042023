import { Component, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ILoginRequest } from 'src/app/interfaces/iloginrequest';
import { ISession } from 'src/app/interfaces/isession';
import { AuthService } from 'src/app/services/auth.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  public hide = true;
  public onError = false;

  //FIXME: Add validation to form
  public form;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.min(3)]],
    });
  }

  public submit(): void {
    const loginRequest = this.form.value as ILoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: ISession) => {
        this.sessionService.logIn(response);
        this.router.navigate(['/articles']);
      },
      error: (error) => console.error(error),
    });
  }
  
  public back(): void {
    this.router.navigate(['/']);
  }
}
