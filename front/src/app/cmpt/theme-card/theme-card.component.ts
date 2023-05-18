import { Component, Input } from '@angular/core';
import { ITheme } from 'src/app/interfaces/itheme';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss'],
})
export class ThemeCardComponent {
  @Input() theme: ITheme | undefined;
  @Input() unsubscribe: boolean;

  constructor(private userService: UserService) {
    this.theme = undefined;
    this.unsubscribe = false;
  }

  public subscribeTheme(): void {
    if (this.theme) {
      this.userService.subscribeTheme(this.theme.id).subscribe({
        next: () => {
          this.unsubscribe = true;
        },
      });
    }
  }
  public unsubscribeTheme(): void {
    if (this.theme) {
      this.userService.unsubscribeTheme(this.theme.id).subscribe({
        next: () => {
          this.unsubscribe = false;
        },
      });
    }
  }
}
