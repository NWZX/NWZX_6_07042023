import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-mobile-menu',
  templateUrl: './mobile-menu.component.html',
  styleUrls: ['./mobile-menu.component.scss'],
})
export class MobileMenuComponent {
  @Input() Links: { name: string; link: string; current: boolean }[];
  constructor() {
    this.Links = [
      { name: 'Articles', link: '/articles', current: true },
      { name: 'Thèmes', link: '/themes', current: false },
    ];
  }

  public closeMenu(): void {
    console.log('closeMenu');
  }
}
