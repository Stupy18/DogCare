import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from './user.service'; // Import UserService

@Injectable({
  providedIn: 'root'
})
export class DogService {

  constructor(
    private http: HttpClient,
    private userService: UserService // Inject UserService
  ) {}

  getDogsByUserId(): Observable<any[]> {
    // Use the getUserId method from UserService to get the current user's ID
    const userId = this.userService.getUserId();
    if (!userId) {
      throw new Error('User ID not found');
    }
    return this.http.get<any[]>(`http://localhost:8080/dogs/user/${userId}`);
  }

  addDogForUser(dogData: any): Observable<any> {
    // Use the getUserId method from UserService to get the current user's ID
    const userId = this.userService.getUserId();
    if (!userId) {
      throw new Error('User ID not found');
    }
    return this.http.post<any>(`http://localhost:8080/dogs/user/${userId}`, dogData);
  }
  
  // If you need the getUserId method in DogService for some reason:
  getUserId(): number | null {
    return this.userService.getUserId();
  }
}
