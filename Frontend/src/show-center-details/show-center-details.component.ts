import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CenterModel } from '../models/center.model';
import { Tests } from '../models/test.model';
import { CenterService } from '../service/center.service';

@Component({
  selector: 'app-show-center-details',
  templateUrl: './show-center-details.component.html',
  styleUrls: ['./show-center-details.component.css']
})
export class ShowCenterDetailsComponent implements OnInit {

  private centerId : number;
  private center : CenterModel;
  private tests : Tests[]=[];
  private centerNames : String;

  constructor(private service : CenterService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe(x => this.centerId = x['id']);
    console.log(this.centerId);
    this.service.fetchCenterByCenterId(this.centerId).subscribe(data => {
      this.center = data;
      this.centerNames= this.center.centerName;
      this.tests = this.center.listOfTests;
      console.log(this.center);
      if (this.center == null) {
        alert("No Details Available ");
        this.router.navigate(['list-center']);
      }
  });

  }

}
