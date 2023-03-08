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
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpForm : FormGroup ; 

  bikeform: FormGroup;
  validMessage: string = "";

  constructor(private signUpService :AuthService ,private router : Router , private toastr: ToastrService) { }

  ngOnInit() {
    this.signUpForm = new FormGroup({

      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),

    })

  
  }

  submitSignup() {

    if (this.signUpForm.valid) {
      this.validMessage = "";
      this.signUpService.signUp(this.signUpForm.value).subscribe(
        data => {
          this.signUpService.setSession(data)
          this.signUpForm.reset();
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

}
