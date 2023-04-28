import { Component, Input } from '@angular/core';
import { ITheme } from 'src/app/interfaces/itheme';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss']
})
export class ThemeCardComponent {
  @Input() theme: ITheme | undefined;
  @Input() unsubscribe: boolean;
  constructor() {
    this.theme = undefined;
    this.unsubscribe = false;
  }
}
