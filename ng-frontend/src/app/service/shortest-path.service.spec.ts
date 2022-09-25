import { TestBed } from '@angular/core/testing';

import { ShortestPathService } from './shortest-path.service';

describe('ShortestPathService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShortestPathService = TestBed.get(ShortestPathService);
    expect(service).toBeTruthy();
  });
});
