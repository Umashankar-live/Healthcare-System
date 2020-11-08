import { Component, OnInit } from '@angular/core';
import { LoginserviceService } from 'src/service/loginservice.service';
import { User } from 'src/models/user.model';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-personal-details',
  templateUrl: './personal-details.component.html',
  styleUrls: ['./personal-details.component.css']
})
export class PersonalDetailsComponent implements OnInit {
  custId: string;
  user: User;

  userName: string;
  email: string;
  age: number;
  gender: string;
  mobile: number;
  password: string;
  isErrorUpdating: boolean = false;
  isUpdated: boolean;


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

  onUpdate(form: NgForm) {

    if (form.valid) {
      let user = new User()
      user.userName = form.value.userNAme
      user.age = form.value.age
      user.email = form.value.email
      user.gender = form.value.gender
      user.mobileNo = form.value.mobile
      user.password = form.value.password
      // user.userId = sessionStorage.getItem('custId')

      this.service.updateUser(user, sessionStorage.getItem('custId')).subscribe(res => {
        console.log(res)
        this.isUpdated = true

      })
    }
  }

  reload() {
    this.ngOnInit();
  }


}
