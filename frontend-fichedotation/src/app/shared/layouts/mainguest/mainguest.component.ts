import { Component, OnInit } from '@angular/core';
import {fader, slider} from '../../../app.routes-animations';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-mainguest',
  templateUrl: './mainguest.component.html',
  styleUrls: ['./mainguest.component.css'],
  animations:[
    fader,
    slider,
    // transformer,
    // stepper
  ]
})
export class MainguestComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    document.body.className = "bg-login";
  }

  ngOnDestroy() {
    document.body.className = "";
  }

  prepareRoute(outlet:RouterOutlet){
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'] ;
  }

}
