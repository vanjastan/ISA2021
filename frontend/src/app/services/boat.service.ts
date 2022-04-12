import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Boats } from '../models/boat';

@Injectable({
    providedIn: 'root'
  })
  export class BoatsService {
  
    constructor(private http: HttpClient) { 
        this.getAllBoats();
    }

    getAllBoats(): Observable<any>{
        return this.http.get(`http://localhost:8080/api/boats/all`);
    }

  }