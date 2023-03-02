import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  rooms : any ; 
  roomForm : FormGroup ; 

  bikeform: FormGroup;
  validMessage: string = "";

  constructor(private roomService :RoomService) { }

  ngOnInit() {

 
   
  
    this.roomService.getRooms().subscribe(
      data => {
        
        this.rooms = data ; 
        return true;
      },
      error => {
        return Observable.throw(error);
      }
    )

    this.roomForm = new FormGroup({

      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),

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
          return Observable.throw(error);
        }
      )
    } else {
      this.validMessage = "Please fill out the form before submitting!";
    }
  }

}
