import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-main-guest',
  templateUrl: './main-guest.component.html',
  styleUrls: ['./main-guest.component.css']
})
export class MainGuestComponent implements OnInit {
  test: Date = new Date();

  constructor() {
  }

  ngOnInit(): void {
  }

}
