import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {MovieDto} from './movie/movie-dto';
import {ActorDto} from './actor/actor-dto';

@Injectable()
export class Service {

    constructor(private http: HttpClient) {

    }

    public getAllMovies() {
        console.log('Hey Service did you get here?');
        return this.http.get<MovieDto[]>('/cinema/api/movie');
    }

    public addNewMovieToDatabase(title: string, country: string, launch: number, category: string, version: any) {
        console.log(title);
        console.log(country);
        console.log(launch);
        console.log(category);
        console.log('Hey Service did you receive the post?');
        return this.http.post('/cinema/api/movie/add', {title, country, launch, category, version});
    }

    public getMovie(id: string) {
        console.log('did you get to the findMovieByIdService?');
        return this.http.get<MovieDto>('/cinema/api/movie/find/' + id);
    }

    public deleteMovieById(id: string) {
        console.log();
        return this.http.delete('/cinema/api/movie/delete/' + id);
    }

    public updateMovie(id: string, title: string, country: string, launch: number, category: string, version: number) {
        console.log(id);
        console.log(title);
        console.log(country);
        console.log(launch);
        console.log(category);
        console.log('Hey Service did you receive the update?');
        return this.http.post('/cinema/api/movie/update/' + id, {id, title, country, launch, category, version});
    }

    public addVoteToMovie(movieId: any, vote: number) {
        console.log('Did you get to the addVoteToMovie in the service?');
        console.log(movieId);
        console.log(vote);
        return this.http.post('/cinema/api/movie/vote/' + movieId, {vote});
    }

    public addCommentToMovie(movieId: any, comment: string) {
        console.log(movieId);
        console.log(comment);
        return this.http.post('/cinema/api/movie/comment/' + movieId, {comment});
    }

    public getAllActors() {
        console.log('Did you get to the getAllActors in the service?');
        return this.http.get<ActorDto[]>('/cinema/api/actor');
    }

    public getActor(id: string) {
        console.log(id);
        return this.http.get<ActorDto>('/cinema/api/actor/find/' + id);
    }
}
