import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { USER_ID_KEY } from 'src/app/config/local-storage-keys';
import { CancelRequest } from 'src/app/models/cancelrequest';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cancelrequest',
  templateUrl: './cancelrequest.component.html',
  styleUrls: ['./cancelrequest.component.css']
})
export class CancelRequestComponent implements OnInit {

  constructor(private cancelRequestService: UserService) { }

  cancelRequest = new CancelRequest(parseInt(USER_ID_KEY),null,false);

  ngOnInit(): void {
  }

  onSubmitCancelRequest(){ 
    this.cancelRequestService.cancelRequest(this.cancelRequest).subscribe(data => {
      alert('Request sent')
    }, error => {
      alert('Error');
    });
  }
}