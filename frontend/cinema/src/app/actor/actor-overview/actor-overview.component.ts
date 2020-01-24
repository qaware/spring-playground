import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {ActorDto} from '../../services/actor/actor-dto';
import {Service} from '../../services/service';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-actor-overview',
  templateUrl: './actor-overview.component.html',
  styleUrls: ['./actor-overview.component.css']
})
export class ActorOverviewComponent implements OnInit {

    displayedColumns = ['img', 'id', 'name', 'age', 'star'];
    defaultImage = 'assets/img/actorImage_default.png';

    dataSource = new MatTableDataSource<ActorDto>();

    constructor(private movieService: Service) {
    }

    @ViewChild(MatSort, {static: true}) sort: MatSort;
    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

    ngOnInit() {
        this.movieService.getAllActors().subscribe( data => {
                this.dataSource.data = data;
                this.dataSource.sort = this.sort;
                this.dataSource.paginator = this.paginator;
            }
        );
    }
}
