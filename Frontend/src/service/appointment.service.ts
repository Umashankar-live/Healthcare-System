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
    return this.http.delete("http://localhost:9010/appointments/getAllAppointments" + appointmentId);
  }

  rejectedStatus(appointmentId: number) {
    return this.http.get<AppointmentModel>("http://localhost:9010/appointments/cancel/" + appointmentId);
  }
  approvedStatus(appointmentId: number) {
    return this.http.get<AppointmentModel>("http://localhost:9010/appointments/approve/" + appointmentId);
  }

  countPending() {
    return this.http.get("http://localhost:9010/appointments/getPendingCount");
  }

  makeAppointment(appointment: AppointmentModel) {
    return this.http.post<AppointmentModel>("http://localhost:9010/appointments/makeAppointment", appointment);
  }

  checkUserAppointment(userId: string) {
    return this.http.get<AppointmentModel>("http://localhost:9010/appointments/appointmentsByUserId/" + userId)
  }

  fetchAppointmentById(appointmentId: number){
    return this.http.get<AppointmentModel>("http://localhost:9010/appointments/searchAppointmentByAppointmentId/"+appointmentId);
  }

}
