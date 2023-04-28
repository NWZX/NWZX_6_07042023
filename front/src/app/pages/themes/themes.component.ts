import { Component, Input } from '@angular/core';
import { ITheme } from 'src/app/interfaces/itheme';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent {
  @Input() themes: ITheme[];
  constructor() {
    this.themes = [
      {
        id: 1,
        title: 'Angular',
        description:
          'Angular is a TypeScript-based open-source web application framework led by the Angular Team at Google and by a community of individuals and corporations.',
        created_at: '2021-01-01 00:00:00',
        updated_at: '2021-01-01 00:00:00',
      },
      {
        id: 2,
        title: 'React',
        description:
          'React is an open-source, front end, JavaScript library for building user interfaces or UI components.',
        created_at: '2021-01-01 00:00:00',
        updated_at: '2021-01-01 00:00:00',
      },
      {
        id: 3,
        title: 'Vue',
        description:
          'Vue.js is an open-source model–view–viewmodel front end JavaScript framework for building user interfaces and single-page applications.',
        created_at: '2021-01-01 00:00:00',
        updated_at: '2021-01-01 00:00:00',
      },
    ];
  }
}
