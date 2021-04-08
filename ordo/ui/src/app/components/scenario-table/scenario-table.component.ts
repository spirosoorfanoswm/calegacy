import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-scenario-table',
  templateUrl: './scenario-table.component.html',
  styleUrls: ['./scenario-table.component.css']
})
export class ScenarioTableComponent implements OnInit {

  @Input() dataStats: any;

  header: any;

  constructor() { }

  ngOnInit() {


    if(this.dataStats){
        this.getHeader();
    }

  }

  getHeader(){
    this.header = this.dataStats[0];

  }

}
