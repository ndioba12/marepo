import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {NavigationEnd, NavigationStart, Router} from '@angular/router';
// import { NavItem, NavItemType } from '../../md/md.module';
import {Subscription} from 'rxjs/Subscription';
import {Location, PopStateEvent} from '@angular/common';
import 'rxjs/add/operator/filter';

import PerfectScrollbar from 'perfect-scrollbar';
import {NavbarComponent} from '../navbar/navbar.component';

declare const $: any;

export interface DropdownLink {
  title: string;
  iconClass?: string;
  routerLink?: string;
}


export enum NavItemType {
  Sidebar = 1, // Only ever shown on sidebar
  NavbarLeft = 2, // Left-aligned icon-only link on navbar in desktop mode, shown above sidebar items on collapsed sidebar in mobile mode
  NavbarRight = 3 // Right-aligned link on navbar in desktop mode, shown above sidebar items on collapsed sidebar in mobile mode
}

export interface NavItem {
  type: NavItemType;
  title: string;
  routerLink?: string;
  iconClass?: string;
  numNotifications?: number;
  dropdownItems?: (DropdownLink | 'separator')[];
}

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit, AfterViewInit {

  public navItems: NavItem[];
  private _router: Subscription;
  private lastPoppedUrl: string;
  private yScrollStack: number[] = [];
  url: string;
  location: Location;

  @ViewChild('sidebar', {static: false}) sidebar: any;
  @ViewChild(NavbarComponent, {static: false}) navbar: NavbarComponent;

  constructor(private router: Router, location: Location) {
    this.location = location;
  }

  ngOnInit() {
    const elemMainPanel = document.querySelector('.main-panel') as HTMLElement;
    const elemSidebar = document.querySelector('.sidebar .sidebar-wrapper') as HTMLElement;
    this.location.subscribe((ev: PopStateEvent) => {
      this.lastPoppedUrl = ev.url;
    });
    this.router.events.subscribe((event: any) => {
      if (event instanceof NavigationStart) {
        if (event.url != this.lastPoppedUrl) {
          this.yScrollStack.push(window.scrollY);
        }
      } else if (event instanceof NavigationEnd) {
        if (event.url == this.lastPoppedUrl) {
          this.lastPoppedUrl = undefined;
          window.scrollTo(0, this.yScrollStack.pop());
        } else {
          window.scrollTo(0, 0);
        }
      }
    });
    this._router = this.router.events.filter(event => event instanceof NavigationEnd).subscribe((event: NavigationEnd) => {
      elemMainPanel.scrollTop = 0;
      elemSidebar.scrollTop = 0;
    });
    const html = document.getElementsByTagName('html')[0];
    if (window.matchMedia(`(min-width: 960px)`).matches && !this.isMac()) {
      let ps = new PerfectScrollbar(elemMainPanel);
      ps = new PerfectScrollbar(elemSidebar);
      html.classList.add('perfect-scrollbar-on');
    } else {
      html.classList.add('perfect-scrollbar-off');
    }
    this._router = this.router.events.filter(event => event instanceof NavigationEnd).subscribe((event: NavigationEnd) => {
      this.navbar.sidebarClose();
    });

    this.navItems = [
      {type: NavItemType.NavbarLeft, title: 'Dashboard', iconClass: 'fa fa-dashboard'},

      {
        type: NavItemType.NavbarRight,
        title: '',
        iconClass: 'fa fa-bell-o',
        numNotifications: 5,
        dropdownItems: [
          {title: 'Notification 1'},
          {title: 'Notification 2'},
          {title: 'Notification 3'},
          {title: 'Notification 4'},
          {title: 'Another Notification'}
        ]
      },
      {
        type: NavItemType.NavbarRight,
        title: '',
        iconClass: 'fa fa-list',

        dropdownItems: [
          {iconClass: 'pe-7s-mail', title: 'Messages'},
          {iconClass: 'pe-7s-help1', title: 'Help Center'},
          {iconClass: 'pe-7s-tools', title: 'Settings'},
          'separator',
          {iconClass: 'pe-7s-lock', title: 'Lock Screen'},
          {iconClass: 'pe-7s-close-circle', title: 'Log Out'}
        ]
      },
      {type: NavItemType.NavbarLeft, title: 'Search', iconClass: 'fa fa-search'},

      {type: NavItemType.NavbarLeft, title: 'Account'},
      {
        type: NavItemType.NavbarLeft,
        title: 'Dropdown',
        dropdownItems: [
          {title: 'Action'},
          {title: 'Another action'},
          {title: 'Something'},
          {title: 'Another action'},
          {title: 'Something'},
          'separator',
          {title: 'Separated link'},
        ]
      },
      {type: NavItemType.NavbarLeft, title: 'Log out'}
    ];
  }

  ngAfterViewInit() {
    this.runOnRouteChange();
  }

  public isMap() {
    if (this.location.prepareExternalUrl(this.location.path()) === '/maps/fullscreen') {
      return true;
    } else {
      return false;
    }
  }

  runOnRouteChange(): void {
    if (window.matchMedia(`(min-width: 960px)`).matches && !this.isMac()) {
      const elemSidebar = document.querySelector('.sidebar .sidebar-wrapper') as HTMLElement;
      const elemMainPanel = document.querySelector('.main-panel') as HTMLElement;
      let ps = new PerfectScrollbar(elemMainPanel);
      ps = new PerfectScrollbar(elemSidebar);
      ps.update();
    }
  }

  isMac(): boolean {
    let bool = false;
    if (navigator.platform.toUpperCase().indexOf('MAC') >= 0 || navigator.platform.toUpperCase().indexOf('IPAD') >= 0) {
      bool = true;
    }
    return bool;
  }

}
