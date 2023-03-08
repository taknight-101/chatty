// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck

import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable()
export class AuthService {



  constructor(public router: Router , private http:HttpClient) {}

  public login(user) {
    let body = JSON.stringify(user);
    return this.http.post('/server/api/users/login', body , {headers: new HttpHeaders().set('Content-Type' ,  'application/json')});

  }

  public signUp(user)  {


    let body = JSON.stringify(user);
    return this.http.post('/server/api/users/signup', body , {headers: new HttpHeaders().set('Content-Type' ,  'application/json')});

  }
  



  public setSession(authResult): void {
    localStorage.setItem('access_token', authResult.authToken);
    localStorage.setItem('tokenType', authResult.tokenType);
    localStorage.setItem('user_id', authResult.user_id);
    localStorage.setItem('chat_rooms_id', JSON.stringify(authResult.chat_rooms_id));
  

  }
  public getSession() {
    return {
      "access_token" : localStorage.getItem("access_token") , 
      "tokenType" :  localStorage.getItem("tokenType"),
      "user_id" : localStorage.getItem('user_id') , 
      "chat_rooms_id" : localStorage.getItem('chat_rooms_id')


  }
  
  } 
    
 
  
  


  public logout(): void {
    localStorage.clear();
    this.router.navigate(['/']);
  }

  public isAuthenticated(): boolean {
  
    const {access_token } = this.getSession() ; 

    return !!access_token 

   
      // Check whether the current time is past the
    // access token's expiry time
    // const expiresAt = JSON.parse(localStorage.getItem('expires_at'));
    // return access_token && (new Date().getTime() < expiresAt )
   
  }

}
