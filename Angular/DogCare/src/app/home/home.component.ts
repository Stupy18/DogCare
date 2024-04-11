import { Component, OnInit, ViewChild } from '@angular/core';
import { DogService } from '../services/dog.service';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { AddDogFormComponent } from '../add-dog-form/add-dog-form.component';
import { AddActivityComponent } from '../add-activity/add-activity.component';
import { PlannedActivitiesButtonComponent } from '../planned-activities-button/planned-activities-button.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  dogs: any[] = [];
  showAddDogModal: boolean = false;
  selectedDog: any = null;
  showAddActivity = false;
  @ViewChild(AddDogFormComponent) addDogForm!: AddDogFormComponent;
  @ViewChild(AddActivityComponent) addActivityComponent!: AddActivityComponent;
  @ViewChild(PlannedActivitiesButtonComponent) plannedActivitiesButton!: PlannedActivitiesButtonComponent;

  constructor(
    private dogService: DogService,
    private userService: UserService, // Inject UserService
    private router: Router,

  ) {}

  logout(): void {
    this.router.navigateByUrl('');
  }

  ngOnInit(): void {
    const userId = this.userService.getUserId(); // Use UserService to get the user ID
    console.log(this.showAddDogModal);
  
    if (!userId) {
      // If no user ID found, redirect to login
      console.log('No user ID found, redirecting to login.');
      this.router.navigateByUrl(''); // Redirect to the login page
    } else {
      // If a user ID is found, proceed to fetch dogs for the user
      console.log('User ID:', userId); // Log the user ID for debugging
      console.log('Fetching dogs for user ID:', userId); // Log fetching attempt
      this.fetchDogs(userId);
    }
  }

  fetchDogs(userId: number): void {
    this.dogService.getDogsByUserId().subscribe({
      next: (dogs) => {
        console.log('Dogs fetched:', dogs); // Log fetched dogs
        this.dogs = dogs;
      },
      error: (error) => console.error('Error fetching dogs:', error),
    });
  }

  selectDog(dog: any): void {
    this.selectedDog = dog;
  }

  onAddDog(): void {
    console.log('Opening Add Dog modal.'); // Log modal opening
    this.showAddDogModal = true; // Show the modal
    console.log(this.showAddDogModal);
  }

  closeModal(): void {
    this.showAddDogModal = false; // Close the modal, called from the child component
  }
  openAddActivityModal(): void {
    this.addActivityComponent.show = true;
  }

  handleDogAdded(dog: any): void {
    this.dogs.push(dog)
    console.log('Dog added:', dog); 
    this.dogs.push(dog)// Log the added dog
    this.closeModal() // Hide the modal
    const userId = this.userService.getUserId();
    if (userId) {
      console.log('Refreshing dogs list for user ID:', userId); // Log the refresh attempt
      this.fetchDogs(userId); // Refresh the list of dogs
    }
  }

  onEditDog(dog: any): void {
    const userId = this.userService.getUserId();
  
    // Check if userId is not null
    if (userId === null) {
      console.error('User ID not found');
      return;
    }
  
    // Ask the user which attribute they want to edit
    const attributeToEdit = prompt('Which attribute would you like to edit? (name/age/weight/height)');
    if (!attributeToEdit) {
      console.log('Edit operation cancelled.');
      return;
    }
  
    // Ask for the new value for the chosen attribute
    let newValue = prompt(`Enter new value for ${attributeToEdit}:`, dog[attributeToEdit]);
    if (!newValue) {
      console.log('Edit operation cancelled.');
      return;
    }
    
    // If the attribute to edit is 'age', 'weight', or 'height', convert it to a number
    let numericValue = null;
    if (['age', 'weight', 'height'].includes(attributeToEdit) && newValue) {
      numericValue = Number(newValue);
    if (isNaN(numericValue)) {
      console.error('Please enter a valid number.');
      return;
  }
}
  
    // Proceed with the update if the value has changed
    if (dog[attributeToEdit] !== newValue) {
      this.dogService.getDogByUserIdAndName(userId, dog.name).subscribe(dogId => {
        if (dogId) {
          const updatedDog = { ...dog, [attributeToEdit]: newValue };
          this.dogService.updateDog(dogId, updatedDog).subscribe({
            next: () => {
              // Update the dog in your local 'dogs' array to reflect the new attribute value
              this.dogs = this.dogs.map(d => d.id === dog.id ? updatedDog : d);
              this.selectedDog = null; // Close the details modal
              console.log('Dog successfully updated');
            },
            error: error => console.error('Error updating dog:', error)
          });
        } else {
          console.error('Dog ID not found for editing');
        }
      });
    } else {
      console.log('No changes made.');
    }
    // this.fetchDogs(userId);
    window.location.reload(); // Reload the page to reflect the change
  }
  

  onDeleteDog(dog: any): void {
    const userId = this.userService.getUserId();
    if (!userId) {
      console.error('User ID not found');
      return;
    }
  
    if (!dog || !dog.name) {
      console.error('Dog name not provided');
      return;
    }
  
    const dogName = dog.name; // Ensure this is a string
  
    this.dogService.getDogByUserIdAndName(userId, dogName).subscribe({
      next: (dogId) => {
        if (dogId) {
          this.dogService.deleteDog(dogId).subscribe({
            next: () => {
              this.dogs = this.dogs.filter(d => d.name !== dogName);
              console.log('Dog successfully deleted');
              this.selectedDog = null; // This line closes the pop-up
            },
            error: (error) => {
              console.error('Error deleting the dog:', error);
            }
          });
        } else {
          console.log('Dog not found:', dogName);
        }
      },
      error: (error) => {
        console.error('Error fetching dog ID by name:', error);
      }
    });
}

}