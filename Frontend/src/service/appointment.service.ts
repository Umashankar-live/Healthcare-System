import { Injectable } from '@angular/core';
import { AppointmentModel } from '../models/appointment.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  
  constructor(private http: HttpClient) { }

  fetchAllAppointment() {
    return this.http.get<AppointmentModel[]>("http://localhost:9010/appointments/getAllAppointments");
  }


  deleteappointment(appointmentId: number) {
    return this.http.delete("http://localhost:9010/appointments/getAllAppointments"+appointmentId);
    }

    rejectedStatus(appointmentId: number) {
      return this.http.put<AppointmentModel[]>("http://localhost:9010/appointments/approve/"+appointmentId);
    }
    approvedStatus(appointmentId: number) {
      throw new Error('Method not implemented.');
    }

  

}