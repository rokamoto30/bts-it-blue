import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';
import { Step } from 'src/app/model/step.model';
import { StepDataSource } from 'src/app/service/step.dataSource';
import { PlotComponent } from '../plot/plot.component';

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

  // //plotting
  // @ViewChild('canvas', {static:true}) myCanvas:  ElementRef;
  // canvas : HTMLCanvasElement;
  // context : CanvasRenderingContext2D;
  // width : number;
  // height : number;

  constructor(private service: ApiService, private fb: FormBuilder) { 

  }

  ngOnInit(): void {
    // this.canvas = this.myCanvas.nativeElement;
    // this.context = this.canvas.getContext('2d');
    // this.width = this.canvas.width;
    // this.height = this.canvas.height;
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

    // // plotting
    // this.plot();
  }

  // plot() {
  //   this.context.clearRect(0, 0, this.width, this.height);
  //   let curPos: [number, number] = [this.width, this.height];
  //   console.log(this.width)
  //   console.log(this.height)
  //   if (this.context) {
  //     this.context.beginPath();
  //     this.context.moveTo(curPos[0], curPos[0]);
  //     console.log("plotting");
  //     this.context.lineTo(0, 0);
  //     this.context.fill();
  //   }
  // }

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
