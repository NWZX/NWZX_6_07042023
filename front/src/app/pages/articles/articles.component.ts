import { Component, Input } from '@angular/core';
import { IArticle } from 'src/app/interfaces/iarticle';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss'],
})
export class ArticlesComponent {
  @Input() articles: IArticle[];
  constructor() {
    this.articles = [
      {
        id: 1,
        theme: {
          id: 1,
          title: 'Angular',
        },
        user: {
          id: 1,
          username: 'admin',
        },
        title: 'Angular',
        content:
          "Content: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled...",
        created_at: '2021-01-01 00:00:00',
        updated_at: '2021-01-01 00:00:00',
      },
      {
        id: 2,
        theme: {
          id: 2,
          title: 'React',
        },
        user: {
          id: 2,
          username: 'admin',
        },
        title: 'React',
        content:
          "Content: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled...",
        created_at: '2021-01-01 00:00:00',
        updated_at: '2021-01-01 00:00:00',
      },
      {
        id: 3,
        theme: {
          id: 3,
          title: 'Vue',
        },
        user: {
          id: 3,
          username: 'admin',
        },
        title: 'Vue',
        content:
          "Content: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled...",
        created_at: '2021-01-01 00:00:00',
        updated_at: '2021-01-01 00:00:00',
      },
    ];
  }
}
