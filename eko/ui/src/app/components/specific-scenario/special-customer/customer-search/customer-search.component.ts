import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import {DataService} from '../../../../services/data.service';
import {GeneralService} from '../../../../services/general.service';
import { Subscription } from 'rxjs';
import {Router, ActivatedRoute} from '@angular/router';
import { AlertService } from './../../../../_alert';

@Component({
  selector: 'app-customer-search',
  templateUrl: './customer-search.component.html',
  styleUrls: ['./customer-search.component.css']
})
export class CustomerSearchComponent implements OnInit {
  @ViewChild('f', {static: false}) searchCustomerForm: NgForm;
  display = 'none';
  searchBy: string;
  searchKey: string;
  submmited = false;
  customers: any;
  context: any;
  portfolioId: any;
  comparisonPeriod: string;
  snapshotDateFrom: string;
  urlParams: any;
  params: any;

  sub1: Subscription;
  sub2: Subscription;
  sub3: Subscription;
  allow:boolean;

 // searchCustomerForm: FormGroup;
  searchCriteria = [
    {value: 'VAT', displayName: 'VAT ID Number'},
    {value: 'CODE', displayName: 'Customer code'},
    {value: 'NAME', displayName: 'Customer Name'}
  ];

  @Output() messageEvent = new EventEmitter<any>();

  constructor( private dataService: DataService,
               private generalService: GeneralService,
               private router: Router,
               private route: ActivatedRoute,
               private alertService: AlertService) {}

  ngOnInit() {
      // this.customers = [{
      //     "customerId": "4271",
      //     "portfolioId": 0,
      //     "portfolio": "ΠΕΤΡΕΛΑΙΟΕΙΔΗ - ΕΞΑΓΩΓΕΣ",
      //     "vatNumber": null,
      //     "customerName": "GUNVOR SA",
      //     "customerStatus": "Μέτριος",
      //     "balance": 50349153.02,
      //     "creditLimit": 67600000,
      //     "proposedLimit": 0,
      //     "score": "7"
      // }]
      this.allow = true;

  }

  openModal() {
    this.customers=[];
    this.submmited = false;
    this.display = 'block';
    this.sub1 = this.dataService.currentContext.subscribe(context => {
      this.context = context;
    });
    this.sub2 = this.dataService.currentSnapshotDateFrom.subscribe(snapshotDateFrom => {
      this.snapshotDateFrom = snapshotDateFrom;
    });

    this.sub3 = this.dataService.currentComparisonPeriod.subscribe(comparisonPeriod => {
      this.comparisonPeriod = comparisonPeriod;
    });

    this.route.paramMap.subscribe(params => {
      this.params = params;
    });
  }

  onCloseHandled() {
    this.display = 'none';
    this.searchCustomerForm.reset();
    this.sub1.unsubscribe();
    this.sub2.unsubscribe();
    this.sub3.unsubscribe();
  }

  searchCustomer() {
    this.allow = false;
    this.submmited = true;
    this.searchKey = this.searchCustomerForm.value.searchKey;
    this.searchBy = this.searchCustomerForm.value.searchBy;
    this.portfolioId = -1;
    this.urlParams = {
      'contextId'     : this.context,
      'snapshotDate'  : this.snapshotDateFrom,
      'scenarioId'    : -1,
      'portfolioId'   : -1,
      'searchBy'      : this.searchBy
    };

    this.generalService.getSearchCustomer(this.urlParams, this.searchKey).subscribe(data => {
      this.customers = data;
      this.allow = true;
    }, (err) => {
      this.allow = true;
  });
  }

    /**
     * EMIT Information
     * @param {string} customerId
     */
  showInfo(customerId: string, customerName: string) {

      //  Emit to Parent
      this.messageEvent.emit( {
          'customerId'    : customerId,
          'customerName'  : customerName
      });

      this.onCloseHandled();
  }

}
