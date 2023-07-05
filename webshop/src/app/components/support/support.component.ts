import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { SupportMessageRequest } from 'src/app/models/requests/SupportMessageRequest';
import { SupportService } from 'src/app/services/support.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit {
  constructor(private supportService:SupportService,private userService:UserService,private router: Router) { }

  ngOnInit(): void {
  }
  contactSupport(data:NgForm):void{
    if (!this.userService.isLoggedIn()){
      this.router.navigate(['/user-auth']);
      return;
    }
    let message:SupportMessageRequest={userMail:this.userService.getEmail(),text:data.value.message,username:this.userService.getUsername()};
    this.supportService.send(message).subscribe();
    console.log(data.value.message);
  }
}
