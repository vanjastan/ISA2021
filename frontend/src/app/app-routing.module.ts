import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientHomeComponent } from './components/clienthome/clienthome.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { LOGIN_PATH, REGISTRATION_PATH, CLIENT_HOME_PATH, LOGIN_REG } from './config/router-paths';

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
    path: LOGIN_REG,
    component: SignupComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
