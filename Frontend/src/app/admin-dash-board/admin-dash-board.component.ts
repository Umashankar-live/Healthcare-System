import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CenterService } from 'src/service/center.service';
import { TestService } from 'src/service/test.service';
import {AppointmentService} from 'src/service/appointment.service';

@Component({
  selector: 'app-admin-dash-board',
  templateUrl: './admin-dash-board.component.html',
  styleUrls: ['./admin-dash-board.component.css']
})
export class AdminDashBoardComponent implements OnInit {

  sidebarClass: string = ""
  menuToggleClass: string = "container1"
  testCount: object = null
  centerCount: object = null
  pendingCount: object = null

  isLoadingCount: boolean = true
  isLoadingPending: boolean = true
  isLoadingCenters: boolean = true
  

  constructor(public router: Router, public testService: TestService,public centerService: CenterService,public appointmentService: AppointmentService) { }

  ngOnInit(): void {

    if (window.atob(sessionStorage.getItem('userType')) !== 'admin')
      this.router.navigate([''], {
        queryParams: {
          redirect: true
        }
      })

    this.testService.countTest().subscribe(
      res => {
        this.testCount = res
        this.isLoadingCount = false
      }
    )

    this.centerService.countCenter().subscribe(
      res => {
        this.centerCount = res
        this.isLoadingCenters = false
      }
    )

    this.appointmentService.countPending().subscribe(
      res => {
        this.pendingCount = res
        this.isLoadingPending = false
      }
    )

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

  adminRefresh(){
    this.ngOnInit();
    this.router.navigate(["/admin/dashboard"]);
  }

}
