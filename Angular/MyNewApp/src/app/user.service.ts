import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/Rx';
import { User } from "app/user";
import { Tweet } from "app/tweet";

@Injectable()
export class UserService {
  private baseUrl: string = 'http://localhost:8080/Kwetter/api';
  constructor(private http: Http) { }

  private getHeaders() {
    let headers = new Headers()
    headers.append('Accept', 'application/json');
    return headers;
  }
  
  private getPostHeaders() {
    let headers = new Headers()
    headers.append('Content-Type', 'application/json');
    return headers;
  }

  getUser(name: string): Observable<User>{
    console.log(name);
    let user$ = this.http
      .get(`${this.baseUrl}/user/` + name, { headers: this.getHeaders() })
      .map(mapUser);
    return user$;
  }

  getAll(): Observable<User[]> {
    console.log("test");
    console.log(this.http
      .get(`${this.baseUrl}/user`, { headers: this.getHeaders() }));
    let users$ = this.http
      .get(`${this.baseUrl}/user`, { headers: this.getHeaders() })
      .map(mapUsers);
    return users$;
  }

  getTweets(id: number): Observable<Tweet[]> {
    console.log("ay");
    console.log(this.http
      .get(`${this.baseUrl}/tweet/` + id + `/user`, { headers: this.getHeaders() }));
    let tweets$ = this.http
      .get(`${this.baseUrl}/tweet/` + id + `/user`, { headers: this.getHeaders() })
      .map(mapTweets);
    return tweets$;
  }

  getFollowers(id : number): Observable<User[]>{
    console.log("followers");
    console.log(this.http
      .get(`${this.baseUrl}/user/followers/` + id, { headers: this.getHeaders() }));
    let users$ = this.http
      .get(`${this.baseUrl}/user/followers/` + id, { headers: this.getHeaders() })
      .map(mapUsers);
    return users$;
  }

  getFollowing(id : number): Observable<User[]>{
    console.log("following");
    console.log(this.http
      .get(`${this.baseUrl}/user/following/` + id, { headers: this.getHeaders() }));
    let users$ = this.http
      .get(`${this.baseUrl}/user/following/` + id, { headers: this.getHeaders() })
      .map(mapUsers);
    return users$;
  }

  postTweet(id: number, message: string): Observable<boolean> {
    console.log(id + " " + message);
    return this.http.post(`${this.baseUrl}/tweet/` + id + '/' + message,{ headers: this.getPostHeaders() }).map(r => {
      console.log(r);
      return true;
    });
  }
}

function mapUsers(response: Response): User[] {
  // extracts a list of entities from the Response
  return response.json()
    .map(toUser);
}


function mapTweets(response: Response): Tweet[] {
  // extracts a list of entities from the Response
  return response.json()
    .map(toTweet);
}

function mapUser(response: Response): User {
  // extracts one entity from the response
  return toUser(response.json());
}

function toUser(data: any): User {
  // converts JSON data to specifeid entity
  let User = <User>({
    id: data.id,
    username: data.userName,
    bio: data.bio,
    location: data.location,
    website: data.website
  });
  console.log('Parsed entity:', User);
  return User;
}


function toTweet(data: any): Tweet {
  // converts JSON data to specifeid entity
  let Tweet = <Tweet>({
    id: data.id,
    tweetText : data.text,
    tweetTimestamp : data.tweetTimestamp
  });
  console.log('Parsed entity:', Tweet);
  return Tweet;
}