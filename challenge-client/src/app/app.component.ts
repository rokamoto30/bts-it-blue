import { Component } from '@angular/core';
import { ChallengeService } from './challenge.service';


/**
 * Implement the challenge logic in this component
 * 
 * Use the provided service to make your REST calls (don't make the calls from this component)
 * 
 * You MUST use types for your data/functions, for example:
 *        function doSomething(myInput: number): string{ return ''}  ===> OK
 *        function doSomething(myInput){ return ''}                  ===> WRONG
 * 
 * 
 * Do your best to use Angular framework features to complete the tasks
 * This project has imported Angular's Reactive and Template forms modules, think about using the forms module to faciliate data binding
 *      https://angular.io/guide/forms-overview
 * 
 * 
 * Make sure your use meaningful variable/function names
 * 
 * 
 */

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private challengeService: ChallengeService){}
}
