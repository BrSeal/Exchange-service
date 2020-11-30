import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  logout(): any{
    this.authService.logout();
  };

  constructor(private authService:AuthService) {}

  isAuth():boolean{
    return this.authService.isAuth();
  }

  ngOnInit(): void {
  }

}
