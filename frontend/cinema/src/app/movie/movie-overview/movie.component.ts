import {Component, OnInit, ViewChild} from '@angular/core';
import {MovieDto} from '../../services/movie/movie-dto';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Service} from '../../services/service';
import {MatPaginator} from '@angular/material/paginator';

@Component({
    selector: 'app-movie',
    templateUrl: './movie.component.html',
    styleUrls: ['./movie.component.css']
})

export class MovieComponent implements OnInit {

    displayedColumns = ['id', 'title', 'country', 'launch', 'category', 'averageVote', 'star'];

    dataSource = new MatTableDataSource<MovieDto>();
    id: number;
    movie: MovieDto[];

    constructor(private movieService: Service) {
    }

    @ViewChild(MatSort, {static: true}) sort: MatSort;
    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

    ngOnInit() {
        this.movieService.getAllMovies().subscribe(data => {
                this.dataSource.data = data;
                this.dataSource.sort = this.sort;
                this.dataSource.paginator = this.paginator;
            }
        );
    }

    deleteMovieById(id: string) {
        this.movieService.deleteMovieById(id).subscribe();
        window.location.reload();
    }
}
