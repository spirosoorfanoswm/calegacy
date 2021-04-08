import { Component, OnInit, Input } from '@angular/core';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-risk-profile-table',
  templateUrl: './risk-profile-table.component.html',
  styleUrls: ['./risk-profile-table.component.css']
})
export class RiskProfileTableComponent implements OnInit {
  @Input() data: any;
  @Input() averages: any;
  @Input() selectedDate: string;
  viewData: string;
  columnName: string;
  averagesValues:any;


  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.dataService.currentRiskProfileViewData.subscribe(viewData => {
      this.viewData = viewData;
     
      this.columnName = viewData.charAt(0).toUpperCase() + viewData.slice(1);
    });
  }
  
}