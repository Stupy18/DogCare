import { Component, OnInit, ViewChild } from '@angular/core';
import { DogService } from '../services/dog.service';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { AddDogFormComponent } from '../add-dog-form/add-dog-form.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  dogs: any[] = [];
  showAddDogModal: boolean = false;
  @ViewChild(AddDogFormComponent) addDogForm!: AddDogFormComponent;

  constructor(
    private dogService: DogService,
    private userService: UserService, // Inject UserService
    private router: Router,

  ) {}

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

  onAddDog(): void {
    console.log('Opening Add Dog modal.'); // Log modal opening
    this.showAddDogModal = true; // Show the modal
    console.log(this.showAddDogModal);
  }

  closeModal(): void {
    this.showAddDogModal = false; // Close the modal, called from the child component
  }

  handleDogAdded(dog: any): void {
    console.log('Dog added:', dog); // Log the added dog
    this.closeModal() // Hide the modal
    const userId = this.userService.getUserId();
    if (userId) {
      console.log('Refreshing dogs list for user ID:', userId); // Log the refresh attempt
      this.fetchDogs(userId); // Refresh the list of dogs
    }
  }
}
