import { TestBed, inject } from '@angular/core/testing';

import { TweetService } from './tweet.service';

describe('TweetService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TweetService]
    });
  });

  it('should ...', inject([TweetService], (service: TweetService) => {
    expect(service).toBeTruthy();
  }));
});
