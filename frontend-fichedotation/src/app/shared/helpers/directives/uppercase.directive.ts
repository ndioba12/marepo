import {Directive, ElementRef, HostListener, Input, Optional, Renderer2} from "@angular/core";
import {NgControl} from "@angular/forms";
import {TitleCasePipe} from "@angular/common";

@Directive({
  selector: "[appUppercase]"
})
export class UppercaseDirective {
  textbox!: HTMLInputElement;
  @Input() allowNumber!: boolean;
  @Input() allowLetter!: boolean;
  @Input() allowSpecialCharacter!: string;
  @Input() isTitleCase!: boolean;

  constructor(
    private titleCasePipe: TitleCasePipe,
    private el: ElementRef,
    private renderer: Renderer2,
    @Optional() private ngControl: NgControl
  ) {
  }

  ngAfterViewInit() {
    this.textbox =
      this.el.nativeElement.tagName === "INPUT"
        ? this.el.nativeElement
        : this.el.nativeElement.querySelector("input");
  }

  @HostListener("input", ["$event"]) onInput(event: any) {
    const formattedVal = this.isTitleCase? this.titleCasePipe.transform(this.textbox.value): this.textbox.value.toUpperCase();
    if (this.ngControl) {
      this.ngControl.control!.setValue(formattedVal, {emitEvent: false});
    } else {
      this.renderer.setProperty(this.textbox, "value", formattedVal);
    }
  }
}
