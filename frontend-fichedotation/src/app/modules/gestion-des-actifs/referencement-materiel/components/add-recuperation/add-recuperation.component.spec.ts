import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRecuperationComponent } from './add-recuperation.component';

describe('AddRecuperationComponent', () => {
  let component: AddRecuperationComponent;
  let fixture: ComponentFixture<AddRecuperationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddRecuperationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddRecuperationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
