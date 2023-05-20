import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit {
  
  constructor() { }

  ngOnInit(): void {
  }
  contactSupport(data:NgForm):void{
    console.log(data.value.message);
    
  }
}
