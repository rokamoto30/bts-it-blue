import {Injectable} from "@angular/core"
import { DataSource } from "@angular/cdk/collections"
import {BehaviorSubject, Observable} from 'rxjs'
import { ApiService } from "./api.service";
import { StepInterface } from "../types/stepInterface";
// https://www.youtube.com/watch?v=itq4KHN8buM

@Injectable()
export class StepDataSource extends DataSource<StepInterface> {
    steps$ = new BehaviorSubject<StepInterface[]>([]);
    isLoading$ = new BehaviorSubject<boolean>(false);

    constructor(private service: ApiService) {
        super();
    }

    connect(): Observable<StepInterface[]> {
        return this.steps$.asObservable();
    }
    disconnect(): void {
        this.steps$.complete();
    }

    loadSteps() :void {
        this.isLoading$.next(true);
        this.service.getSteps().subscribe( res => {
            this.steps$.next(res);
            this.isLoading$.next(false);
        })
    }
}