import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tests } from '../../models/test.model';
import { TestService } from '../../service/test.service';

@Component({
  selector: 'app-list-test',
  templateUrl: './list-test.component.html',
  styleUrls: ['./list-test.component.css']
})
export class ListTestComponent implements OnInit {

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

  constructor(private route: Router, private service: TestService) {

  }

  ngOnInit(): void {
    this.tests = []
    this.tests1 = []
    this.service.fetchAllTests().subscribe(
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


  // reloadData() {
  //   this.service.fetchAllTests().subscribe(data => {
  //     this.tests = data;
  //     console.log(this.tests);
  //   });
  // }

  remove(testId: number) {
   
    this.service.deleteTest(testId).subscribe((res) => {

      if (res == -1)
        this.isDeleteError = true

      else {
        this.tests = []
        this.ngOnInit()
      }
    })


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
