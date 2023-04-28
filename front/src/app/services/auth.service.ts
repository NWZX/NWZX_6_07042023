import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ILoginRequest } from 'src/app/interfaces/iloginrequest';
import { IRegisterRequest } from 'src/app/interfaces/iregisterrequest';
import { ISession } from 'src/app/interfaces/isession';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private pathService = 'api/auth';

  constructor(private httpClient: HttpClient) {}

  public register(registerRequest: IRegisterRequest): Observable<void> {
    return this.httpClient.post<void>(
      `${this.pathService}/register`,
      registerRequest
    );
  }

  public login(loginRequest: ILoginRequest): Observable<ISession> {
    return this.httpClient.post<ISession>(
      `${this.pathService}/login`,
      loginRequest
    );
  }

  public getPathService(): string {
    return this.pathService;
  }
}
