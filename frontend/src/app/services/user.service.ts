import { VERIFY_ACC_URL, REGISTER_URL } from './../config/api-paths';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import UserRegistrationDTO from '../models/user-registration-dto.model';
import { USER_ID_KEY } from '../config/local-storage-keys';
import { User } from '../models/user';
import { CancelRequest } from '../models/cancelrequest';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  constructor(private http: HttpClient) { 
  }

  verifyAccount(token: string): Observable<any> {
    return this.http.get(`${VERIFY_ACC_URL}/${token}`);
  }

  register(user: UserRegistrationDTO): Observable<any> {
    return this.http.post(REGISTER_URL, user);
  }

  getUser(userId: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/users/logged/${userId}`);
  }
  
  editUser(user: User) : Observable<User> {
    return  this.http.put<User>(`http://localhost:8080/api/users/edit`, user);
  }

  public getUserInfo(): Observable<any> {
    const userId = localStorage.getItem(USER_ID_KEY);
    return this.http.get(`http://localhost:8080/api/users/logged/${userId}`);
  }

  cancelRequest(cancelRequest: CancelRequest): Observable<any> {
    const userId = localStorage.getItem(USER_ID_KEY);
    return this.http.post<CancelRequest>(`http://localhost:8080/api/users/${userId}/cancelRequest`, cancelRequest);
  }

  getAllCancelRequests(): Observable<any>{
    return this.http.get(`http://localhost:8080/api/users/allCancelRequests`);
  }

  answerRequest(cancelRequest: CancelRequest): Observable<any>{
    return this.http.put(`http://localhost:8080/api/users/answerRequest`, cancelRequest);
  }
}
