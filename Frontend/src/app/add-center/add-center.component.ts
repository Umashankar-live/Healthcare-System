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
  center: CenterModel;
  tests: Tests[] = [];

  constructor(private route: Router, private service: CenterService, private testService: TestService) {
    this.center = new CenterModel();
  }

  ngOnInit() {
    this.testService.fetchAllTests().subscribe(data => {
      this.tests = data;
      console.log(this.tests);
    });
  }

  add(test: Tests) {

    this.checkedTests.push(test);
    //this.tests.splice(test.testId, 1); 

  }

  saveCenter() {
    console.log(this.checkedTests);
    this.center.listOfTests = this.checkedTests;
    console.log(this.center.listOfTests);
    console.log(this.center);
    this.service.addCeter(this.center).subscribe(response => {
      this.route.navigate(['list-center'])
    });
  }

  logout() {
    sessionStorage.clear();
    this.route.navigate(['login']);
  }

}
