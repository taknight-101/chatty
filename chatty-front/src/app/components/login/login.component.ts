// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck

import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm : FormGroup ; 

  validMessage: string = "";

  localesList = [
    { code: 'en-US', label: 'English' },
    { code: 'ar', label: 'Arabic' }
  ]


  constructor(private loginService :AuthService , private router : Router , private toastr: ToastrService  ) { }

  ngOnInit() {
    this.loginForm = new FormGroup({

      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),

    })


  }

  submitLogin() {
   
    if (this.loginForm.valid) {
      this.validMessage = "Your Room has been craeted. Invite & Talk!";
      console.log(this.loginForm.value)
      this.loginService.login(this.loginForm.value).subscribe(
        data => {
     
          
          this.loginService.setSession(data)
          this.loginForm.reset();
          this.router.navigate(['/rooms']);

          return true;
        },
        error => {
    
          this.toastr.error(error.error.message, 'Server Error');
        }
      )
    } else {
      this.validMessage = "Please fill out the form before submitting!";
    }
  }

  

  // toastMe(){

  //     this.toastr.error('Hello world!', 'Toastr fun!');
    
   
  // }
  
}

