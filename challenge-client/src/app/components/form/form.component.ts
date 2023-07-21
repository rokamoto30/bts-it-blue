import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';
import { Step } from 'src/app/model/step.model';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  directions: string[] = [];
  speedMap = new Map<string, string>();
  speedDescriptions :string[] = [];

  stepForm = this.fb.group({
    speed: ['', [Validators.required]],
    direction: ['', [Validators.required]],
    hours: [0, [Validators.required]],
    mins: [0, [Validators.required]]
  });

  // id = 0;
  // speedType = new FormControl('');
  // speedDirection = new FormControl('');
  // durationHours = new FormControl(0);
  // durationMinutes = new FormControl(0);


  constructor(private service: ApiService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.service.getDirections().subscribe(res => this.directions = Object.values(res));
    this.service.getSpeeds()
      .subscribe(res => Object.values(res).map(speed => {
        this.speedMap.set(speed['speedDescpription'], speed['speedType']);
        this.speedDescriptions.push(speed['speedDescpription']);
      }));
  }

  onSubmit() {
    console.log("submited")
  }

}
