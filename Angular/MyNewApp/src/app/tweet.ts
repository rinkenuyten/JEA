import { User } from "app/user";

export class Tweet {

    id: number;
    tweetText : String;
    tweetTimestamp: String;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}
