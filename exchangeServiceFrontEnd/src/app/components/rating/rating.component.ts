import { Component, OnInit } from '@angular/core';
import {Rating, RatingService} from "../../services/rating.service";

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  rating:Rating[];

  constructor(
    private ratingService:RatingService
  ) { }

  ngOnInit(): void {
    this.ratingService.getRatingList().subscribe(data=>{
      this.rating=data;
    });
  }
}
