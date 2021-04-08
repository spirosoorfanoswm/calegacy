import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-statistics-table',
  templateUrl: './statistics-table.component.html',
  styleUrls: ['./statistics-table.component.css']
})
export class StatisticsTableComponent implements OnInit {
  @Input() data: any;
  @Input() selectedDate: string;
  
  constructor() { }

  ngOnInit() {
    
  }

}