import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditRecuperationComponent } from './edit-recuperation.component';

describe('EditRecuperationComponent', () => {
  let component: EditRecuperationComponent;
  let fixture: ComponentFixture<EditRecuperationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditRecuperationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditRecuperationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
