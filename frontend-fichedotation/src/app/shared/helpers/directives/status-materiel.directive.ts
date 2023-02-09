import { Directive, ElementRef, Input } from '@angular/core';

@Directive({
  selector: '[appStatusMateriel]'
})
export class StatusMaterielDirective {

  constructor(
    private elementRef: ElementRef
  ) { }

  @Input() set appStatusMateriel(statusMateriel: string) {
    switch (statusMateriel.toUpperCase()) {
      case "AFFECTE":
        this.elementRef.nativeElement.classList.add('affecter');
        this.elementRef.nativeElement.innerHTML = "affecté";
        break;
      case "NON REFERENCIE":
        this.elementRef.nativeElement.classList.add('non-referencier');
        this.elementRef.nativeElement.innerHTML = "non reférencié";
        break;
      case "REFERENCIE":
      this.elementRef.nativeElement.classList.add('referencier');
        this.elementRef.nativeElement.innerHTML = "reférencié";
        break;

      default: break;
    }
  }

}
