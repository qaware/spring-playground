import {Component, OnInit} from '@angular/core';
import {Message} from '../services/message/message.mode';
import {MessageService} from '../services/message/message.service';

@Component({
    selector: 'app-message',
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

    messages: Message[] = [];

    constructor(private messageService: MessageService) {
    }

    ngOnInit() {
        this.messageService.errorOccurred.subscribe((message) => {
            this.displayMessage(message);
        });
    }

    displayMessage(message) {
        this.messages.push(message);
        console.log(this.messages);
        setTimeout(() => {
            this.messages = this.messages.filter((m) => m !== message);
        }, 10000);
    }

    close(message) {
        this.messages = this.messages.filter((m) => m !== message);
    }
}
