import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MovieDto} from '../../services/movie/movie-dto';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Service} from '../../services/service';
import {MovieAddForm} from '../../services/forms/movieAddForm.interface';

@Component({
    selector: 'app-movie-add',
    templateUrl: './movie-add.component.html',
    styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit {

    @Input() movie: MovieDto;
    @Output() runSubmitFunc: EventEmitter<MovieAddForm> = new EventEmitter();

    id: string;
    title: string;
    country: string;
    launch: any;
    category: string;
    version: any;

    movieAddForm: FormGroup;

    constructor(private movieService: Service) {
    }

    ngOnInit() {
        this.movieAddForm = new FormGroup({
            id: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
            title: new FormControl('', {
                validators: [Validators.required],
            }),
            country: new FormControl('', {
                validators: [Validators.required],
            }),
            launch: new FormControl('', {
                validators: [Validators.required],
            }),
            category: new FormControl('', {
                validators: [Validators.required],
            }),
            version: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
        });
    }

    onSubmit(): void {
        if (this.movieAddForm.touched) {
            const id = this.movie ? this.movieAddForm.controls.id.value : null;
            const title = this.movieAddForm.controls.title.touched
                ? this.movieAddForm.controls.title.value
                : null;
            const country = this.movieAddForm.controls.country.touched
                ? this.movieAddForm.controls.country.value
                : null;
            const launch = this.movieAddForm.controls.launch.touched
                ? this.movieAddForm.controls.launch.value
                : null;
            const category = this.movieAddForm.controls.category.touched
                ? this.movieAddForm.controls.category.value
                : null;
            const version = this.movie ? this.movieAddForm.controls.id.value : null;

            this.runSubmitFunc.emit({
                id,
                title,
                country,
                launch,
                category,
                version
            });

            this.movieService.addNewMovieToDatabase(this.title, this.country, this.launch, this.category, this.version).subscribe();
            window.location.href = '/movie';
        }
    }
}
