import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  constructor(private router:Router,private route:ActivatedRoute) { }

  ngOnInit() {
    document.body.className = "bg-login";
  }

  ngOnDestroy() {
    document.body.className = "";
  }

  ResetPassword(){
    Swal.fire({
      title: 'Réinitialisation',
      text: "Un e-mail de réinitialisation a été envoyé à votre adresse. Suivez les intructions fournies pour réinitialiser votre mot de passe.",
      icon: 'success',
      showCancelButton: false,
      confirmButtonColor: '#3085d6',
      confirmButtonText: 'Fermer',
    }).then(() => {
      this.router.navigate(['reset-password'], { relativeTo: this.route.parent });
    })
  }
}
