import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-planned-activities-button',
  templateUrl: './planned-activities-button.component.html',
  styleUrls: ['./planned-activities-button.component.css']
})
export class PlannedActivitiesButtonComponent implements OnInit {
  showPopup: boolean = false;
  activities: any[] = [];
  userDogs: any[] = [];

  constructor(
    private http: HttpClient,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    // Removed fetchUserDogs from here
  }

  showPlannedActivities(): void {
    const userId = this.userService.getUserId();
    if (!userId) {
      console.error('User ID not found');
      return;
    }

    if (this.userDogs.length === 0) {
      this.fetchUserDogs();
    } else {
      this.showPopup = true;
    }
  }

  fetchUserDogs(): void {
    const userId = this.userService.getUserId(); 
    this.http.get<any[]>(`http://localhost:8080/dogs/user/${userId}`).subscribe(dogs => {
      this.userDogs = dogs;
      this.fetchActivities(); // Fetch activities after fetching dogs
    }, error => console.error('Error fetching dogs:', error));
  }

  fetchActivities(): void {
    this.http.get<any[]>('http://localhost:8080/activity-logs').subscribe(activities => {
      // Now, you can filter the activities per dog and add them to each dog
      this.userDogs.forEach(dog => {
        dog.activities = activities.filter(a => a.dog.dogID === dog.dogID);
      });
      this.showPopup = true; // Show popup after filtering activities
    }, error => console.error('Error fetching activities:', error));
  }

  closePopup(): void {
    this.showPopup = false;
  }
}
