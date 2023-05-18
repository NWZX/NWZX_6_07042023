import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ITheme } from 'src/app/interfaces/itheme';
import { IUser } from 'src/app/interfaces/iuser';
import { SessionService } from 'src/app/services/session.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent {
  public user?: IUser;
  public hide = true;
  public onError = false;

  //FIXME: Add validation to form
  public form;

  constructor(
    private userService: UserService,
    private sessionService: SessionService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      name: [
        '',
        [Validators.required, Validators.min(3), Validators.max(20)],
      ],
    });
  }

  public ngOnInit(): void {
    this.userService.getUser().subscribe({
      next: (user: IUser) => {
        this.user = user;
        this.form.setValue({ email: user.email, name: user.name });
      },
    });
  }

  public submit(): void {
    if (this.form.value.email != this.user?.email || this?.form.value.name != this.user?.name) {
      const putRequest = this.form.value as Partial<IUser>;
      this.userService.updateUser(putRequest).subscribe({
        next: (response: IUser) => {
          if (this.form.value.email != this.user?.email)
            this.logout();
          else
            this.user = response;
        },
        error: (error) => console.error(error),
      });
    }
  }

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['/login']);
  }
}
