import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


/**
 * Use this service to make your REST calls,
 * use http client to do so
 * 
 * Ref:
 * https://angular.io/api/common/http/HttpClient
 * 
 */


@Injectable({
  providedIn: 'root'
})
export class ChallengeService {

  constructor(private httpClient: HttpClient) { }
}
