import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Login } from 'src/models/login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {
 
 

  login_url = "http://localhost:9002/healthcaresystem/login";
  registration_url = "http://localhost:9002/healthcaresystem/registeruser";
 

  constructor(private _http:HttpClient) { }

  public loginUser(login: Login) {
    return this._http.post<User>(this.login_url,login)
  }
  
  public registerUser(user: User):Observable<any> {
    return this._http.post<any>(this.registration_url,user)
  }
  
}
