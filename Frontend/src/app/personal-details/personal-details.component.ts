import { Component, OnInit } from '@angular/core';
import { LoginserviceService } from 'src/service/loginservice.service';
import { User } from 'src/models/user.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-personal-details',
  templateUrl: './personal-details.component.html',
  styleUrls: ['./personal-details.component.css']
})
export class PersonalDetailsComponent implements OnInit {
  custId: string;
  user: User;


  constructor(private route: ActivatedRoute, private router: Router, private service: LoginserviceService) {

    this.user = new User();
    
  }


  ngOnInit() {

    this.route.params.subscribe(x => this.custId = x['userId']);
    console.log(this.custId);
    this.service.getUserByUserId(this.custId).subscribe(data => {
      this.user = data;
      console.log(this.user);

    });

  }

}
