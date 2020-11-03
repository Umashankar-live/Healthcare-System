import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CenterModel } from '../../models/center.model';
import { CenterService } from '../../service/center.service';
import { Tests } from '../../models/test.model';

@Component({
  selector: 'app-user-list-center',
  templateUrl: './user-list-center.component.html',
  styleUrls: ['./user-list-center.component.css']
})
export class UserListCenterComponent implements OnInit {

  centers: CenterModel[] = [];
  center: CenterModel;
  centers1: CenterModel[] = [];

  //Flags required for interactive UI
  isAdded: boolean = null
  isUpdated: boolean = false
  isLoading: boolean = true
  isErrorUpdating: boolean = false

  sortedById: boolean = null
  sortedByName: boolean = null
  sortedByDes: boolean = null
  centerNames: String;
  tests: Tests[] = [];


  constructor(private route: Router, private service: CenterService) { }

  ngOnInit(): void {
    this.centers = []
    this.centers1 = []
    this.service.fetchAllCenters().subscribe(
      (data => {
        this.isLoading = false
        for (let a of (data as any)) {
          this.centers.push({
            centerId: a.centerId,
            centerName: a.centerName,
            listOfTests: a.listOfTests

          })

          this.centers1.push({
            centerId: a.centerId,
            centerName: a.centerName,
            listOfTests: a.listOfTests

          })
        }
        this.sortById()
      })
    )
  }


  getDetailsById(centerId: number) {

    this.service.fetchCenterByCenterId(centerId).subscribe(data => {
      this.center = data;
      this.centerNames = this.center.centerName;
      this.tests = this.center.listOfTests;
      console.log(this.center);
      if (this.center == null) {
        alert("No Details Available ");
      }
    });

  }

  //Sort by id
  sortById() {
    this.centers.sort(this.sortByProperty('centerId'))
    this.sortedById = true
    this.sortedByName = this.sortedByDes = false
  }

  //Sort by name
  sortByName() {
    this.centers1.sort(this.sortByProperty('centerName'))
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
    this.centers1 = this.centers.filter(centers => centers.centerName.includes(event.target.value))
    if (event.target.value == '' || event.target.value == undefined)
      this.centers1 = this.centers
  }

  logout() {
    sessionStorage.clear();
    this.route.navigate(['login']);
  }
}
