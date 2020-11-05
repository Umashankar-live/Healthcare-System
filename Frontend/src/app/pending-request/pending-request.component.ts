import { Component, OnInit } from '@angular/core';
import { s } from '@angular/core/src/render3';
import { Router } from '@angular/router';
import { AppointmentModel } from '../../models/appointment.model';
import { AppointmentService } from '../../service/appointment.service';

@Component({
  selector: 'app-pending-request',
  templateUrl: './pending-request.component.html',
  styleUrls: ['./pending-request.component.css']
})
export class PendingRequestComponent implements OnInit {

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
  pendingAppoints: AppointmentModel[] = [];
  isStatusChanging: boolean = false;

  constructor(private route: Router, private service: AppointmentService) { }


  ngOnInit(): void {

    this.service.fetchAllAppointment().subscribe(
      res => {
        this.isLoaded = true
        this.appointment = res
        this.pendingAppoints = this.appointment.filter(appointmentBean => appointmentBean.status == 'pending')
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

  onKeyUp(event: any) {

    this.pendingAppoints = this.pendingAppoints.filter(appointmentBean => appointmentBean.userName.includes(event.target.value))
    if (event.target.value == '' || event.target.value == undefined) {
      this.pendingAppoints = this.appointment.filter(appointmentBean => appointmentBean.status == 'pending')
    }
  }


  changeStatus(appointmentId: number, status: string) {
    this.isStatusChanging = true

    if (status == "approved") {
      this.service.approvedStatus(appointmentId).subscribe(
        res => {
          if (res == null)
            alert('Error occured');
          else {
            alert('Appointment is Approved.. !!')
            this.ngOnInit()
          }
        }
      )
    }

    else if (status == "rejected") {
      this.service.rejectedStatus(appointmentId).subscribe(
        res => {
          if (res == null)
            alert('Error occured');
          else {
            alert('Appointment is Rejected.. !!')
            this.ngOnInit()
          }
        }
      )

    }
  }






}
