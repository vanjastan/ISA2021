import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import LoginDTO from 'src/app/models/login-dto.model';
import { USER_ID_KEY, USER_ROLE_KEY, USERNAME_KEY, USER_TOKEN_KEY } from 'src/app/config/local-storage-keys';
import { ROLE_ADMIN,  ROLE_CLIENT } from 'src/app/config/user-roles-keys';
import { ADMIN_HOME_PATH,  CLIENT_HOME_PATH } from 'src/app/config/router-paths';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginSuccess: boolean = false;
  loginError: boolean = false;
  loginForm: FormGroup;
  toastr: any;

  constructor(private fb: FormBuilder,
              private router: Router,
              private authService: AuthService) {
    this.createForm();
  }

  ngOnInit(): void {
  }

  private createForm(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onLogin(): void {
    const credentials: LoginDTO = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    };

    this.authService.login(credentials).subscribe(
      {next: (data) => {
      localStorage.setItem(USER_ID_KEY, data.id),
      localStorage.setItem(USER_ROLE_KEY, data.authorities[0]),
      localStorage.setItem(USERNAME_KEY, data.username),
      localStorage.setItem(USER_TOKEN_KEY, data.token.accessToken),

      this.loginSuccess = true;
      this.loginError = false;

      switch (data.authorities[0]) {
        case ROLE_ADMIN:  this.router.navigate([ADMIN_HOME_PATH]); break;
        case ROLE_CLIENT: this.router.navigate([CLIENT_HOME_PATH]); break;
      }
    }
  });
}
}
