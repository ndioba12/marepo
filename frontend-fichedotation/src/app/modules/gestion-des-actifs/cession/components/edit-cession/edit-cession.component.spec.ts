import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCessionComponent } from './edit-cession.component';

describe('EditCessionComponent', () => {
  let component: EditCessionComponent;
  let fixture: ComponentFixture<EditCessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditCessionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditCessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
