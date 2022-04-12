import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Client } from 'src/app/models/client';
import { USER_ID_KEY } from 'src/app/config/local-storage-keys';

@Component({
  selector: 'clientprofilepage',
  templateUrl: './clientprofilepage.component.html',
  styleUrls: ['./clientprofilepage.component.css']
})
export class ClientProfileComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  categories = [];
  user: Client = new Client(1,"","","","","","","","","");
  form: FormGroup;
  constructor(private userService: UserService, private router: Router, private formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit() {
    this.getUserInfo();
    this.form = this.formBuilder.group({
      oldPassword: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      newPassword: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      username: String
    });
  }

  private getUserInfo(): void {
    this.userService.getUserInfo().subscribe(data => {
      this.user = data;
    }, error => {
      console.log("Error in getting user data!")
    });
  }

  onClickSave(){
    this.userService.editUser(this.user)
    .subscribe(
      data=> {
        alert('Request has been sent!');
          console.log('Updated!', JSON.stringify(data));
        },
        error=> console.error('Error updating!',error)
    ) 
  }

  onClickCancel(){
      this.router.navigate(['/client']);
  }
}