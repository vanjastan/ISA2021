import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Boats } from 'src/app/models/boat';
import { BoatsService } from 'src/app/services/boat.service';
import {Sort} from '@angular/material/sort';

@Component({
  selector: 'app-listofboats',
  templateUrl: './listofboats.component.html',
  styleUrls: ['./listofboats.component.css']
})
export class ListOfBoatsComponent implements OnInit{

  categories = [];
  sortedBoats = [];

  constructor(private service: BoatsService, private http: HttpClient) { }

  ngOnInit() {
    this.getAllBoats();
  }

  getAllBoats(){
    this.service.getAllBoats().subscribe(data => {
      this.categories = data;
      this.sortedBoats = data;
    },
    error => {
      alert('Unable to get boats');
    });
  }

  sortData(sort: Sort) {
    const data = this.categories;
    if (!sort.active || sort.direction === '') {
      this.sortedBoats = data;
      return;
    }

    this.sortedBoats = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return this.compare(a.name, b.name, isAsc);
        case 'address':
          return this.compare(a.address, b.address, isAsc);
        case 'grade':
          return this.compare(a.grade, b.grade, isAsc);
        case 'price':
          return this.compare(a.price, b.price, isAsc);
        default:
          return 0;
      }
    });
  }

  compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

}