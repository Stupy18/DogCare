import { Component, Output, EventEmitter } from '@angular/core';
import { DogService } from '../services/dog.service';
import { HttpClient } from '@angular/common/http';
import { HomeComponent } from '../home/home.component';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-add-dog-form',
  templateUrl: './add-dog-form.component.html',
  styleUrls: ['./add-dog-form.component.css']
})
export class AddDogFormComponent {
  @Output() dogAdded = new EventEmitter<any>();
  show = false; // Control visibility with this
  newDog = { name: '', breedName: '', age: 0, weight: 0, height: 0, imageURL: '' };
  breeds: any[] = [];

  constructor(private http: HttpClient,private dogService: DogService) {}

  ngOnInit(): void {
    this.fetchBreeds();
    this.onBreedChange();
  }

  onBreedChange() {
    const selectedBreed = this.breeds.find(breed => breed.name === this.newDog.breedName);
    if (selectedBreed && selectedBreed.reference_image_id) {
      // Construct the image URL directly from the reference_image_id
      this.newDog.imageURL = `https://cdn2.thedogapi.com/images/${selectedBreed.reference_image_id}.jpg`;
    } else {
      // Reset the imageURL if no breed is selected or if the breed has no reference_image_id
      this.newDog.imageURL = '';
    }
  }


  fetchBreeds() {
    this.http.get<any[]>('https://api.thedogapi.com/v1/breeds', {
      headers: { 'x-api-key': 'live_StlZNPMyGC31fefMQBBrCEgXSc0hcVJbCM7O7UAGbbw5uMRj9DB2zAgpzho2mSkF' }
    }).subscribe(breeds => {
      this.breeds = breeds;
      // Optionally set a default breed and image here
    });
  }

  openModal(): void { this.show = true; }
  closeModal(): void { this.show = false; }

  addDog(): void {
    // Check if any of the required fields are empty, undefined, or 0
    if (!this.newDog.name.trim() ||
        !this.newDog.breedName.trim() ||
        this.newDog.age <= 0 ||
        this.newDog.weight <= 0 ||
        this.newDog.height <= 0) {
      alert('All attributes must be completed!');
      return; // Prevent the form submission
    }
  
    // Proceed with the API call if validation passes
    this.dogService.addDogForUser(this.newDog).subscribe(
      response => {
        this.dogAdded.emit(response); // Notify parent component or other listeners
        this.closeModal(); // Close the modal form
        window.location.reload(); // Optionally reload the page to reflect the change
      },
      error => console.error('Error adding dog:', error)
    );
  }
  
  
}