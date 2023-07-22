import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormComponent } from './components/form/form.component';

import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import {MatChipsModule} from '@angular/material/chips';
import {MatCardModule} from '@angular/material/card';
import { MatFormFieldModule } from "@angular/material/form-field";
import {MatTableModule} from '@angular/material/table';



import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ApiService } from './service/api.service';
import { StepDataSource } from './service/step.dataSource';


@NgModule({
  declarations: [
    AppComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,

    FormsModule, 
    ReactiveFormsModule,

    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatChipsModule,
    MatCardModule,
    MatFormFieldModule,
    MatTableModule,

    BrowserAnimationsModule

  ],
  providers: [ApiService, StepDataSource],
  bootstrap: [AppComponent]
})
export class AppModule { }
