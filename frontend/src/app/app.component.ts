import {DatePipe, NgForOf, NgIf} from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { GithubService } from './service/github.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    DatePipe,
    FormsModule
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string = 'GitTrack';
  myUsername: string = "<Cunegundes />"
  username: string = '';
  loading: boolean = false;
  error: string = '';
  events: any[] = [];
  groupedEvents: { repoName: string, events: any[] }[] = [];

  @ViewChild('resultsList')
  resultsList!: ElementRef;

  constructor(private githubService: GithubService) {}

  fetchEvents() {
    this.loading = true;
    this.error = '';
    this.githubService.getUserEvents(this.username).then(
      (data: any[]) => {
        this.events = data;
        this.groupEventsByRepo();
        this.loading = false;
        this.scrollToResults();
      }
    ).catch(
      (error: any) => {
        this.error = 'Github account not found';
        this.loading = false;
      }
    );
  }

  groupEventsByRepo() {
    const grouped = this.events.reduce((acc, event) => {
      const repoName = event.repo.name;
      if (!acc[repoName]) {
        acc[repoName] = [];
      }
      acc[repoName].push(event);
      return acc;
    }, {});

    this.groupedEvents = Object.keys(grouped).map(repoName => ({
      repoName,
      events: grouped[repoName]
    }));
  }

  scrollToResults() {
    if (this.resultsList) {
      this.resultsList.nativeElement.scrollIntoView({ behavior: 'smooth' });
    }
  }
}
