import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import {Router} from '@angular/router';

@Injectable()
export class SharedState {
  private isLoading: Subject<boolean> = new Subject<boolean>();

  constructor(private router: Router) {}

  getIsLoading = () => this.isLoading;

  setIsLoading = (value: boolean) => {
    this.isLoading.next(value);
  }
}
