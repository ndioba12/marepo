import { Component, ElementRef, ViewChild } from '@angular/core';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-popup-reset-password',
  templateUrl: './popup-reset-password.component.html',
  styleUrls: ['./popup-reset-password.component.css']
})
export class PopupResetPasswordComponent {

  @ViewChild('changePasswordModal') changePasswordModal: ElementRef | undefined;
  closeResult = '';
  firstConnect = localStorage.getItem('premiere-connexion');
  hide: boolean = true;
  hide2: boolean = true;

  constructor(
    private modalService: NgbModal,
    private config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngAfterViewInit(): void {
    if (this.firstConnect === "false") {
      this.open(this.changePasswordModal);
    }
  }

  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title', centered: true })
      .result.then(
        (save) => {
          this.closeResult = `Closed with: ${save}`;
          localStorage.setItem('premiere-connexion', "true");
        },
        (dismiss) => {
          this.closeResult = `Dismissed dismiss`;
        }
      );
  }


}
