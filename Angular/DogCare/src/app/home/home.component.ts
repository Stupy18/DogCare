import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { DogService } from '../services/dog.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  dogs: any[] = [];
  userId: number | null = null; // Initialize userId to null

  constructor(
    private dogService: DogService, 
    private router: Router, 
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      this.userId = this.getUserId();
      console.log('User ID:', this.userId); // Log the user ID for debugging
      if (this.userId) {
        this.dogService.getDogsByUserId(this.userId).subscribe(
          data => {
            console.log('Dogs data:', data); // Log the data for debugging
            this.dogs = data;
          },
          error => {
            console.error('There was an error fetching dogs:', error);
          }
        );
      } 
    }
  }
  
  getUserId(): number | null {
    // Get the user ID directly, without parsing it as JSON, since it's not an object.
    const userID = localStorage.getItem('userID');
    if (userID) {
      return Number(userID); // Convert to number if necessary
    }
    console.log('No user data in local storage, returning null.');
    return null;
  }

  onAddDog() {
    // Placeholder for add dog functionality
    console.log('Add Dog button clicked');
  }
}  