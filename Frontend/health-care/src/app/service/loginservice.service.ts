import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {
 
 

  login_url = "http://localhost:9002/healthcaresystem/login";
  registration_url = "http://localhost:9002/healthcaresystem/registeruser";
 

  constructor(private _http:HttpClient) { }

  public loginUser(user: User):Observable<any> {
    return this._http.post<any>(this.login_url,user)
  }
  public registerUser(user: User):Observable<any> {
    return this._http.post<any>(this.registration_url,user)
  }
  
}
