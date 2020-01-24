import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MovieDto} from '../../services/movie/movie-dto';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Service} from '../../services/service';
import {ActivatedRoute, Router} from '@angular/router';
import {MessageService} from '../../services/message/message.service';
import {MovieUpdateForm} from '../../services/forms/movieUpdateForm.interface';

@Component({
    selector: 'app-movie-edit',
    templateUrl: './movie-edit.component.html',
    styleUrls: ['./movie-edit.component.css']
})
export class MovieEditComponent implements OnInit {

    @Input() movie: MovieDto;
    @Output() runSubmitFunc: EventEmitter<MovieUpdateForm> = new EventEmitter();

    id: any;
    version: number;
    title: string;
    country: string;
    launch: number;
    category: string;

    movieDto: MovieDto;
    movieUpdateForm: FormGroup;

    constructor(private movieService: Service,
                private route: ActivatedRoute,
                private messageService: MessageService,
                private router: Router) {
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
            this.movieDto = data;
            this.version = this.movieDto.version;
            this.title = this.movieDto.title;
            this.country = this.movieDto.country;
            this.launch = this.movieDto.launch;
            this.category = this.movieDto.category;
        });
    }

    createForm(): void {
        this.movieUpdateForm = new FormGroup({
            id: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
            version: new FormControl(
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
        });
    }

    onSubmit(): void {
        if (this.movieUpdateForm.touched) {
            const id = this.movieDto.id ? this.movieUpdateForm.controls.id.value : null;
            const version = this.movieDto.title ? this.movieUpdateForm.controls.version.value : null;
            const title = this.movieUpdateForm.controls.title.touched
                ? this.movieUpdateForm.controls.title.value
                : null;
            const country = this.movieUpdateForm.controls.country.touched
                ? this.movieUpdateForm.controls.country.value
                : null;
            const launch = this.movieUpdateForm.controls.launch.touched
                ? this.movieUpdateForm.controls.launch.value
                : null;
            const category = this.movieUpdateForm.controls.category.touched
                ? this.movieUpdateForm.controls.category.value
                : null;

            this.runSubmitFunc.emit({
                id,
                version,
                title,
                country,
                launch,
                category
            });
            this.movieService.updateMovie(
                this.id,
                this.title,
                this.country,
                this.launch,
                this.category,
                this.version).subscribe(
                token => {
                    this.router.navigateByUrl('/movie');
                },
                error => {
                    this.messageService.createMessage(error);
                    throw error;
                }
            );
        }
    }
}

