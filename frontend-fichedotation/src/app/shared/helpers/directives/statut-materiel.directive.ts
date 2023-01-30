import { Directive, ElementRef, Input } from '@angular/core';

@Directive({ selector: '[appStatusMateriel]' })

export class StatutMaterielDirective {

    constructor(
        private elementRef: ElementRef
      ) { }

      @Input() set appStatusMateriel(statusMateriel: string) {
        switch (statusMateriel) {
          case "affecté":
            this.elementRef.nativeElement.classList.add('affecter');
            this.elementRef.nativeElement.innerHTML = "affecté";
            break;
          case "non reférencié":
            this.elementRef.nativeElement.classList.add('non-referencier');
            this.elementRef.nativeElement.innerHTML = "non reférencié";
            break;
          case "reférencié":
          this.elementRef.nativeElement.classList.add('referencier');
            this.elementRef.nativeElement.innerHTML = "reférencié";
            break;

          default: break;
        }
      }
}
