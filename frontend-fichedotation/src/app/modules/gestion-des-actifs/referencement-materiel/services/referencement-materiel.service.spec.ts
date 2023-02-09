import { TestBed } from '@angular/core/testing';

import { ReferencementMaterielService } from './referencement-materiel.service';

describe('ReferencementMaterielService', () => {
  let service: ReferencementMaterielService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReferencementMaterielService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
