import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object // Inject PLATFORM_ID
  ) {}

  canActivate(): boolean {
    // Check if running on the browser
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('token');
      if (token) {
        return true; // User is logged in, allow route access
      } else {
        this.router.navigate(['/']); // No token found, redirect to login
        return false;
      }
    } else {
      // Not running in a browser, handle accordingly (e.g., assume not authenticated)
      return false;
    }
  }
}
