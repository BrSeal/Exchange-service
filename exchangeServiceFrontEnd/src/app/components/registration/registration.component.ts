import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  username: string;
  password: string;

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }

  register() {
    this.authService.register({username:this.username,password:this.password});
  }
}
