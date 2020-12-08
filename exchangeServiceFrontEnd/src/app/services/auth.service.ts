import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {ResourcesService} from "./resources.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authUrl = "http://localhost:8080/authenticate";
  private rolesUrl = "http://localhost:8080/user/roles";
  private logoutUrl = "http://localhost:8080/logout";
  private registerUrl = "http://localhost:8080/register";

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  public roles: string[];


  constructor(private httpClient: HttpClient,
              private router: Router,
              private res:ResourcesService) {
    this.roles = [];
  }

  getHeaders(): HttpOptions {
    return this.httpOptions;
  }

  login({username, password}) {
    this.httpClient.post(this.authUrl, {username, password})
      .subscribe(data => {
        this.httpOptions.headers = this.httpOptions.headers.set("token", "Bearer_" + data["token"]);
        this.getRoles().subscribe(roles => {
            this.roles = roles;
            this.router.navigate(['/getRates'])
          })
      },error => alert("Wrong username or password!"));
  }

  logout() {
    this.httpClient.post(this.logoutUrl, null, this.httpOptions);
    this.roles = [];
    this.httpOptions.headers = this.httpOptions.headers.delete('token');
    this.res.setDefault();
    this.router.navigate(["/loginForm"]);
  }

  getRoles(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.rolesUrl, this.httpOptions);
  }

  isAuth(): boolean {
    return Boolean(this.httpOptions.headers.get('token') && this.httpOptions.headers.get('token').length);
  }

  register({username, password}) {
    this.httpClient.post(this.registerUrl, {username, password}, {responseType: 'text'})
      .subscribe(() => {
          this.router.navigate(["/loginForm"]);
        },
        () => alert("Username is already taken or invalid!"));
  }
}

interface HttpOptions {
  headers: HttpHeaders;
}
