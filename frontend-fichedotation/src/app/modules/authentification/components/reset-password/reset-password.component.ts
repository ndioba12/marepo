import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  hide:boolean = true;
  hide2:boolean = true;

  constructor(private router:Router) { }

  ngOnInit() {
    document.body.className = "bg-login";
    localStorage.setItem('premiere-connexion', "false");
  }

  ngOnDestroy() {
    document.body.className = "";
  }

  ResetPassword(): void{
    this.router.navigate(['auth/login']);
  }

}
