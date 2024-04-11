import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from './user.service'; // Import UserService

@Injectable({
  providedIn: 'root'
})
export class DogService {
  private baseUrl = 'http://localhost:8080/dogs';

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

  updateDog(dogId: number, dogData: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${dogId}`, dogData);
  }

  getDogByUserIdAndName(userId: number, name: string): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/dogs/user/${userId}/name/${name}/id`);
  }

  deleteDog(dogId: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/dogs/${dogId}`);
  }


}
