import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Service} from '../../services/service';
import {ActorDto} from '../../services/actor/actor-dto';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {ActorDetailForm} from '../../services/forms/actorDetailForm.interface';


@Component({
    selector: 'app-actor-detail',
    templateUrl: './actor-detail.component.html',
    styleUrls: ['./actor-detail.component.css']
})
export class ActorDetailComponent implements OnInit {

    @Input() actor: ActorDto[];
    @Output() runSubmitFunc: EventEmitter<ActorDetailForm> = new EventEmitter();

    defaultImage = 'assets/img/actorImage_default.png';

    id: any;
    name: string;
    age: number;
    movies = [];

    actorDto: ActorDto;
    actorDetailForm: FormGroup;

    constructor(private movieService: Service, private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.getData();
        this.createForm();
    }

    getData() {
        this.getId();
        this.getActor();
    }


    getId(): void {
        this.route.params.subscribe(params => {
            this.id = +params.id;
            console.log(this.id);
        });
    }

    getActor(): void {
        this.movieService.getActor(this.id).subscribe(data => {
            console.log(this.id);
            this.actorDto = data;
            this.name = this.actorDto.name;
            this.age = this.actorDto.age;
            this.movies = this.actorDto.movies;
        });
    }

    createForm(): void {
        this.actorDetailForm = new FormGroup({
            id: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
            name: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
            age: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
            movies: new FormControl(
                {value: '', disabled: true},
                {
                    validators: [Validators.required],
                }
            ),
        });
    }

    onSubmit(): void {
        if (this.actorDetailForm.touched) {
            const id = this.actorDto.id ? this.actorDetailForm.controls.id.value : null;
            const name = this.actorDto.name ? this.actorDetailForm.controls.name.value : null;
            const age = this.actorDto.age ? this.actorDetailForm.controls.age.value : null;
            const movies = this.actorDetailForm.controls.movies.touched
                ? this.actorDetailForm.controls.movies.value
                : null;

            this.runSubmitFunc.emit({
                id,
                name,
                age,
                movies
            });
        }
    }
}
