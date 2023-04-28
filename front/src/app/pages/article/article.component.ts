import { Component } from '@angular/core';
import { IArticle } from 'src/app/interfaces/iarticle';
import { IComment } from 'src/app/interfaces/icomment';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent {
  article: IArticle | undefined;
  comments: IComment[];

  constructor() {
    this.article = {
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
    };
    this.comments = [
      {
        id: 1,
        user: {
          id: 1,
          username: 'admin',
        },
        content: 'Comment 1',
        created_at: '2021-01-01 00:00:00',
        updated_at: '2021-01-01 00:00:00',
      },
      {
        id: 2,
        user: {
          id: 1,
          username: 'admin',
        },
        content: 'Comment 2',
        created_at: '2021-01-01 00:00:00',
        updated_at: '2021-01-01 00:00:00',
      }
    ];
  }
}
