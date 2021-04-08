import { Component, OnInit, Input } from '@angular/core';
import { AlertService } from './../../_alert';
import {GeneralService} from './../../services/general.service';
@Component({
  selector: 'app-exporter-table',
  templateUrl: './exporter-table.component.html',
  styleUrls: ['./exporter-table.component.css']
})
export class ExporterTableComponent implements OnInit {  

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