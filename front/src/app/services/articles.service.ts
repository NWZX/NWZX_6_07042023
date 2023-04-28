import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IArticle } from '../interfaces/iarticle';

@Injectable({
  providedIn: 'root',
})
export class ArticlesService {
  private pathService = 'api/articles';

  constructor(private httpClient: HttpClient) { }
  
  public getArticles(): Observable<IArticle[]> {
    return this.httpClient.get<IArticle[]>(`${this.pathService}`);
  }

  public getArticle(id: number): Observable<IArticle> {
    return this.httpClient.get<IArticle>(`${this.pathService}/${id}`);
  }

  public createArticle(article: IArticle): Observable<IArticle> {
    return this.httpClient.post<IArticle>(`${this.pathService}`, article);
  }

  public updateArticle(article: IArticle): Observable<IArticle> {
    return this.httpClient.put<IArticle>(`${this.pathService}`, article);
  }

  public deleteArticle(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.pathService}/${id}`);
  }
}
