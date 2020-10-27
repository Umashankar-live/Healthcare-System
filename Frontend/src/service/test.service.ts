import { Injectable } from '@angular/core';
import { Tests } from '../models/test.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  countTest() {
    return this.http.get("http://localhost:8001/admin/test/countTest");
  }

  deleteTest(index: number) {
    console.log(index);
    return this.http.delete("http://localhost:8001/admin/test/deleteTest/" + index);
  }

  fetchAllTests() {
    return this.http.get<Tests[]>("http://localhost:8001/admin/test/getAllTests");
  }

  constructor(private http: HttpClient) { }


  addTest(test: Tests) {
    return this.http.post("http://localhost:8001/admin/test/addTest", test);
  }

}
