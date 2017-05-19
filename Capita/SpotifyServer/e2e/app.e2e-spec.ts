import { SpotifyServerPage } from './app.po';

describe('spotify-server App', () => {
  let page: SpotifyServerPage;

  beforeEach(() => {
    page = new SpotifyServerPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
