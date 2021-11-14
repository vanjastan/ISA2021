import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-clienthome',
  templateUrl: './clienthome.component.html',
  styleUrls: ['./clienthome.component.css']
})
export class ClientHomeComponent implements OnInit {

  categories = [];

  constructor(private usersService: UserService) { }

  ngOnInit() {

  }
}