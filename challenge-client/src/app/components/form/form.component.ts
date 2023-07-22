import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';
import { Step } from 'src/app/model/step.model';
import { StepDataSource } from 'src/app/service/step.dataSource';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  directions: string[] = [];
  speedDesc: string[] = [];
  descToType : Map<string, string> = new Map<string, string>();
  typeToDesc : Map<string, string> = new Map<string, string>();

  stepDataSource = new StepDataSource(this.service);
  step: Step = new Step(0, '', '', 0, 0);
  total: string = '';

  stepForm : FormGroup = this.fb.group({
    id: [0, [Validators.required]],
    speed: ['', [Validators.required]],
    direction: ['', [Validators.required]],
    hours: [0, [Validators.required]],
    mins: [0, [Validators.required]]
  });

  displayedColumns: string[] = ['id', 'speedType', 'speedDirection', 'durationHours', 'durationMinutes' ];


  constructor(private service: ApiService, private fb: FormBuilder) { 

  }

  ngOnInit(): void {
    this.sync();
  }

  sync() {
    this.service.getDirections().subscribe(res => this.directions = Object.values(res));
    this.service.getSpeeds()
      .subscribe(res => Object.values(res).map(speed => {
        this.descToType.set(speed['speedDescpription'], speed['speedType']);
        this.typeToDesc.set(speed['speedType'], speed['speedDescpription']);
        this.speedDesc.push(speed['speedDescpription'])
      }));
    this.stepDataSource.loadSteps();
    this.service.getTotal().subscribe(res => this.total = res);
  }

  onSave() {
    this.step = new Step(
      this.stepForm.controls['id'].value, 
      this.descToType.get(this.stepForm.controls['speed'].value), 
      this.stepForm.controls['direction'].value, 
      this.stepForm.controls['hours'].value, 
      this.stepForm.controls['mins'].value
    );
    this.service.saveStep(this.step).subscribe(() => this.sync());
  }

  onRowClick(row) {
    this.stepForm.setValue({
      id: row.id,
      speed: this.typeToDesc.get(row.speedType),
      direction: row.speedDirection,
      hours: row.durationHours,
      mins: row.durationMinutes
    });

  }

  onClear() {
    this.stepForm.setValue({
      id: 0,
      speed: '',
      direction: '',
      hours: 0,
      mins: 0
    });
  }

  onDelete() :void{
    this.step = new Step(
      this.stepForm.controls['id'].value, 
      this.descToType.get(this.stepForm.controls['speed'].value), 
      this.stepForm.controls['direction'].value, 
      this.stepForm.controls['hours'].value, 
      this.stepForm.controls['mins'].value
    );
    this.service.deleteStep(this.step).subscribe( () => {
      this.sync();
      this.onClear();
    } );
  }

  onSubmit(){};

}
