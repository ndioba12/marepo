import { Directive, Input, ElementRef } from '@angular/core';

@Directive({
  selector: '[appEtatMateriel]'
})
export class EtatMaterielDirective {

  constructor(private elementRef: ElementRef) {}

  @Input() set appEtatMateriel(statut: string | boolean) {

    switch ((typeof statut === 'string') ? statut.toUpperCase():statut) {
      case 'ACTIF':
        this.elementRef.nativeElement.classList.remove('etat-inactif');
        this.elementRef.nativeElement.classList.add('etat-actif');
        this.elementRef.nativeElement.innerHTML = 'Actif';
        break;

      case 'INACTIF':
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

      case 'REFORME':
        this.elementRef.nativeElement.classList.add('etat-reforme');
        this.elementRef.nativeElement.innerHTML = 'Reformé';
        break;

      case 'ALERTE':
        this.elementRef.nativeElement.classList.add('etat-alerte');
        this.elementRef.nativeElement.innerHTML = 'Alerte';
        break;

      default:
        this.elementRef.nativeElement.innerHTML = 'Bakhoul';
        break;
    }
  }
}
