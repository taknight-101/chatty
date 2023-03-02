import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import 'rxjs/add/operator/filter';
import * as auth0 from 'auth0-js';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class AuthService {

  auth0 = new auth0.WebAuth({
    clientID: '2g01JwR45gn66Xdoqqcp4NCJTDRD0P2M',
    domain: 'danbunkerps.auth0.com',
    responseType: 'token id_token',
    audience: 'http://localhost:8080',
    redirectUri: 'http://localhost:4200/callback',
    scope: 'openid view:registration view:registrations'
  });




  constructor(public router: Router , private http:HttpClient) {}

  public login(user) {
    let body = JSON.stringify(user);
    // body = JSON.stringify({id : 1 , username : "angular guy" , password : " fuck hell yeah" })
    return this.http.post('/server/api/users/login', body , {headers: new HttpHeaders().set('Content-Type' ,  'application/json')});

  }

  public signUp(user)  {


    let body = JSON.stringify(user);
    // body = JSON.stringify({id : 1 , username : "angular guy" , password : " fuck hell yeah" })
    return this.http.post('/server/api/users/signup', body , {headers: new HttpHeaders().set('Content-Type' ,  'application/json')});

  }
  

  public handleAuthentication(): void {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        window.location.hash = '';
        this.setSession(authResult);
        this.router.navigate(['/admin']);
      } else if (err) {
        this.router.navigate(['/admin']);
        console.log(err);
      }
    });
  }

  public setSession(authResult): void {
    localStorage.setItem('access_token', authResult.authToken);
    localStorage.setItem('tokenType', authResult.tokenType);
    localStorage.setItem('user_id', authResult.user_id);
 
  

  }

  public logout(): void {
    // Remove tokens and expiry time from localStorage
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    // Go back to the home route
    this.router.navigate(['/']);
  }

  public isAuthenticated(): boolean {
    // Check whether the current time is past the
    // access token's expiry time
    const expiresAt = JSON.parse(localStorage.getItem('expires_at'));
    return new Date().getTime() < expiresAt;
  }

}
