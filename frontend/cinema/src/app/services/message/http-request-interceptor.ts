import {Injectable} from '@angular/core';

import {
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpRequest,
    HttpErrorResponse,
} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Router} from '@angular/router';
import {catchError, tap} from 'rxjs/operators';
import {MessageService} from './message.service';


@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
    constructor(private router: Router, private messageService: MessageService) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('message');
        return next.handle(req).pipe(tap(() => {}), catchError(err => this.handleError(err)));
    }

    private handleError(err: HttpErrorResponse): Observable<any> {
        if (err.status === 409) {
            console.log('Caught 409 error, redirect to movie overview');
            this.messageService.createMessage(err);
            return of(err.message);
        }
        window.location.href = '/movie';
    }
}
