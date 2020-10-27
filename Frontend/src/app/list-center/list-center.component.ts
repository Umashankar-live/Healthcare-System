import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CenterModel } from '../../models/center.model';
import { CenterService } from '../../service/center.service';

@Component({
  selector: 'app-list-center',
  templateUrl: './list-center.component.html',
  styleUrls: ['./list-center.component.css']
})
export class ListCenterComponent implements OnInit {

  centers : CenterModel[]=[];
  constructor(private route : Router, private service : CenterService) { }

  ngOnInit() {
    setTimeout(() => { this.reloadData() }, 100);
  }

  reloadData() {
    this.service.fetchAllCenters().subscribe(data => {
      this.centers =data;
      console.log(this.centers);
    });
  }

  remove(index: number){
    var ans =confirm("Are you sure you want to delete?");
    if(ans){
      this.service.deleteCenter(index).subscribe(response=>{
         console.log(this.centers);
         this.reloadData();
      });
    }
  }
  
  logout(){
    sessionStorage.clear();
    this.route.navigate(['login']);
  }
}
