import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tests } from '../../models/test.model';
import { TestService } from '../../service/test.service';

@Component({
  selector: 'app-add-test',
  templateUrl: './add-test.component.html',
  styleUrls: ['./add-test.component.css']
})
export class AddTestComponent implements OnInit {
  test : Tests;
  constructor(private router : Router, private service : TestService) {
    this.test = new Tests();
   }

  ngOnInit() {
  }

  saveTest(){
    console.log(this.test.testName);
    this.service.addTest(this.test).subscribe(response=>{
      //this.route.navigate(['list-test']);
    });
    
  }

  logout(){
    sessionStorage.clear();
    this.router.navigate(['login']);
  }

}
