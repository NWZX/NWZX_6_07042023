import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ITheme } from 'src/app/interfaces/itheme';

@Component({
  selector: 'app-custom-select',
  templateUrl: './custom-select.component.html',
  styleUrls: ['./custom-select.component.scss'],
})
export class CustomSelectComponent {
  @Input() id: string;
  @Input() label: string;
  @Input() placeholder: string;
  @Input() options: ITheme[] | null;
  @Input() control!: FormControl;

  constructor() {
    this.id = '';
    this.label = '';
    this.placeholder = '';
    this.options = [];
  }
}
