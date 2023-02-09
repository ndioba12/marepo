import {Injectable} from '@angular/core';
import Swal from 'sweetalert2';
import { ToastrService } from 'ngx-toastr';
declare var $: any;

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor(
    private toastr: ToastrService
  ) {}

  showAlert(alert: Alert): void {
    switch (alert?.status) {
      case 'OK':
        this.toastr.success(alert?.message, alert?.titre);
        break;
      case 'EXCEPTION':
        this.toastr.error(alert?.message, alert?.titre);
        break;
      case 'WARNING':
        this.toastr.warning(alert?.message, alert?.titre);
        break;
      case 'INFO':
        this.toastr.info(alert?.message, alert?.titre);
        break;
      default:
        this.toastr.error(alert?.message, alert?.titre);
        break;
    }
  }

  showSwal(type: string, callbacks: any): void {
    Swal.fire({
      title: 'Etes-vous sûr ?',
      text: 'Vous ne pourrez pas revenir en arrière !',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#1C5EAB',
      confirmButtonText: 'Continuer',
      cancelButtonColor: '#FF4D4F',
      cancelButtonText: 'Annuler',
    }).then((result: any) => {
      if (result.isConfirmed) {
        callbacks();
      }
    });
  }

}

export interface Alert {
  status?: string;
  message?: string;
  titre: string;
}
