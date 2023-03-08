// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck

import {ActivatedRoute, CanActivate, Router} from "@angular/router";
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable()
export class IsMemberGuard implements CanActivate {
  constructor(private authService: AuthService , private router : Router , private route : ActivatedRoute) {
  }

  canActivate() {
    
  let {chat_rooms_id } = this.authService.getSession() 
  console.log("omg man" , (JSON.parse(chat_rooms_id) as any[]).includes(+this.route.snapshot.paramMap.get('id')))
  return (JSON.parse(chat_rooms_id) as any[]).includes(+this.route.snapshot.paramMap.get('id'))


  }

}




  
