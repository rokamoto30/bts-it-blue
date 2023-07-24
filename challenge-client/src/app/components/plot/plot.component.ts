import { Component, OnInit, ViewChild, ElementRef, Input } from '@angular/core';
import { Step } from 'src/app/model/step.model';
import { Vector } from 'src/app/model/vector.model';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-plot',
  templateUrl: './plot.component.html',
  styleUrls: ['./plot.component.css']
})
export class PlotComponent implements OnInit {
  @ViewChild('canvas', {static:true}) myCanvas:  ElementRef;
  // @Input() steps :Step[];
  directionToAngle :Map<string, number> = new Map<string, number>([
    ["E", 0],
    ["NE", 45],
    ["N", 90],
    ["NW", 135],
    ["W", 180],
    ["SW", 225],
    ["S", 270],
    ["SE", 315]
  ]);
  
  vectors: Vector[] = [];

  constructor(private service: ApiService) { }


  ngOnInit(): void {
    const canvas: HTMLCanvasElement = this.myCanvas.nativeElement;
    this.plotSteps(canvas);
    
    // if (context) {
    //   this.plotSteps(canvas, this.steps);
    // }
  }

  plotSteps(canvas: HTMLCanvasElement) {

    const context = canvas.getContext('2d');
    let width = canvas.width;
    let height = canvas.height;
    let curPos: [number, number] = [width/2, height/2];

    this.service.getVectors()
      .subscribe(res => {
        Object.values(res).map(vector => {
          this.vectors.push(new Vector(vector['x']*10, vector['y']*-10))
        });

        if (context) {
          context.beginPath();
          context.moveTo(width/2, height/2);
          context.fillStyle = "rgb(228, 48, 32)";
          context.fillRect(curPos[0]-3,curPos[1]-3,6,6)
          context.fillStyle = "rgb(0, 0, 0)";
          this.vectors.forEach( vector => {
            curPos[0] = curPos[0] + vector.x;
            curPos[1] = curPos[1] + vector.y;
            context.lineTo(curPos[0], curPos[1]);
            console.log(vector)
          })
          context.fillStyle = "rgb(0, 201, 20)";
          context.fillRect(curPos[0]-3,curPos[1]-3,6,6)
          context.stroke();
        }

    });
  }

}
