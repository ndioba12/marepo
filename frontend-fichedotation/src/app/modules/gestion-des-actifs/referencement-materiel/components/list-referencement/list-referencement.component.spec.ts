import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListReferencementComponent } from './list-referencement.component';

describe('ListReferencementComponent', () => {
  let component: ListReferencementComponent;
  let fixture: ComponentFixture<ListReferencementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListReferencementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListReferencementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
