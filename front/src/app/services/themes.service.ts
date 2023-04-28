import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ITheme } from '../interfaces/itheme';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ThemesService {
  private pathService = 'api/themes';

  constructor(private httpClient: HttpClient) { }

  public getThemes(): Observable<ITheme[]> {
    return this.httpClient.get<ITheme[]>(`${this.pathService}`);
  }

  public getTheme(id: number): Observable<ITheme> {
    return this.httpClient.get<ITheme>(`${this.pathService}/${id}`);
  }

  public createTheme(theme: ITheme): Observable<ITheme> {
    return this.httpClient.post<ITheme>(`${this.pathService}`, theme);
  }

  public updateTheme(theme: ITheme): Observable<ITheme> {
    return this.httpClient.put<ITheme>(`${this.pathService}`, theme);
  }

  public deleteTheme(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.pathService}/${id}`);
  }
}
