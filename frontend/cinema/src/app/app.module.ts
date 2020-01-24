import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MovieComponent} from './movie/movie-overview/movie.component';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTableModule} from '@angular/material/table';
import {HeaderComponent} from './header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {Service} from './services/service';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {WelcomeComponent} from './welcome/welcome.component';
import {_MatMenuDirectivesModule, MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MovieEditComponent} from './movie/movie-edit/movie-edit.component';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSortModule} from '@angular/material/sort';
import {MovieAddComponent} from './movie/movie-add/movie-add.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';
import {HttpRequestInterceptor} from './services/message/http-request-interceptor';
import {MessageComponent} from './message/message.component';
import {Router} from '@angular/router';
import {MessageService} from './services/message/message.service';
import { MovieVoteComponent } from './movie/movie-vote/movie-vote.component';
import { MovieCommentComponent } from './movie/movie-comment/movie-comment.component';
import { MovieDetailComponent } from './movie/movie-detail/movie-detail.component';
import {MatListModule} from '@angular/material/list';
import {ActorDetailComponent} from './actor/actor-detail/actor-detail.component';
import { ActorOverviewComponent } from './actor/actor-overview/actor-overview.component';

@NgModule({
    declarations: [
        AppComponent,
        MovieComponent,
        HeaderComponent,
        WelcomeComponent,
        MovieEditComponent,
        MovieAddComponent,
        MessageComponent,
        MovieVoteComponent,
        MovieCommentComponent,
        MovieDetailComponent,
        ActorDetailComponent,
        ActorOverviewComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatCardModule,
        MatFormFieldModule,
        MatTableModule,
        MatToolbarModule,
        MatPaginatorModule,
        MatCheckboxModule,
        _MatMenuDirectivesModule,
        MatIconModule,
        MatMenuModule,
        MatInputModule,
        MatButtonModule,
        MatTooltipModule,
        MatSortModule,
        ReactiveFormsModule,
        MatSelectModule,
        FormsModule,
        MatListModule,
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useFactory(router: Router, messageService: MessageService) {
                return new HttpRequestInterceptor(router, messageService);
            },
            multi: true,
            deps: [Router, MessageService]
        },
        Service,
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
