import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CenterModel } from '../models/center.model';

@Injectable({
  providedIn: 'root'
})
export class CenterService {
  countCenter() {
    return this.http.get("http://localhost:9003/admin/diagnosticCenter/countCenter");
  }

  addCeter(center: CenterModel) : Observable<Object>{
    return this.http.post("http://localhost:9003/admin/diagnosticCenter/addCenter" , center);
  }

  deleteCenter(index: number) {
    console.log(index);
    return this.http.delete("http://localhost:9003/admin/diagnosticCenter/deleteCenter/"+index);
  }

  fetchAllCenters() {
    return this.http.get<CenterModel[]>("http://localhost:9003/admin/diagnosticCenter/getAllCenters");
  }

  fetchCenterByCenterId(index : number){
    return this.http.get<CenterModel>("http://localhost:9003/admin/diagnosticCenter/searchCenter/" +index);
  }

  constructor(private http : HttpClient) { }
}
