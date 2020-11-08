import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentModel } from 'src/models/appointment.model';
import { AppointmentService } from 'src/service/appointment.service';

@Component({
  selector: 'app-view-status',
  templateUrl: './view-status.component.html',
  styleUrls: ['./view-status.component.css']
})
export class ViewStatusComponent implements OnInit {

  appointment: AppointmentModel
  appointmentId: number;
  constructor(private route: ActivatedRoute, private router: Router, private service: AppointmentService) {
    this.appointment = new AppointmentModel();
  }

  ngOnInit() {

    this.route.params.subscribe(x => this.appointmentId = x['appointmentId']);

    if (this.appointmentId)
      this.service.fetchAppointmentById(this.appointmentId).subscribe(data => {
        this.appointment = data;
      },
        (error) => {
          alert("You Don't have appointment registered yet.. !!!");
          this.router.navigate(['user/dashboard']);
        });
  }

  remove(appointmentId: number) {
    console.log(this.appointment.status);
    console.log(this.appointment.appointmentId);
    if (this.appointment.status == "approved" || this.appointment.status == "rejected") {
      this.service.cancelAppointment(appointmentId).subscribe(data => {
        console.log(data);
        alert("Deleted Successfully... !!")

      })
      this.router.navigate(['user/dashboard']);
    }

    else {
      alert(" You cannot Delete a pending appointment... !!")
    }

  }



}
