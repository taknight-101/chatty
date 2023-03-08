// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck

import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  rooms : any ; 
  roomForm : FormGroup ; 

 
  validMessage: string = "";

  constructor(private roomService :RoomService , private auth :AuthService , private http:HttpClient) { }

  ngOnInit() {

 
   
  
    this.roomService.getRooms().subscribe(
      data => {
        
        this.rooms = data ; 
        return true;
      },
      error => {
        // return Observable.throw(error);
      }
    )

    this.roomForm = new FormGroup({

      name: new FormControl('', Validators.required),
      description: new FormControl('' , null),

    })

    // this.bikeform = new FormGroup({
    //   name: new FormControl('', Validators.required),
    //   email: new FormControl('', Validators.required),
    //   phone: new FormControl('', Validators.required),
    //   model: new FormControl('', Validators.required),
    //   serialNumber: new FormControl('', Validators.required),
    //   purchasePrice: new FormControl('', Validators.required),
    //   purchaseDate: new FormControl('', Validators.required),
    //   contact: new FormControl()
    // });
  }

  submitRegistration() {
    console.log(this.roomForm)
    if (this.roomForm.valid) {
      this.validMessage = "Your Room has been craeted. Invite & Talk!";
      console.log(this.roomForm.value)
      this.roomService.createRoom(this.roomForm.value).subscribe(
        data => {
          this.rooms.push(data) ; 
          this.roomForm.reset();
          return true;
        },
        error => {
          // return Observable.throw(error);
        }
      )
    } else {
      this.validMessage = "Please fill out the form before submitting!";
    }
  }

  
  deleteRoom(room_id){
 
    const {access_token, user_id} = this.auth.getSession() 
   
    return this.http.get('/server/api/msgs/deleteRoom/' + room_id, {headers: new HttpHeaders().set('Authorization', 'Bearer ' + access_token).set('Content-Type' , 'application/json')} ).subscribe(
      (data : any) => {
        let current_rooms = JSON.parse(localStorage.getItem("chat_rooms_id"))
     
        this.rooms = this.rooms.filter(r => r.id != room_id); 
        localStorage.setItem("chat_rooms_id", JSON.stringify((current_rooms as any[]).filter( r => r != room_id) ))
       
     
        return true;
      },
      error => {
        // return Observable.throw(error);
      }
    );
  }

}
