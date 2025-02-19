import { Injectable } from '@angular/core';
import Axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class GithubService {
  private API_URL = "http://localhost:8080"

  constructor() { }

  async getUserEvents(username: string): Promise<any> {
    try {
      const response = await Axios.get(`${this.API_URL}/${username}/events`);
      return response.data;
    } catch (error) {
      console.error("Error:", error)
      throw error;
    }
  }
}
