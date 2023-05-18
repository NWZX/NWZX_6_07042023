import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IUser } from '../interfaces/iuser';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private pathService = 'api/users';

  constructor(private httpClient: HttpClient) { }

  public getUser(): Observable<IUser> {
    return this.httpClient.get<IUser>(`${this.pathService}`);
  }

  public updateUser(user: Partial<IUser>): Observable<IUser> {
    return this.httpClient.put<IUser>(`${this.pathService}`, user);
  }

  public subscribeTheme(themeId: number): Observable<IUser> {
    return this.httpClient.get<IUser>(`${this.pathService}/subscribe/${themeId}`, {});
  }

  public unsubscribeTheme(themeId: number): Observable<IUser> {
    return this.httpClient.get<IUser>(`${this.pathService}/unsubscribe/${themeId}`, {});
  }
}
