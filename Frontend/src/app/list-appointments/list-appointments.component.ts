import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-appointments',
  templateUrl: './list-appointments.component.html',
  styleUrls: ['./list-appointments.component.css']
})
export class ListAppointmentsComponent implements OnInit {

  constructor(private route : Router) { }

  ngOnInit() {
  }

  logout(){
    sessionStorage.clear();
    this.route.navigate(['login']);
  }

}
