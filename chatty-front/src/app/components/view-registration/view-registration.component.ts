import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-registration',
  templateUrl: './view-registration.component.html',
  styleUrls: ['./view-registration.component.css']
})
export class ViewRegistrationComponent implements OnInit {

  public bikeReg;

  constructor(private roomService: RoomService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getBikeReg(this.route.snapshot.params.id);
  }

  getBikeReg(id:number) {
    this.roomService.getRoom(id).subscribe(
      data => {
        this.bikeReg = data;
      },
      err => console.error(err),
      () => console.log('bikes loaded')
    );
  }

}
