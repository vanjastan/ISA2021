import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CancelRequest } from 'src/app/models/cancelrequest';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cancelrequestlist',
  templateUrl: './cancelrequestlist.component.html',
  styleUrls: ['./cancelrequestlist.component.css']
})
export class CancelRequestListComponent implements OnInit {

  categories = [];
  cancelRequest = new CancelRequest(null, null, null);

  constructor(private service: UserService, private http: HttpClient) { }

  ngOnInit(): void {
    this.getAllRequests();
  }

  getAllRequests(){
    this.service.getAllCancelRequests().subscribe(data => {
      this.categories = data;
    },
    error => {
      alert('Unable to get cancel requests');
    });
  }

  onSelect(selectedItem: any, accepted: boolean) {
    this.cancelRequest.id = selectedItem.id;
    this.cancelRequest.user_id = selectedItem.userId;
    this.cancelRequest.text = selectedItem.text;
    this.cancelRequest.accepted = accepted;
    console.log(this.cancelRequest);
    this.service.answerRequest(this.cancelRequest).subscribe(data => {
      alert('Request answered')
      location.reload();
    }, error => {
      alert('Error');
    });
  }
}