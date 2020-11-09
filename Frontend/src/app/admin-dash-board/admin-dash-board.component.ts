import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CenterService } from 'src/service/center.service';
import { TestService } from 'src/service/test.service';
import { AppointmentService } from 'src/service/appointment.service';
import { AuthService } from 'src/service/auth.service';

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


  constructor(private router: Router, private testService: TestService, private centerService: CenterService,
    private appointmentService: AppointmentService, private authService: AuthService) { }

  ngOnInit(): void {
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
    this.authService.setLoggedIn(false);
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

  adminRefresh() {
    this.ngOnInit();
    this.router.navigate(["/admin/dashboard"]);
  }

}
