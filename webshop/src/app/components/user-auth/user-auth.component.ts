import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { tap } from 'rxjs/operators';
//import { ProductService } from '../services/product.service';
//import { UserService } from '../services/user.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { TokenModel } from 'src/app/models/domains/TokenModel';
import { LOCALSTORAGE_TOKEN_KEYS ,LOCALSTORAGE_USER_KEYS} from 'src/app/app.module';
import { Router } from '@angular/router';
import { FileService } from 'src/app/services/file.service';
import { UserInfoResponse } from 'src/app/models/responses/UserInfoResponse';

@Component({
  selector: 'app-user-auth',
  templateUrl: './user-auth.component.html',
  styleUrls: ['./user-auth.component.css'],
})
export class UserAuthComponent implements OnInit {
  showLogin: boolean = true;
  showPin: boolean = false;
  closeResult: string = '';
  authError: string = "";
  tokens: TokenModel | any;
  userInfo:UserInfoResponse|any;
  constructor(private userService: UserService, private modalService: NgbModal, private jwtHelper: JwtHelperService, private product: ProductService,private fileService:FileService ,private router: Router) {

  }

  ngOnInit(): void {

  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
  
  signUp(data: NgForm) {
    this.userService.register(data.value).subscribe(
      data => {
        console.log('Account created sucessfully!');
        this.openLogin();
      },
      error => {
        console.log('An error occured!');
      }
    );
  }
  login(data: NgForm, content: any) {

    console.log(data.value.username);
    this.userService.login(data.value.username, data.value.password).subscribe(
      data => {
        this.tokens = data;
        if (this.jwtHelper.decodeToken(this.tokens.access_token).roles[0] === "WEBSHOP_PENDING")
          this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
          }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
          });
        else if (this.jwtHelper.decodeToken(this.tokens.access_token).roles[0] === "WEBSHOP") {
          localStorage.setItem(LOCALSTORAGE_TOKEN_KEYS, JSON.stringify(this.tokens));
          this.router.navigate(['cart']).then(() => {
            window.location.reload();
          });
        }
        
      }
      ,
      error => {
        console.log('An error occured!');
      }
    );

  }
  openSignUp() {
    this.showLogin = false
  }
  openLogin() {
    this.showLogin = true;
  }

  enterPin(pin1: any, pin2: any, pin3: any, pin4: any, conent: any): void {
    let username: string = this.jwtHelper.decodeToken(this.tokens.access_token).sub;
    let pinCode: string = pin1.value + pin2.value + pin3.value + pin4.value
    this.userService.checkPin({ username, pinCode }).pipe(tap(() => this.router.navigate(['cart']).then(() => {
      window.location.reload();
    }))).subscribe(
      data => {
        if (data.activated) {
          localStorage.setItem(LOCALSTORAGE_TOKEN_KEYS, JSON.stringify(this.tokens));
          conent.dismiss('Cross click');
        }

      },
      error => {
        console.log("Pin error");
      }
    )

  }
  onFileSelected(event:any) {

    const file:File = event.target.files[0];

    if (file) {


        const formData = new FormData();

        formData.append("files", file);

       this.fileService.upload(formData).subscribe();
    }
}
  move(e: any, p: any, c: any, n: any) {
    var length = c.value.length;
    var maxlenght = c.getAttribute("maxlength");
    if (length == maxlenght) {
      if (n != "")
        n.focus();
    }
    if (p === "Backspace") {
      if (p != "")
        p.focus();
    }
  }
}
