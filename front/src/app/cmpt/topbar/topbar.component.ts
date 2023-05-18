import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss'],
})
export class TopbarComponent {
  @Input() Mode: boolean;
  //@ts-ignore
  @Output() parentMethodEvent: EventEmitter<any> = new EventEmitter();
  @Input() LogoSrc: string;
  @Input() AvatarSrc: string;
  @Input() Links: { name: string; link: string; current: boolean }[];
  constructor() {
    this.Mode = false;
    this.LogoSrc = 'assets/img/logo.png';
    this.AvatarSrc = 'assets/img/avatar.png';
    this.Links = [
      { name: 'Articles', link: '/articles', current: true },
      { name: 'Thèmes', link: '/themes', current: false },
    ];
  }

  public ngOnChanges(): void {
    console.log('ngOnChanges');
    console.log('Mode', this.Mode);
  }

  public toggleMenu(): void {
    this.parentMethodEvent.emit();
  }
}
