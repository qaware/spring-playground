import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {MovieComponent} from './movie/movie-overview/movie.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {MovieAddComponent} from './movie/movie-add/movie-add.component';
import {MovieEditComponent} from './movie/movie-edit/movie-edit.component';
import {MovieVoteComponent} from './movie/movie-vote/movie-vote.component';
import {MovieCommentComponent} from './movie/movie-comment/movie-comment.component';
import {MovieDetailComponent} from './movie/movie-detail/movie-detail.component';
import {ActorDetailComponent} from './actor/actor-detail/actor-detail.component';
import {ActorOverviewComponent} from './actor/actor-overview/actor-overview.component';

const routes: Routes = [
    {
        path: '',
        component: WelcomeComponent,
    },
    {
        path: 'cinema',
        component: WelcomeComponent,
    },
    {
        path: 'movie',
        component: MovieComponent
    },
    {
        path: 'movie-add',
        component: MovieAddComponent
    },
    {
        path: 'movie-detail/movie/:id',
        component: MovieDetailComponent
    },
    {
        path: 'movie-edit/movie/:id',
        component: MovieEditComponent
    },
    {
        path: 'movie-vote/movie/:id',
        component: MovieVoteComponent
    },
    {
        path: 'movie-comment/movie/:id',
        component: MovieCommentComponent
    },
    {
        path: 'actor',
        component: ActorOverviewComponent
    },
    {
        path: 'actor-detail/:id',
        component: ActorDetailComponent
    },
    {
        path: '**',
        redirectTo: '',
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
    exports: [RouterModule],
    providers: []
})
export class AppRoutingModule {
}
