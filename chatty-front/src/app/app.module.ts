import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { RoomService } from './services/room.service';
import { HomeComponent } from './components/home/home.component';
import { AuthService } from './services/auth.service';
import { AuthGuard } from './services/auth.guard';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { RoomComponent } from './components/room/room.component';
import { IsMemberGuard } from './services/is_room_member.guard';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';
import { TranslocoRootModule } from './transloco-root.module';


@NgModule({
  declarations: [
    AppComponent,
 
    HomeComponent,
    LoginComponent,
    SignupComponent,
    RoomComponent
    

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule, 
    ReactiveFormsModule, 
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(), // ToastrModule added
    TranslocoRootModule

  ],
  providers: [RoomService, AuthService, AuthGuard , IsMemberGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
