import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  messages : any = []
//   messages : any = [{

//     id : 1 , 
//     type : "text" , 
//     from : "ahmed", 
//     to : "amr", 
//     content : "example content"  , 
//     created_at : new Date().toDateString()

//   },
//   {

//     id : 1 , 
//     type : "text" , 
//     from : "amr", 
//     to : "ahmed", 
//     content : "example content 2"  , 
//     created_at : new Date().toDateString()

//   }
// ]
  room : any ; 
  roomForm : FormGroup ; 

  room_id  ;

  join_room  = true ; 
  validMessage: string = "";

  constructor(private roomService :RoomService  ,private route: ActivatedRoute, private router: Router  , private http : HttpClient) { }

  ngOnInit() {
    // this.roomForm = new FormGroup({

    //   name: new FormControl('', Validators.required),
    //   description: new FormControl('', Validators.required),

    // })

     this.room_id = this.route.snapshot.paramMap.get('id')
    if ( !this.room_id ) { this.router.navigate(['/']) }
    this.roomService.getRoom(this.room_id  as number).subscribe(
      data => {
        
        this.http.get('/server/api/msgs/' + this.room_id,
        // {headers: new HttpHeaders().set('Authorization', 'Bearer ' + token)}
      ).subscribe(
        (msgs : any[]) => {
          msgs.forEach(msg => {
             let new_data_format  = new Date(msg.created_at).toDateString() ; 

             msg.created_at = new_data_format.substring(0, new_data_format.length - 5);   
      
               
          });
            this.messages = msgs ; 


        });

        this.room = data ; 
        return true;
      },
      error => {
        return Observable.throw(error);
      }
    )


  }

  submitRegistration() {

    if (this.roomForm.valid) {
      this.validMessage = "Your Room has been craeted. Invite & Talk!";
      console.log(this.roomForm.value)
      this.roomService.createRoom(this.roomForm.value).subscribe(
        data => {
          
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

  joinRoom(){
    let user_id =   localStorage.getItem('user_id');
    let room_id = this.route.snapshot.paramMap.get('id') ; 
    console.log("AHMED" , {"member_id" : user_id ,room_id})
    let body= JSON.stringify({"member_id" : user_id ,room_id})

    return this.http.post('/server/api/users/joinRoom', body , httpOptions).subscribe(
      data => {
        
        console.log("BOYA" , data)
        this.join_room = false 
        return true;
      },
      error => {
        return Observable.throw(error);
      }
    );

  }

  sendMessage(message){
    let msg = {content : message.value , roomId : this.room_id , type : "text" ,  from_user :localStorage.getItem("user_id") , created_at : new Date().toISOString()}
    let body = JSON.stringify(msg)

    return this.http.post('/server/api/msgs', body , httpOptions).subscribe(
      (data : any) => {
        
        console.log("BOYA" , data)
        let new_data_format  = new Date(data.created_at).toDateString() ; 

        data.created_at = new_data_format.substring(0, new_data_format.length - 5);   

        this.messages.push(data)
        return true;
      },
      error => {
        return Observable.throw(error);
      }
    );

  }

}
