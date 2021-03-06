import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CenterModel } from 'src/models/center.model';
import { Tests } from 'src/models/test.model';
import { CenterService } from 'src/service/center.service';
import {AppointmentService} from 'src/service/appointment.service';
import { AppointmentModel } from 'src/models/appointment.model';


@Component({
  selector: 'app-user-list-test',
  templateUrl: './user-list-test.component.html',
  styleUrls: ['./user-list-test.component.css']
})
export class UserListTestComponent implements OnInit {

  tests: Tests[] = [];
  tests1: Tests[] = [];
  testId : number ;
  centerId: number;
  center: CenterModel;
  appointment: AppointmentModel;
  msg :string;
  errormsg: string;



  //Flags required for interactive UI
  isAdded: boolean = null
  isUpdated: boolean = false
  isLoading: boolean = true
  isErrorUpdating: boolean = false

  sortedById: boolean = null
  sortedByName: boolean = null
  sortedByDes: boolean = null
  isDeleteError: boolean = false
  appoint: AppointmentModel;
  
  constructor(private route: ActivatedRoute, private router: Router, private service: CenterService, private appointmentService : AppointmentService) {
    this.appointment = new AppointmentModel();
    this.appoint = new AppointmentModel();
  }

  ngOnInit() {

    this.route.params.subscribe(x => this.centerId = x['centerId']);
    console.log(this.centerId);
    this.service.fetchCenterByCenterId(this.centerId).subscribe(data => {
      this.center = data;
      this.tests = this.center.listOfTests
      this.tests1 = this.center.listOfTests
      console.log(this.center);
      this.isLoading = false
    });
  }

  //Sort by id
  sortById() {
    this.tests1.sort(this.sortByProperty('testId'))
    this.sortedById = true
    this.sortedByName = this.sortedByDes = false
  }

  //Sort by name
  sortByName() {
    this.tests1.sort(this.sortByProperty('testName'))
    this.sortedByName = true
    this.sortedById = this.sortedByDes = false
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

  onKey(event: any) {
    this.tests1 = this.tests.filter(test => test.testName.includes(event.target.value))
    if (event.target.value == '' || event.target.value == undefined)
      this.tests1 = this.tests
  }


  saveTestId(id: number){

    this.testId = id ;

  }


  appointmentUser(){
  
    this.appointment.userId = sessionStorage.getItem('custId');
    this.appointment.centerId = this.centerId ;
    this.appointment.testId = this.testId ;
    console.log(this.appointment.dateTime);
    this.appointmentService.makeAppointment(this.appointment).subscribe(data => {
      console.log(data);
      this.appointment=data;
      this.appoint=data
      this.isAdded = true ;
      
    },error => {this.isErrorUpdating = true; });   

  }

  routerViewStatus(){
    this.router.navigate(['/user/dashboard/viewStatus/',this.appoint.appointmentId]);
     
  }

  logout() {
    sessionStorage.clear();
    this.router.navigate(['login']);
  }
}
