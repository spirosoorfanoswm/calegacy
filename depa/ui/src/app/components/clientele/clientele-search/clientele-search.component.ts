import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import {DataService} from '../../../services/data.service';
import {GeneralService} from '../../../services/general.service';
import { Subscription } from 'rxjs';
import {Router, ActivatedRoute} from '@angular/router';
import { AlertService } from './../../../_alert';
import {environment} from '../../../../environments/environment';

@Component({
  selector: 'app-clientele-search',
  templateUrl: './clientele-search.component.html',
  styleUrls: ['./clientele-search.component.css']
})
export class ClienteleSearchComponent implements OnInit {
  @ViewChild('f', {static: false}) searchCustomerForm: NgForm;
  @Input() scenarioId: any;
  display = 'none';
  displayCustomerInfo = 'none';
  customerId:any;
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
  allow:boolean;
  vatLabel : string;

  sub1: Subscription;
  sub2: Subscription;
  sub3: Subscription;
  showClientInfo:boolean;
 // searchCustomerForm: FormGroup;
  searchCriteria = [
    {value: 'VAT', displayName: environment.vatLabel},
    {value: 'CODE', displayName: 'Customer code'},
    {value: 'NAME', displayName: 'Customer Name'}
  ];

  constructor( private dataService: DataService,
               private generalService: GeneralService,
               private router: Router,
               private route: ActivatedRoute,
               private alertService: AlertService) {}

  ngOnInit() {
    this.allow = true;
    this.vatLabel = environment.vatLabel;
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
  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  searchCustomer() {
    
    this.submmited = true;
    this.searchKey = this.searchCustomerForm.value.searchKey;
    this.searchBy = this.searchCustomerForm.value.searchBy;
    this.portfolioId = (this.router.url === '/clientele' || this.router.url === '/specific-scenario') ? -1 : this.params.get('portfolioId');
    this.scenarioId = (this.scenarioId) ? this.scenarioId : -1;
    this.urlParams = {
      'contextId'     : this.context,
      'snapshotDate'  : this.snapshotDateFrom,
      'scenarioId'    : this.scenarioId,
      'portfolioId'   : this.portfolioId,
      'searchBy'      : this.searchBy
    };
    this.allow = false;
    //this.alertService.loadSpinner('loader');
    this.generalService.getSearchCustomer(this.urlParams, this.searchKey).subscribe(data => {
      this.allow = true;
      this.customers = data;
      if(this.customers && this.customers.length>0) {
        this.submmited = false;
      } else {
        this.submmited = true;
      }
    }, (err) => {
      this.customers =[];
          this.allow = true;
    });


    }

    closeInfo() {
      this.showClientInfo = false;
      this.displayCustomerInfo='none';
      this.display='none';
  }

  showInfo(customerId: string) {
    this.customerId = customerId          
    this.showClientInfo = false;
    this.displayCustomerInfo='none';
    this.display='none';
    if (this.router.url.startsWith('/clientele') || this.router.url === '/clientele' || this.router.url === '/specific-scenario') {
      this.customerId = customerId          
      this.showClientInfo = true;
      this.displayCustomerInfo='block';
    } else {
      this.router.navigate([this.router.url + '/' + customerId]);
    }


//    if (this.router.url === '/clientele') {
//       this.router.navigate([this.router.url + '/*/' + customerId]);
//    } else if ( this.router.url === '/specific-scenario') {
//      this.router.navigate(['/clientele' + '/*/' + customerId + '/' + this.scenarioId]);
//    } else {
//        this.router.navigate([this.router.url + '/' + customerId]);
//    }
  }

}
