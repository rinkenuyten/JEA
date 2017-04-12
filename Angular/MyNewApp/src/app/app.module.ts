import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http } from '@angular/http';
import { RouterModule } from '@angular/router';
import { UserComponent } from "app/user/user.component";
import { routes } from "app/app.routes";
import { AppComponent } from "app/app.component";
import { TestComponent } from './test/test.component';
import { TweetComponent } from './tweet/tweet.component';
import { TranslateModule } from "ng2-translate";
import 'ng2-translate';
import { TranslateStaticLoader, TranslateLoader } from "ng2-translate";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    TestComponent,
    TweetComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    TranslateModule.forRoot(),
    RouterModule.forRoot(routes, {
      useHash: true
    })
  ],
  providers: [UserComponent, TestComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
