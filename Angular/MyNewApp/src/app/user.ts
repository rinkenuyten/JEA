export class User {
    id : number;
    username: string = '';
    bio: string = '';
    location: string = '';
    website: string = '';


    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}
