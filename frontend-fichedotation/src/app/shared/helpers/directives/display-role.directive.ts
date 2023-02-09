import { Directive, ElementRef, Input } from '@angular/core';

@Directive({
  selector: '[appDisplayRole]',
})
export class DisplayRoleDirective {
  constructor(private elementRef: ElementRef) {}

  @Input() set appDisplayRole(role: string) {
    switch (role) {
      case 'SAD':
        this.elementRef.nativeElement.innerHTML = '(Super Admin)';
        break;
      case 'SUP':
        this.elementRef.nativeElement.innerHTML = '(Superviseur)';
        break;
      case 'ADM':
        this.elementRef.nativeElement.innerHTML = '(Administrateur)';
        break;
      case 'ACH':
        this.elementRef.nativeElement.innerHTML = '(Achats)';
        break;
      case 'SET':
        this.elementRef.nativeElement.innerHTML = '(Support Technique)';
        break;
      case 'SEL':
        this.elementRef.nativeElement.innerHTML = '(Service Logistique)';
        break;
      case 'CPT':
        this.elementRef.nativeElement.innerHTML = '(Comptable)';
        break;
      case 'PEL':
        this.elementRef.nativeElement.innerHTML = '(Personnel)';
        break;
      case 'CTG':
        this.elementRef.nativeElement.innerHTML = '(Contrôleur De Gestion)';
        break;
      case 'CTO':
        this.elementRef.nativeElement.innerHTML = '(Chief Technical Officer - CTO)';
        break;
      case 'CSO':
        this.elementRef.nativeElement.innerHTML = '(Chief Strategy Officer - CSO)';
        break;
      default:
        this.elementRef.nativeElement.innerHTML = '(Personnel)';
        break;
    }
  }
}
