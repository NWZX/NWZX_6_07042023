import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { IArticle } from 'src/app/interfaces/iarticle';
import { ArticlesService } from 'src/app/services/articles.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss'],
})
export class ArticlesComponent {
  public articles$: Observable<IArticle[]>;

  constructor(
    private articlesService: ArticlesService,
    private router: Router
  ) {
    this.articles$ = this.articlesService.getArticles();
  }

  public sort(): void {
    
  }

  public goToArticleNew(): void {
    this.router.navigate(['/articles/new']);
  }
}
