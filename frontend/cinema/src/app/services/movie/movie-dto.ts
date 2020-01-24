import {ActorDto} from '../actor/actor-dto';

export class MovieDto {

    id: string;
    title: string;
    country: string;
    launch: number;
    category: string;
    version: number;
    averageVote: number;
    comments: string[] = [];
    actors: ActorDto[] = [];

    constructor(id, title, country, launch, category, version, averageVote, comments, actors) {
        this.id = id;
        this.title = title;
        this.country = country;
        this.launch = launch;
        this.category = category;
        this.version = version;
        this.averageVote = averageVote;
        this.comments = comments;
        this.actors = actors;
    }
}

