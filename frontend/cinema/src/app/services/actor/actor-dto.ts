export class ActorDto {

    id: any;
    name: string;
    age: number;
    movies = [];

    constructor(id, name, age, movies) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.movies = movies;
    }
}
