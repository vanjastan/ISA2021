import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { HOME_PATH } from 'src/app/config/router-paths';
import UserRegistrationDTO from 'src/app/models/user-registration-dto.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  registrationForm: FormGroup;

  constructor(private userService: UserService,
              private authService: AuthService,
              private fb: FormBuilder,
              private router: Router) { }

  ngOnInit(): void {
    if (this.authService.isUserLoggedIn()) {
      console.log('Please logout if you want to create a new account.', 'Warning');
      this.router.navigate([HOME_PATH]);
    }

    this.createForm();
  }

  private createForm(): void {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      address: ['', Validators.required],
      phone: ['', Validators.required],
      city: ['', Validators.required],
      country: ['', Validators.required],
    });
  }

  onRegisterSubmit(): void {
    const password = this.registrationForm.controls.password.value;

    const userInfo: UserRegistrationDTO = {
      password: password,
      username: this.registrationForm.controls.username.value,
      email: this.registrationForm.controls.email.value,
      name: this.registrationForm.controls.name.value,
      surname: this.registrationForm.controls.surname.value,
      address: this.registrationForm.controls.address.value,
      phone: this.registrationForm.controls.phone.value,
      city: this.registrationForm.controls.city.value,
      country: this.registrationForm.controls.country.value,
    };

    this.userService.register(userInfo).subscribe(data => {
      console.log(userInfo);
      console.log('You account has been created, go to your email to verify it.');
    }, error => {
      console.log(userInfo);
      console.log('There was an error while adding your account. Try again later.');
    });
  }

}
