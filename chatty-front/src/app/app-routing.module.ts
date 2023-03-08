import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

import { AuthGuard } from './services/auth.guard';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { RoomComponent } from './components/room/room.component';
import { IsMemberGuard } from './services/is_room_member.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login', pathMatch: 'full'
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'rooms',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },

  {
     path: 'rooms/:id',
     component: RoomComponent,
     canActivate: [AuthGuard ]
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
