import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {UserService} from 'src/app/services/user.service';

@Injectable()
export class LoginGuard implements CanActivate {

  constructor(private router: Router, private userService: UserService) {
  }

  canActivate(): boolean {
    if (this.userService.getUserInfo) {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }
}
