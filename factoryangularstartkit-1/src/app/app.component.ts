import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Logger} from './@shared/helpers/others/logger.service';
import {environment} from '../environments/environment';

const log = new Logger('AppComponent');

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'FactoryAngularStarterKit';
  showLoading?: Observable<boolean>;

  constructor() {}

  ngOnInit(): void {
    // Configuration du logger
    if (environment.production) {
      Logger.enableProductionMode();
    }
  }
}
