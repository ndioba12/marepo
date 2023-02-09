import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCessionComponent } from './add-cession.component';

describe('AddCessionComponent', () => {
  let component: AddCessionComponent;
  let fixture: ComponentFixture<AddCessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCessionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
