import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IRegisterRequest } from 'src/app/interfaces/iregisterrequest';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  public onError = false;

  public form;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      name: [
        '',
        [Validators.required, Validators.min(3), Validators.max(20)],
      ],
      password: [
        '',
        [Validators.required, Validators.min(3), Validators.max(40)],
      ],
    });
  }

  public submit(): void {
    const registerRequest = this.form.value as IRegisterRequest;
    this.authService.register(registerRequest).subscribe({
      next: (_: void) => this.router.navigate(['/login']),
      error: (_) => (this.onError = true),
    });
  }

  public back(): void {
    this.router.navigate(['/']);
  }
}
