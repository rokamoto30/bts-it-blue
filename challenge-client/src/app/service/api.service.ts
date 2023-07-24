import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {catchError} from 'rxjs/operators'
import { Step } from '../model/step.model';
import { StepInterface } from '../types/stepInterface';
import { CordInterface } from '../types/cordInterface';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  // ERROR
  errorHandler(response : Observable<Object>) : Observable<Object> {
    return response.pipe(catchError(
      (error) => {
        console.log(error)
        return throwError(error);
      }
    ));
  }
  prefix = "http://localhost:8080"
  // API CALLS
  get(endpoint : string) :Observable<Object> {
    return this.errorHandler( this.http.get(this.prefix + endpoint) );
  }
  post(endpoint : string, body : Object) : Observable<Object> {
    return this.errorHandler( this.http.post(this.prefix + endpoint, body) );
  }
  put(endpoint : string, body : Object) : Observable<Object> {
    return this.errorHandler( this.http.post(this.prefix + endpoint, body) );
  }
  delete(endpoint : string, body : Object) : Observable<Object> {
    return this.http.request('delete', this.prefix + endpoint, {body: body})
    // return this.errorHandler( this.http.delete(this.prefix + endpoint, options) );
  }

  //API SERVICE INIT
  getVectors() :Observable<Object> {
    return this.get("/vectors");
  }
  getSpeeds() :Observable<Object> {
    return this.get("/speed-list");
  }
  getDirections() :Observable<Object> {
    return this.get( "/direction-list");
  }
  getSteps() :Observable<StepInterface[]> {
    return this.http.get<StepInterface[]>(this.prefix + "/step/get").pipe(catchError(
      (error) => {
        console.log(error)
        return throwError('api error');
      }
    ));
  }
  getTotal() :Observable<string> {
    return this.http.get(this.prefix + "/total", {responseType: 'text'});
  }

  //API SERVICE CUD
  saveStep(step : Step) :Observable<Object> {
    return this.post("/step/saveUpdate", step);
  }
  deleteStep(step : Step) :Observable<Object> {
    return this.delete("/step/delete", step);
  }

}
