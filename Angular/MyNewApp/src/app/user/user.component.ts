import { Component } from '@angular/core';
import { UserService } from "app/user.service";
import { User } from "app/user";
import { Tweet } from "app/tweet";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: 'user.component.html',
  styleUrls: ['user.component.css'],
  providers: [UserService]
})
export class UserComponent {

  userList: User[] = [];
  tweetList: Tweet[] = [];
  LoggedUser: User;
  isloaded: boolean = false;
  followers: User[] = [];
  following: User[] = [];

  constructor(private UserService: UserService, private route: ActivatedRoute) {
  }

  ngOnInit() {
   this.route.params.subscribe(params => {
       this.loadUser(params['username']);
    });
    console.log(this.LoggedUser);
  }

  loadUsers() {
    this.UserService.getAll()
      .subscribe(u => {
        this.userList = u;
      });
  }

  loadUser(name){
    console.log(name);
    this.UserService.getUser(name)
    .subscribe(u => {
      this.LoggedUser = u;
      this.isloaded = true;
      console.log(this.isloaded);
      this.loadTweet(u.id);
    })
  }

  loadTweet(Userid){
    this.UserService.getTweets(Userid)
    .subscribe(t => {
      this.tweetList = t;
      this.loadFollowers(Userid);
    })
  }

  loadFollowers(Userid){
    this.UserService.getFollowers(Userid)
    .subscribe(t => {
      this.followers = t;
      this.loadFollowing(Userid);
    })
  }

  loadFollowing(Userid){
    this.UserService.getFollowing(Userid)
    .subscribe(t => {
      this.following = t;
    })
  }

  postTweet(tweetMessage: string) {
    if (tweetMessage == '') return;
    console.log("tweetmessage: " + tweetMessage);
    this.UserService.postTweet(this.LoggedUser.id, tweetMessage).subscribe(r => this.loadTweet(this.LoggedUser.id));
  }

}
