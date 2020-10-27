import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user.model';
import { LoginserviceService } from '../../service/loginservice.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user=new User();
    userr=new User();
    rolee:string;
    msg='';
  constructor(private _service : LoginserviceService,private router: Router) { }

  ngOnInit() {
  }

  registerUser(){
    this._service.registerUser(this.user).subscribe(
    data=>{
      console.log("response received");
      alert('User Registration Successful! Please Login.')
 
      this.router.navigate(['/login'])
      this.userr=data;
    },
    error=>{
      console.log("exception occured")
      this.msg="User with same username already exist";
    })
  }
}
