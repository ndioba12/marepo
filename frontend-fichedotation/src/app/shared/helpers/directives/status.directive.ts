import { Directive, Input, ElementRef } from '@angular/core';

@Directive({
  selector: '[appStatus]',
})
export class StatusDirective {

  constructor(private elementRef: ElementRef) {}

  @Input() set appStatus(status: string | boolean) {
    switch (status) {
      case 'Actif':
        this.elementRef.nativeElement.classList.remove('etat-inactif');
        this.elementRef.nativeElement.classList.add('etat-actif');
        this.elementRef.nativeElement.innerHTML = 'Actif';
        break;

      case 'Inactif':
        this.elementRef.nativeElement.classList.remove('etat-actif');
        this.elementRef.nativeElement.classList.add('etat-inactif');
        this.elementRef.nativeElement.innerHTML = 'Inactif';
        break;

      case true:
        this.elementRef.nativeElement.classList.remove('etat-inactif');
        this.elementRef.nativeElement.classList.add('etat-actif');
        this.elementRef.nativeElement.innerHTML = 'Actif';
        break;

      case false:
        this.elementRef.nativeElement.classList.remove('etat-actif');
        this.elementRef.nativeElement.classList.add('etat-inactif');
        this.elementRef.nativeElement.innerHTML = 'Inactif';
        break;

      case 'Reformé':
        this.elementRef.nativeElement.classList.add('etat-reforme');
        this.elementRef.nativeElement.innerHTML = 'Reformé';
        break;

      case 'Alerte':
        this.elementRef.nativeElement.classList.add('etat-alerte');
        this.elementRef.nativeElement.innerHTML = 'Alerte';
        break;

      default:
        break;
    }
  }
}
