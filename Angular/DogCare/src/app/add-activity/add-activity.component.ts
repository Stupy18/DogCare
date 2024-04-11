import { Component, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../services/user.service';
import { DogService } from '../services/dog.service';

@Component({
  selector: 'app-add-activity',
  templateUrl: './add-activity.component.html',
  styleUrls: ['./add-activity.component.scss']
})
export class AddActivityComponent {
  @Input() show = false;
  activities: any[] = [];
  dogs: any[] = [];
  selectedActivityId: number | null = null;
  selectedDogId: number | null = null;
  notes: string = ''; // Adding a property for notes

  constructor(private http: HttpClient, private userService: UserService, private dogService: DogService) {}

  ngOnInit() {
    // const userId = this.userService.getUserId();
    // if (userId !== null) {
    //   this.fetchActivities();
    //   this.fetchDogs(userId);
    // }
    this.fetchActivities();
  }
  
  
  fetchActivities() {
    const userId = this.userService.getUserId();
    if (userId !== null) {
      this.fetchDogs(userId);
    }
    this.http.get<any[]>('http://localhost:8080/activities').subscribe(data => {
      this.activities = data;
      console.log("Fetched ActivitiesAC = ", this.activities);
      this.checkDataReady();
    });
  }
  
  fetchDogs(userId: number): void {
    this.dogService.getDogsByUserId().subscribe({
      next: (dogs) => {
        console.log('Dogs fetchedAC2:', dogs); // Log fetched dogs
        this.dogs = dogs;
        console.log("Fetched DogsAC = ", this.dogs);
        this.checkDataReady();
      },
      error: (error) => console.error('Error fetching dogs:', error),
    });
  }
  
  
  private checkDataReady() {
    if (this.dogs.length > 0 && this.activities.length > 0) {
      // Both dogs and activities are fetched, perform additional logic or enable UI components
    }
  }
  
  

  addActivity() {

    
    console.log('Selected Dog ID:', this.selectedDogId); // Log selected dog ID
    if (this.selectedActivityId == null || this.selectedDogId == null) {
      alert('Please select both an activity and a dog.');
      return;
    }
    
  
  
    // Ensure that dogs array is not empty and contains selected dog
    // if (!this.dogs || this.dogs.length === 0 || !this.dogs.find(dog => dog.dogID === this.selectedDogId)) {
    //   alert('Please select a valid dog.');
    //   return;
    // }
    
  
    const activityLogDto = {
      dogId: this.selectedDogId,
      activityId: this.selectedActivityId,
      timestamp: new Date().toISOString(),
      notes: this.notes
    };
  
    this.http.post('http://localhost:8080/activity-logs', activityLogDto).subscribe({
      next: () => {
        alert('Activity added successfully.');
        this.close();
      },
      error: (error) => {
        console.log(activityLogDto);
        console.error('Error adding activity:', error);
        alert('Failed to add activity.');
      }
    });
    window.location.reload();
  }
  
  close() {
    this.show = false;
  }
}
