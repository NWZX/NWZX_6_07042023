import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { IComment } from '../interfaces/icomment';

@Injectable({
  providedIn: 'root',
})
export class CommentsService {
  private pathService = 'api/comments';

  constructor(private httpClient: HttpClient) { }
  
  public getComments(): Observable<IComment[]> {
    return this.httpClient.get<IComment[]>(`${this.pathService}`);
  }

  public getCommentsByArticle(articleId: number): Observable<IComment[]> {
    return this.httpClient.get<IComment[]>(`${this.pathService}/article/${articleId}`);
  }

  public getComment(id: number): Observable<IComment> {
    return this.httpClient.get<IComment>(`${this.pathService}/${id}`);
  }

  public createComment(comment: Partial<IComment>): Observable<IComment> {
    return this.httpClient.post<IComment>(`${this.pathService}`, comment);
  }

  public updateComment(comment: IComment): Observable<IComment> {
    return this.httpClient.put<IComment>(`${this.pathService}`, comment);
  }

  public deleteComment(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.pathService}/${id}`);
  }
}
