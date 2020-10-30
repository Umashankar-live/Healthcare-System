import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CenterModel } from '../../models/center.model';
import { Tests } from '../../models/test.model';
import { CenterService } from '../../service/center.service';
import { TestService } from '../../service/test.service';

@Component({
  selector: 'app-add-center',
  templateUrl: './add-center.component.html',
  styleUrls: ['./add-center.component.css']
})
export class AddCenterComponent implements OnInit {

  selectedTests: number[] = [];
  index: number = 0;
  checkedTests: Tests[] = [];
  finalTests: Tests[] = [];
  center: CenterModel;

  tests: Tests[] = [];
  tests1: Tests[] = [];

  //Flags required for interactive UI
  isAdded: boolean = null
  isUpdated: boolean = false
  isLoading: boolean = true
  isErrorUpdating: boolean = false

  sortedById: boolean = null
  sortedByName: boolean = null
  sortedByDes: boolean = null
  isDeleteError: boolean = false


  constructor(private route: Router, private service: CenterService, private testService: TestService) {
    this.center = new CenterModel();
  }

  ngOnInit(): void {
    this.tests = []
    this.tests1 = []
    this.testService.fetchAllTests().subscribe(
      (data => {
        this.isLoading = false
        for (let a of (data as any)) {
          this.tests.push({
            testId: a.testId,
            testName: a.testName

          })

          this.tests1.push({
            testId: a.testId,
            testName: a.testName

          })
        }
        this.sortById()
      })
    )
  }


  add(test: Tests) {

    this.checkedTests.push(test);
    //this.tests.splice(test.testId, 1); 

  }

  saveCenter() {
    console.log(this.checkedTests);
    this.checkedTests.forEach((item, index) => {
      if (this.finalTests.findIndex(i => i.testId == item.testId) === -1) {
        this.finalTests.push(item)
      }
    });
    this.center.listOfTests = this.finalTests;
    console.log(this.center.listOfTests);
    console.log(this.center);
    this.service.addCeter(this.center).subscribe(response => {
      alert(" Center successfully added !!!")
      this.route.navigate(['admin/dashboard/center/view'])
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

  logout() {
    sessionStorage.clear();
    this.route.navigate(['login']);
  }

}
