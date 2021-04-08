import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-modelvalidation-table',
  templateUrl: './modelvalidation-table.component.html',
  styleUrls: ['./modelvalidation-table.component.css']
})
export class ModelvalidationTableComponent implements OnInit {
  @Input() data: any;
  
  constructor() { }

  ngOnInit() {
    
  }

}