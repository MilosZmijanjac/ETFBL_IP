import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LOCALSTORAGE_USER_KEYS } from 'src/app/app.module';
import { UserInfoResponse } from 'src/app/models/responses/UserInfoResponse';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  username: string | any;
  userInfo: UserInfoResponse | any;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    let userString: string |null = localStorage.getItem(LOCALSTORAGE_USER_KEYS);
    if(userString!==null)
    this.userInfo=JSON.parse(userString);
  }

}
