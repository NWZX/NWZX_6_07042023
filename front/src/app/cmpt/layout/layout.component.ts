import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent {
  @Input() displayTopBar: boolean;
  @Input() isConnected: boolean;
  @Input() containerClass: string;

  constructor() {
    this.displayTopBar = false;
    this.isConnected = false;
    this.containerClass = "";
  }
}
