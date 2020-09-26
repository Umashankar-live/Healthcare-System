import { Injectable } from '@angular/core';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  constructor() { }

  authenticate(user: User):boolean{
    // hard coded validation
    if(user!=null){
      sessionStorage.setItem("userName", user.userName);
      // based on type of user
      sessionStorage.setItem("usertype", user.role);

      return true;
    }else{
      return false;
    }
  }

  isUserLoggedIn(): boolean{
    let user = sessionStorage.getItem("userName");
    if(user == null)
      return false;
    return true;  
  }

  typeOfUser():string{
    let userType = sessionStorage.getItem("usertype");
    return userType;
  }

  logout(){
    sessionStorage.removeItem("userName");
    sessionStorage.removeItem("usertype");
    sessionStorage.removeItem("loggedInUser");
  }

}
