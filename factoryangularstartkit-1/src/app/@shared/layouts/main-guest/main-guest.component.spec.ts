import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MainGuestComponent} from './main-guest.component';

describe('MainGuestComponent', () => {
  let component: MainGuestComponent;
  let fixture: ComponentFixture<MainGuestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MainGuestComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MainGuestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
