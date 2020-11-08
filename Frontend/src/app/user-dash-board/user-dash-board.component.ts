import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointmentModel } from 'src/models/appointment.model';
import { AppointmentService } from 'src/service/appointment.service'

@Component({
  selector: 'app-user-dash-board',
  templateUrl: './user-dash-board.component.html',
  styleUrls: ['./user-dash-board.component.css']
})
export class UserDashBoardComponent implements OnInit {

  sidebarClass: string = ""
  menuToggleClass: string = "container1"
  testCount: object = null
  centerCount: object = null
  pendingCount: object = null

  isLoadingCount: boolean = true
  isLoadingPending: boolean = true
  isLoadingCenters: boolean = true
  userId: string
  name: string
  isAppointmentExisit: boolean = true;
  appointment: AppointmentModel;



  constructor(private router: Router, private service: AppointmentService) {
    this.appointment = new AppointmentModel();
  }

  ngOnInit(): void {

    if (window.atob(sessionStorage.getItem('userType')) !== 'user')
      this.router.navigate([''], {
        queryParams: {
          redirect: true
        }
      })
    this.name = sessionStorage.getItem('uName');
    console.log("Name = "+this.name);
    this.service.checkUserAppointment(sessionStorage.getItem('custId')).subscribe(
      data => {
        this.appointment = data;
        console.log(data);
        /* if (data == null) {
           console.log(data);
           this.router.navigate(['/user/dashboard/userCenter/view']);
         }
         else {
           alert("You can add only one appointment at a time.....")
           this.router.navigate(['/user/dashboard/viewStatus/', this.appointment.appointmentId]);
         }*/

      }
    )

  }

  getPersonalDetail() {

    this.userId = sessionStorage.getItem('custId')
    console.log(this.userId);
    this.router.navigate(['/user/dashboard/PersonalDetails/', this.userId]);
  }


  logOut() {
    sessionStorage.clear();
    this.router.navigate([''])
  }

  //For toggle
  toggleClass() {
    if (this.sidebarClass == "") {
      this.sidebarClass = "toggled"
      this.menuToggleClass = "container1  clickable"
    }
    else {
      this.sidebarClass = ""
      this.menuToggleClass = "container1 clickable"
    }
  }


  checkAppointmentexist() {

    this.service.checkUserAppointment(sessionStorage.getItem('custId')).subscribe(
      data => {
        this.appointment = data;
        console.log(data);
        if (data == null) {
          console.log(data);
          this.router.navigate(['/user/dashboard/userCenter/view']);
        }
        else {
          alert("You can add only one appointment at a time.....")
          this.router.navigate(['/user/dashboard/viewStatus/', this.appointment.appointmentId]);
        }

      }
    )


  }

  viewStatus() {
    if (this.appointment == null) {
      alert("You Don't have appointment registered yet.. !!!");
      this.router.navigate(['user/dashboard']);
    }
    else
      this.router.navigate(['/user/dashboard/viewStatus/', this.appointment.appointmentId]);
  }

}
