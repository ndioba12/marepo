import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-popup-reset-password',
  templateUrl: './popup-reset-password.component.html',
  styleUrls: ['./popup-reset-password.component.css'],
})
export class PopupResetPasswordComponent {
  formBuiler: FormBuilder = new FormBuilder();

  @ViewChild('changePasswordModal') changePasswordModal: ElementRef | undefined;
  closeResult = '';
  firstConnect = localStorage.getItem('premiere-connexion');
  hide: boolean = true;
  hide2: boolean = true;

  constructor(
    private router: Router,
    private modalService: NgbModal,
    private config: NgbModalConfig
  ) {
    this.config.backdrop = 'static';
    this.config.keyboard = false;
  }

  ngAfterViewInit(): void {
    if (this.firstConnect === 'true') {
      this.open(this.changePasswordModal);
    }
    this.router.events.subscribe({
      next: () => this.modalService.dismissAll(),
    });
  }

  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title', centered: true })
      .result.then(
        (save) => {
          this.closeResult = `Closed with: ${save}`;
          localStorage.setItem('premiere-connexion', 'true');
        },
        (dismiss) => {
          this.closeResult = `Dismissed dismiss`;
        }
      );
  }
}
