import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditReferencementComponent } from './edit-referencement.component';

describe('EditReferencementComponent', () => {
  let component: EditReferencementComponent;
  let fixture: ComponentFixture<EditReferencementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditReferencementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditReferencementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
