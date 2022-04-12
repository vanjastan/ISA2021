import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './components/adminhome/adminhome.component';
import { CancelRequestComponent } from './components/cancelrequest/cancelrequest.component';
import { CancelRequestListComponent } from './components/cancelrequestlist/cancelrequestlist.component';
import { ClientHomeComponent } from './components/clienthome/clienthome.component';
import { ClientProfileComponent } from './components/clientprofilepage/clientprofilepage.component';
import { ListOfBoatsComponent } from './components/listofboats/listofboats.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { LOGIN_PATH, REGISTRATION_PATH, CLIENT_HOME_PATH, ADMIN_HOME_PATH, LOGIN_REG, CLIENT_PROFILE, LIST_OF_BOATS_PATH, CANCEL_REQUEST,
CANCEL_REQUEST_LIST } from './config/router-paths';

const routes: Routes = [
  {
    path: LOGIN_PATH,
    component: LoginComponent
  },
  {
    path: REGISTRATION_PATH,
    component: SignupComponent
  },
  {
    path: CLIENT_HOME_PATH,
    component: ClientHomeComponent
  },
  {
    path: ADMIN_HOME_PATH,
    component: AdminHomeComponent
  },
  {
    path: LOGIN_REG,
    component: SignupComponent
  },
  {
    path: CLIENT_PROFILE,
    component: ClientProfileComponent
  },
  {
    path: LIST_OF_BOATS_PATH,
    component: ListOfBoatsComponent
  },
  {
    path: CANCEL_REQUEST,
    component: CancelRequestComponent
  },
  {
    path: CANCEL_REQUEST_LIST,
    component: CancelRequestListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
