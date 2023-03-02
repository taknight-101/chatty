import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm : FormGroup ; 

  validMessage: string = "";

  constructor(private loginService :AuthService , private router : Router) { }

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
          return Observable.throw(error);
        }
      )
    } else {
      this.validMessage = "Please fill out the form before submitting!";
    }
  }

}
