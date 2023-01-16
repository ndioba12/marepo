import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReferencementComponent } from './add-referencement.component';

describe('AddReferencementComponent', () => {
  let component: AddReferencementComponent;
  let fixture: ComponentFixture<AddReferencementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddReferencementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddReferencementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
