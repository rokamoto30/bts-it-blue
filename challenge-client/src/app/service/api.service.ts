import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {catchError} from 'rxjs/operators'
import { Step } from '../model/step.model';

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
        return throwError('api error');
      }
    ));
  }

  // API CALLS
  get(endpoint : string) :Observable<Object> {
    return this.errorHandler( this.http.get("http://localhost:8080" + endpoint) );
  }
  post(endpoint : string, body : Object) : Observable<Object> {
    return this.errorHandler( this.http.post("localhost:8080" + endpoint, body) );
  }
  put(endpoint : string, body : Object) : Observable<Object> {
    return this.errorHandler( this.http.post("localhost:8080" + endpoint, body) );
  }
  delete(endpoint : string, body : Object) : Observable<Object> {
    return this.errorHandler( this.http.post("localhost:8080" + endpoint, body) );
  }

  //API SERVICE INIT
  getSpeeds() :Observable<Object> {
    return this.get("/speed-list");
  }
  getDirections() :Observable<Object> {
    return this.get( "/direction-list");
  }
  getSteps() :Observable<Object> {
    return this.get( "/step/get");
  }
  getTotal() :Observable<Object> {
    return this.get( "/total");
  }

  //API SERVICE CUD
  saveStep(step : Step) :Observable<Object> {
    return this.post("/step/saveUpdate", step);
  }
  deleteStep(step : Step) :Observable<Object> {
    return this.delete("/step/delete", step);
  }

}
