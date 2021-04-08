import { Component, OnInit, Input } from '@angular/core';
import { AlertService } from './../../../_alert';
import {GeneralService} from '../../../services/general.service';
@Component({
  selector: 'app-modelvalidation-table',
  templateUrl: './modelvalidation-table.component.html',
  styleUrls: ['./modelvalidation-table.component.css']
})
export class ModelvalidationTableComponent implements OnInit {  

  constructor(
    private generalService: GeneralService,
    private alertService: AlertService) {
  }

  ngOnInit() {
    
  }

  export() {

    this.generalService.exportReports();
  }

}