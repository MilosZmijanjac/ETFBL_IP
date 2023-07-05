import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LOCALSTORAGE_USER_KEYS } from 'src/app/app.module';
import { UserInfoResponse } from 'src/app/models/responses/UserInfoResponse';
import { FileService } from 'src/app/services/file.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  username: string | any;
  userInfo: UserInfoResponse | any;
  isEditable:boolean=false;
  formData!:FormData
  constructor(private userService: UserService,private fileService:FileService) { }

  ngOnInit(): void {
    let userString: string |null = localStorage.getItem(LOCALSTORAGE_USER_KEYS);
    if(userString!==null)
    this.userInfo=JSON.parse(userString);
  }
  editUser(data: NgForm) {
   
    this.userService.update(data.value).subscribe(
      data => {
        this.userInfo=data;
        localStorage.setItem(LOCALSTORAGE_USER_KEYS,JSON.stringify(this.userInfo));
        if(this.formData!==null)
        this.fileService.uploadProfile(this.formData,data.username).subscribe();
      },
      error => {
        console.log('An error occured!');
      }
    );
  }
  onFileSelected(event:any) {
    const file: File = event.target.files[0];

    if (file) {
      this.formData = new FormData();
      this.formData.append("files", file);
      
    }
}
}
