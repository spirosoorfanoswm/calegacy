import { Component, OnInit, Input } from '@angular/core';
import {DataService} from '../../../services/data.service';
import {GeneralService} from '../../../services/general.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import { AlertService } from './../../../_alert';

@Component({
  selector: 'app-special-customer',
  templateUrl: './special-customer.component.html',
  styleUrls: ['./special-customer.component.css']
})
export class SpecialCustomerComponent implements OnInit {

    @Input() scenario: any;

    scenarioSpecialCustomers: any;
    searchParams;
    loadedSpecialCustomer = false;
    createCustomer = false;

    //  PAGINATION
    itemsPerPageLoad = 100;
    totalItemsLoad   = 200;
    j: any;

    urlParams = {};

    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    allow:boolean;
    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.allow = true;
        this.scenarioSpecialCustomers = this.scenario.customers;

        this.initSubscriptions();
        this.changeTitle('Specific Scenario Customers');
    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
    }

    initSubscriptions() {

        this.sub1 = this.dataService.currentContext.subscribe(context => {

            this.context = context;

        });

        this.sub2 = this.dataService.currentSnapshotDateFrom.subscribe(snapshotDateFrom => {

            this.snapshotDateFrom = snapshotDateFrom;

        });

        this.sub3 = this.dataService.currentComparisonPeriod.subscribe(comparisonPeriod => {

            this.comparisonPeriod = comparisonPeriod;

        });
    }

    /**
     * Filter Search: TEXT
     * @param f
     * @param key
     */
    filterResults(f, key) {

        let toSearch = f.value;
        toSearch['key'] = key;

        this.searchParams = toSearch;
    }

    newCustomer(){

        this.createCustomer = true;

    }

    homeCustomer(){

        this.loadedSpecialCustomer = false;
        this.createCustomer = false;

    }

    /**
     * Show Info
     * @param {string} customerId
     */
    showInfo( customer: any ) {

        this.dataService.changeScenarioSpecialCustomer( customer );
        this.loadedSpecialCustomer = true;
    }

    homeProspect() {

        this.loadedSpecialCustomer = false;

    }

    deleteCustomer( i, scenario ){
       
        if( confirm("Are you sure you want to proceed to delete the Customer: " + scenario.customerName )) {
            var deletedCustomerId = scenario.customerId;
            this.allow = false;
            this.scenarioSpecialCustomers.splice(i, 1);

            //  Place Deleted in the Specific Scenario
            this.scenario['deletedCustomers'] = [deletedCustomerId];

            //  LETS SEND THE REQUEST OVER
            this.urlParams = {
                'contextId'     : this.context,
                'snapshotDate'  : this.snapshotDateFrom,
                //  Only place Specific Scenario
                'scenarios'     : [ this.scenario ]
            };
            this.generalService.putScenario( this.urlParams).subscribe(data => {
                this.allow = true;
                this.alertService.info('Your changes have been saved. Please wait until scenario processing is finished. Thank you');
                this.dataService.changeScenario( data );
                this.router.navigate(["/scenarios"]);

            }, (err) => {     
                this.allow = true;    
                if(err.error && err.error.message) {
                    this.alertService.error(err.error.message);
                } else {
                    this.alertService.error('There was an error on update, your changes have been rolled back.');
                }      
                //this.scenarioCustomer = this.scenarioSession; 
               // this.alertService.error('There was an error on update, your changes have been rolled back.');
               // this.router.navigate(["/scenarios"]);
            });
        }
    }

}
