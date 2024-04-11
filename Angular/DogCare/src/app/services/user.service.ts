import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private userId: number | null = null;
  

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private router: Router // Inject the Router here
  ) {
    // Check if running on the browser platform to safely access localStorage
    if (isPlatformBrowser(this.platformId)) {
      const userID = localStorage.getItem('userID');
      this.userId = userID ? Number(userID) : null;
    }
  }

  getUserId(): number | null {
    if (this.userId)
    return this.userId;
    return null;
    
  }

  setUserId(userId: number): void {
    if (isPlatformBrowser(this.platformId)) {
      this.userId = userId;
      localStorage.setItem('userID', userId.toString());
    }   
  }

  getUserEmailbyId(): string {
    if (isPlatformBrowser(this.platformId)) {
      // Retrieve the email from local storage
      const userEmail = localStorage.getItem('token');
      return userEmail ? userEmail : ''; // Return empty string if email not found
    } else {
      return '';
    }
  }

  clearUserId(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.userId = null;
      localStorage.removeItem('userID');
      this.router.navigate(['']);
    }
  }
}
