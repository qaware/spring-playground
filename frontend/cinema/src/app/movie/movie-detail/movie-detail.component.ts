import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Service} from '../../services/service';
import {MovieDto} from '../../services/movie/movie-dto';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {MovieDetailForm} from '../../services/forms/movieDetailForm.interface';
import {ActorDto} from '../../services/actor/actor-dto';

@Component({
    selector: 'app-movie-detail',
    templateUrl: './movie-detail.component.html',
    styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent implements OnInit {

    @Input() movie: MovieDto;
    @Output() runSubmitFunc: EventEmitter<MovieDetailForm> = new EventEmitter();

    defaultImage = 'assets/img/actorImage_default.png';

    id: any;
    title: string;
    comments = [];
    actors: ActorDto[];
    movieDto: MovieDto;
    movieDetailForm: FormGroup;

    constructor(private movieService: Service,
                private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.getData();
        this.createForm();
    }

    getData() {
        this.getId();
        this.getMovie();
    }

    getId(): void {
        this.route.params.subscribe(params => {
            this.id = +params.id;
        });
    }

    getMovie(): void {
        this.movieService.getMovie(this.id).subscribe(data => {
            console.log('Get Movie');
            this.movieDto = data;
            console.log(this.movieDto.actors);
            this.title = this.movieDto.title;
            this.comments = this.movieDto.comments;
            this.actors = this.movieDto.actors;
        });
    }

    createForm(): void {
        this.movieDetailForm = new FormGroup({
            title: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
        });
    }

    onSubmit(): void {
        if (this.movieDetailForm.touched) {
            const title = this.movieDto.id ? this.movieDetailForm.controls.title.value : null;

            this.runSubmitFunc.emit({
                title
            });
        }
    }
}
