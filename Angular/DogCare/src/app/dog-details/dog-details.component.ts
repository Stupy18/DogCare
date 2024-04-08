import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dog-details',
  templateUrl: './dog-details.component.html',
  styleUrls: ['./dog-details.component.css']
})
export class DogDetailsComponent implements OnInit {
  @Input() dog: any;
  @Output() close = new EventEmitter<void>();
  @Output() edit = new EventEmitter<any>();
  @Output() delete = new EventEmitter<any>();
  breedInfo: any;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchBreedInfo(this.dog.breedName);
  }

  fetchBreedInfo(breedName: string): void {
    const url = `https://api.thedogapi.com/v1/breeds/search?q=${encodeURIComponent(breedName)}`;
    this.http.get<any[]>(url, {
      headers: { 'x-api-key': 'live_StlZNPMyGC31fefMQBBrCEgXSc0hcVJbCM7O7UAGbbw5uMRj9DB2zAgpzho2mSkF' }
    }).subscribe(breeds => {
      if (breeds.length > 0) {
        // Assuming the first result is the most relevant
        this.breedInfo = breeds[0];
      }
    });
  }

  closeModal(): void {
    this.close.emit();
  }

    // Emit the dog data for editing
  editDog(dog: any): void {
    this.edit.emit(dog);
  }

  // Emit the dog data for deletion
  deleteDog(dog: any): void {
    this.delete.emit(dog);
}
}
