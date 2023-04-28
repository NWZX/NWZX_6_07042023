import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss'],
})
export class TopbarComponent {
  @Input() Mode: 'Logo' | 'LogoMenuAvatar';
  @Input() LogoSrc: string;
  @Input() AvatarSrc: string;
  @Input() Links: { name: string; link: string; current: boolean }[];
  constructor() {
    this.Mode = 'Logo';
    this.LogoSrc = 'assets/img/logo.png';
    this.AvatarSrc = 'assets/img/avatar.png';
    this.Links = [
      { name: 'Articles', link: '/articles', current: true },
      { name: 'Thèmes', link: '/themes', current: false },
    ];
  }
}
