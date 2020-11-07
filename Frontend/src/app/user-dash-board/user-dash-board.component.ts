import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AppointmentService} from 'src/service/appointment.service'

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
  userId : string
  isAppointmentExisit: boolean =true;


  

  constructor(private router: Router , private service: AppointmentService) { }

  ngOnInit():void {
    
    if (window.atob(sessionStorage.getItem('userType')) !== 'user')
    this.router.navigate([''], {
      queryParams: {
        redirect: true
      }
    })

  }

  getPersonalDetail(){
    
    this.userId = sessionStorage.getItem('custId')
    console.log(this.userId);
    this.router.navigate(['/user/dashboard/PersonalDetails/',this.userId]);
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


  checkAppointmentexist(){
    
    this.service.checkUserAppointment(sessionStorage.getItem('custId')).subscribe(
      data => {
        if (data == null)
        this.isAppointmentExisit = true ;
        else
        this.isAppointmentExisit= false ;
      }
    )

    
  }

}
