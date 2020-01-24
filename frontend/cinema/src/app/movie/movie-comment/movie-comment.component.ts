import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MovieDto} from '../../services/movie/movie-dto';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Service} from '../../services/service';
import {ActivatedRoute, Router} from '@angular/router';
import {MessageService} from '../../services/message/message.service';
import {MovieCommentForm} from '../../services/forms/movieCommentForm.interface';

@Component({
    selector: 'app-movie-comment',
    templateUrl: './movie-comment.component.html',
    styleUrls: ['./movie-comment.component.css']
})
export class MovieCommentComponent implements OnInit {

    @Input() movie: MovieDto;
    @Output() runSubmitFunc: EventEmitter<MovieCommentForm> = new EventEmitter();

    id: any;
    title: string;
    comment: string;
    movieDto: MovieDto;
    movieCommentForm: FormGroup;

    constructor(private movieService: Service, private route: ActivatedRoute,
                private messageService: MessageService, private router: Router) {
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
            this.title = this.movieDto.title;
        });
    }

    createForm(): void {
        this.movieCommentForm = new FormGroup({
            id: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
            title: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
            comment: new FormControl('', {
                validators: [Validators.required],
            }),
        });
    }

    onSubmit(): void {
        if (this.movieCommentForm.touched) {
            const id = this.movieDto.id ? this.movieCommentForm.controls.id.value : null;
            const title = this.movieDto.title ? this.movieCommentForm.controls.title.value : null;
            const comment = this.movieCommentForm.controls.comment.touched
                ? this.movieCommentForm.controls.comment.value
                : null;

            this.runSubmitFunc.emit({
                id,
                title,
                comment
            });

            this.movieService.addCommentToMovie(this.id, this.comment).subscribe(
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
