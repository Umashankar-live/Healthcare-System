import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointmentModel } from '../../models/appointment.model';
import { AppointmentService } from '../../service/appointment.service';


@Component({
  selector: 'app-list-appointments',
  templateUrl: './list-appointments.component.html',
  styleUrls: ['./list-appointments.component.css']
})
export class ListAppointmentsComponent implements OnInit {

  appointment: AppointmentModel[] = [];
  tempAppointment: AppointmentModel[] = [];
  appointmentBean: AppointmentModel = new AppointmentModel();

  bucketSize: number = 5
  page: number = 1


  //Flags required for interactive UI
  isAdded: boolean = null
  isUpdated: boolean = false
  isLoaded: boolean = false
  isErrorUpdating: boolean = false

  sortedById: boolean = null
  sortedByName: boolean = null
  sortedByDes: boolean = null
  isDeleteError: boolean = false
  sortedByAppointmentId: boolean = false;
  pendingAppoints: any[] = [];
  allAppoints: any[] = [];
  tempAppoints: AppointmentModel[];

  constructor(private route: Router, private service: AppointmentService) {

  }

  ngOnInit(): void {

    this.service.fetchAllAppointment().subscribe(
      res => {
        this.isLoaded = true
        this.appointment = res
        this.tempAppointment = res
        this.sortByAppointmentId();
      }
    )
  }

  //Sort by allocation id
  sortByAppointmentId() {
    this.appointment.sort(this.sortByProperty('appointmentId'))
    this.sortedByAppointmentId = true
  }

  //Function to sort property of an array
  sortByProperty(property) {
    return function (a, b) {
      if (a[property] > b[property])
        return 1;
      else if (a[property] < b[property])
        return -1;

      return 0;
    }
  }


  viewPending() {
    this.appointment = this.tempAppointment.filter(appointmentBean => appointmentBean.status == 'pending')
  }

  onKeyUpAll(event: any) {
    this.appointment = this.appointment.filter(appointmentBean => appointmentBean.userName.includes(event.target.value))
    if (event.target.value == '' || event.target.value == undefined) {
      this.appointment = this.tempAppointment
    }
  }

  viewApproved() {
    this.appointment = this.tempAppointment.filter(appointmentBean => appointmentBean.status == 'approved')
  }

  viewRejected() {
    this.appointment = this.tempAppointment.filter(appointmentBean => appointmentBean.status == 'rejected')
  }


  logout() {
    sessionStorage.clear();
    this.route.navigate(['login']);
  }
}
