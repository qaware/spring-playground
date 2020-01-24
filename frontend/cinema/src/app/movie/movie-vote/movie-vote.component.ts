import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MovieDto} from '../../services/movie/movie-dto';
import {Service} from '../../services/service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MessageService} from '../../services/message/message.service';
import {MovieVoteForm} from '../../services/forms/movieVoteForm.interface';

@Component({
    selector: 'app-movie-vote',
    templateUrl: './movie-vote.component.html',
    styleUrls: ['./movie-vote.component.css']
})
export class MovieVoteComponent implements OnInit {

    @Input() movie: MovieDto;
    @Output() runSubmitFunc: EventEmitter<MovieVoteForm> = new EventEmitter();

    id: any;
    title: string;
    vote: number;
    votes = [1, 2, 3, 4, 5];

    movieDto: MovieDto;
    movieVoteForm: FormGroup;

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
        this.movieVoteForm = new FormGroup({
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
            vote: new FormControl('', {
                validators: [Validators.required],
            }),
        });
    }

    onSubmit(): void {
        if (this.movieVoteForm.touched) {
            const id = this.movieDto.id ? this.movieVoteForm.controls.id.value : null;
            const title = this.movieDto.title ? this.movieVoteForm.controls.title.value : null;
            const vote = this.movieVoteForm.controls.vote.touched
                ? this.movieVoteForm.controls.vote.value
                : null;

            this.runSubmitFunc.emit({
                id,
                title,
                vote
            });
            this.movieService.addVoteToMovie(this.id, this.vote).subscribe(
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
