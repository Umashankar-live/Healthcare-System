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
    this.service.fetchAppointmentById(this.appointmentId).subscribe(data => {
      this.appointment=data;
    });
  }

}
