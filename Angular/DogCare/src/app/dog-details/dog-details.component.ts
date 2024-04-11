
import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../services/user.service';

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
  breedAverageWeightLowerBound: number | null = null; ;
  breedAverageWeightUpperBound: number | null = null; ;

  constructor(private http: HttpClient, private userService :UserService) {}

  ngOnInit(): void {
    this.fetchBreedInfo(this.dog.breedName);
  }

  fetchBreedInfo(breedName: string): void {
    const url = `https://api.thedogapi.com/v1/breeds/search?q=${encodeURIComponent(breedName)}`;
    this.http.get<any[]>(url).subscribe(breeds => {
      if (breeds.length > 0) {
        // Assuming the first result is the most relevant
        this.breedInfo = breeds[0];
        const weightRange = this.breedInfo.weight.metric.split(' - ');
        this.breedAverageWeightLowerBound = parseFloat(weightRange[0]);
        this.breedAverageWeightUpperBound = parseFloat(weightRange[1]);
      }
    });
  }

  sendWeightAlert(): void {
    this.sendEmailBasedOnWeight();
  }
  

  sendEmailBasedOnWeight(): void {
    const bodyData = {
      to: this.userService.getUserEmailbyId(),
      subject: 'Dog Weight Alert',
      text: ''
    };
    console.log("Email = ", this.userService.getUserEmailbyId())
    if(this.breedAverageWeightLowerBound && this.breedAverageWeightUpperBound)
    if (this.dog.weight < this.breedAverageWeightLowerBound) {
      alert('Your dog ' + this.dog.name + ' is underweight. Check Email for more Information')
      bodyData.text = 'Your dog ' + this.dog.name + ' is underweight. Consider increasing its food intake. Normal weight should be ' + this.breedInfo.weight.metric +' kg';
    } else if (this.dog.weight > this.breedAverageWeightUpperBound) {
      alert('Your dog ' + this.dog.name + ' is overweight. Check Email for more Information')
      bodyData.text = 'Your dog ' + this.dog.name +' is overweight. Consider reducing its food intake. Normal weight should be ' + this.breedInfo.weight.metric +' kg';
    } else {
      alert('Your dog is within the ideal weight range. Check Email for more Information')
      bodyData.text = 'Your dog is within the ideal weight range.';
    }

    // Trigger an HTTP request to your Spring Boot backend to send the email
    this.http.post('http://localhost:8080/send-email', bodyData).subscribe(
      response => {
        console.log('Email sent:', response);
      },
      error => {
        console.error('Error sending email:', error);
      }
    );
  }

  closeModal(): void {
    this.close.emit();
  }

  editDog(dog: any): void {
    this.edit.emit(dog);
  }

  deleteDog(dog: any): void {
    this.delete.emit(dog);
  }
}
