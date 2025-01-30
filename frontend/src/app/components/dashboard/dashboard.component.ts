import { DatePipe, NgForOf, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GithubService } from '../../service/github.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  imports: [
    FormsModule,
    DatePipe,
    NgIf,
    NgForOf
  ]
})
export class DashboardComponent {
  username: string = '';
  events: any[] = [];
  loading: boolean = false;
  error: string | null = null;

  constructor(private githubService: GithubService) {}

  async fetchEvents() {
    if (!this.username) return;
    this.loading = true;
    this.error = null;

    try {
      this.events = await this.githubService.getUserEvents(this.username);
    } catch (error) {
      this.error = "Erro ao buscar eventos do usuário";
    } finally {
      this.loading = false;
    }
  }
}
