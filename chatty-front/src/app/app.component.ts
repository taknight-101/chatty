// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck

import { Component, Inject } from '@angular/core';
import { TranslocoService } from '@ngneat/transloco';
import { AuthService } from './services/auth.service';
import { DOCUMENT } from "@angular/common" ; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

})
export class AppComponent {
  title = 'app';

  is_loggedIn= false ; 

  activeLang : string ; 
  availableLangs ; 

  constructor(private authService: AuthService , private translocoService: TranslocoService,@Inject(DOCUMENT) private document: Document) {
    translocoService.getDefaultLang();

    this.is_loggedIn = authService.isAuthenticated()  
  }

  public logout(){
    this.is_loggedIn = false; 
  this.authService.logout()

  }


  
  public onActivate(active_component) {
    // little hack to solve the logout state issue :) 
  // console.log(active_component , !!active_component.loginService  , !!active_component.signUpService ,  !(+active_component.loginService  ^ +active_component.signUpService) )
    let is_login_comp =  !!active_component.loginService
    let is_signUP_comp =  !!active_component.signUpService 
    this.is_loggedIn = !(+is_login_comp ^ +is_signUP_comp) 
      
  }


  ngOnInit() {

    this.availableLangs = this.translocoService.getAvailableLangs() 
   this.activeLang =  this.translocoService.getActiveLang()
  }

  changeLang(lang : Stirng ){
    let htmlTag = this.document.getElementsByTagName("html")[0] as HTMLHtmlElement;
    htmlTag.dir = lang === "ar" ? "rtl" : "ltr";

    this.translocoService.setActiveLang(lang)
    this.activeLang = lang
  }
}


