import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authUrl = "http://localhost:8080/authenticate";
  private rolesUrl = "http://localhost:8080/user/roles";
  private logoutUrl = "http://localhost:8080/logout";

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  public roles: string[];

  constructor(private httpClient: HttpClient,
              private router: Router) {
  }

  getHeaders(): HttpOptions {
    return this.httpOptions;
  }

  login({username, password}) {
    this.httpClient.post(this.authUrl, {username, password})
      .subscribe(data => {
        this.getRoles().subscribe(roles => {
          this.roles = roles;
          this.router.navigate(['/getRates'])
            .then(r => this.httpOptions.headers = this.httpOptions.headers.set('Authorization', "data['_token']"));
        }, error => console.log("Status:", error.status, "\nMessage: ", error.message))
      });
  }

  logout() {
    this.httpClient.post(this.logoutUrl, null, this.httpOptions);
    this.roles = [];
    this.httpOptions.headers = this.httpOptions.headers.delete('Authorization');
  }

  getRoles(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.rolesUrl, this.httpOptions);
  }

  isAuth(): boolean {
    return Boolean(this.httpOptions.headers.get('Authorization') &&
      this.httpOptions.headers.get('Authorization').length);
  }
}

interface HttpOptions {
  headers: HttpHeaders;
}
