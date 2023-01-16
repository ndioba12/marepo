import { Injectable } from '@angular/core';
import { SharedState } from './shared.state';

@Injectable()
export class SharedFacade {
  constructor(private sharedState: SharedState) {}

  isLoading = () => this.sharedState.getIsLoading();

  setLoading = (value: boolean) => {
    this.sharedState.setIsLoading(value);
  };
}
