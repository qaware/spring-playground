import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {Message} from './message.mode';

@Injectable({
    providedIn: 'root'
})
export class MessageService {
    errorOccurred = new Subject();

    constructor() {
    }

    displayMessage(message: Message) {
        this.errorOccurred.next(message);
    }

    createMessage(err): void {
        this.displayMessage({
            level: 'warning',
            title: 'Warning',
            message: 'Updating not possible because selected values were changed in the meantime!'
        });
    }
}
