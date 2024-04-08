import { Component, Output, EventEmitter } from '@angular/core';
import { DogService } from '../services/dog.service';

@Component({
  selector: 'app-add-dog-form',
  templateUrl: './add-dog-form.component.html',
  styleUrls: ['./add-dog-form.component.css']
})
export class AddDogFormComponent {
  @Output() dogAdded = new EventEmitter<any>();
  show = false; // Control visibility with this
  newDog = { name: '', breedName: '', age: 0, weight: 0, height: 0, imageURL: '' };

  constructor(private dogService: DogService) {}

  openModal(): void { this.show = true; }
  closeModal(): void { this.show = false; }

  addDog(): void {
    this.dogService.addDogForUser(this.newDog).subscribe(
      response => { this.dogAdded.emit(response); this.closeModal(); },
      error => console.error('Error adding dog:', error)
    );
  }
}
