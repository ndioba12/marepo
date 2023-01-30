import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  @ViewChild('panelToggle') panelToggle!: ElementRef;
  firstConnect: any = localStorage.getItem('premiere-connexion');

  constructor() {}

  ngOnInit(): void {}

  togglePanel() {
    this.panelToggle.nativeElement.classList.add('panel-dismiss');
  }

  ngAfterViewInit(): void {
    if (this.firstConnect === 'true') {
      console.log('helo');
    }
  }
}
