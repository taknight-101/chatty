// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class RoomService { 
  constructor(private http:HttpClient) { }

  getRooms() {
    let token = localStorage.getItem('access_token');
    return this.http.get('/server/api/rooms',
      {headers: new HttpHeaders().set('Authorization', 'Bearer ' + token)}
    );
  }

  getRoom(id: number) {
    let token = localStorage.getItem('access_token');
    return this.http.get('/server/api/rooms/' + id,
      {headers: new HttpHeaders().set('Authorization', 'Bearer ' + token)}
    );
  }

  createRoom(room) {
    let body = JSON.stringify(room);
    let token = localStorage.getItem('access_token');

    return this.http.post('/server/api/rooms', body,  {headers: new HttpHeaders().set('Authorization', 'Bearer ' + token).set( 'Content-Type','application/json') });
  }

}
