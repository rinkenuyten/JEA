import { Component } from '@angular/core';
import { User } from "app/user";
import { UserService } from "app/user.service";
import {TranslateService} from 'ng2-translate';
import 'ng2-translate';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css']
})
export class AppComponent {
  title = 'app works!'

  constructor(private translate: TranslateService) {
    translate.addLangs(['en', 'nl']);
    translate.setDefaultLang('en');
    translate.use('en');
  }
  changeLang(lang: string) {
    this.translate.use(lang);
  }
}
