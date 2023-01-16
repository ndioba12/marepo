import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class LoginComponent implements OnInit {

  hide:boolean = true;

  constructor(private router:Router) { }

  ngOnInit() {
    document.body.className = "bg-login";
    localStorage.setItem('premiere-connexion', "false");
  }

  ngOnDestroy() {
    document.body.className = "";
  }

  onLogin(): void{
    this.router.navigateByUrl('gestion-des-actifs')
  }

}
