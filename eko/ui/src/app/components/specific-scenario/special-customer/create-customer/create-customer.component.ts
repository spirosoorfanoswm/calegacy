import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Prospect} from '../../../../models/newProspect.model';
import {DataService} from '../../../../services/data.service';
import {Subscription} from 'rxjs';
import {GeneralService} from '../../../../services/general.service';
import {FormBuilder} from '@angular/forms';
import {Customer} from '../../../../models/newCustomer.model';
import { AlertService } from './../../../../_alert';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

    customerCreate: any;
    newCustomer: any;

    scenario: any;

    urlParams = {};
    contextAll:any;

    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;

    scenarioSession: any;

    customerId: string;
    customerInfo: any;
    isOpenTab: boolean = false;
    tabsTitles = ['Highlights', 'Analysis', 'Statistics', 'Provisions'];
    cards = [];
    selectedTab = 'Highlights';
    specificCard: any;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    allow:boolean;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private formBuilder: FormBuilder,
                private dataService: DataService,
                private generalService: GeneralService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.allow = true;
        //  GET current Scenario
        this.dataService.currentScenario.subscribe(scenario => {

            this.scenario = scenario;

            //  Copy the Old Object for any errors
            this.scenarioSession = JSON.parse(JSON.stringify(scenario));

        });

        //  GET ContextINfo
        this.dataService.currentContextAll.subscribe(context => {

            this.contextAll = context;

        });

        //  Create a new Prospect Class
        var newCustomer = new Customer();
        this.newCustomer = newCustomer.newCustomer;

        this.initSubscriptions();

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
     *  Get Customer Info
     */
    fireData() {

        if ( this.context && this.snapshotDateFrom && this.comparisonPeriod ) {

            this.urlParams = {
                'contextId': this.context,
                'customerId': this.customerCreate.customerId,
                'snapshotDate': this.snapshotDateFrom,
                'period': this.comparisonPeriod
            };
            this.allow = false;
            this.generalService.getCustomerInfo(this.urlParams, this.customerCreate.customerId ).subscribe(customerInfo => {
                 this.allow = true;
                this.customerInfo = customerInfo;
                this.cards = [
                    {title: 'ID', table: this.customerInfo.customerDetailsSnapshotDto.idData.values},
                    {title: 'Business data', table: this.customerInfo.customerDetailsSnapshotDto.businessData.values},
                    {title: 'Credit Scores', table: this.customerInfo.customerDetailsSnapshotDto.creditScoreData.values}
                ];
            }, (err) => {
                this.allow = true;              
            });

            //  TESTING PURPOSES
            // this.generalService.getCustomerInfoDev(this.urlParams, this.customerCreate.customerId).subscribe(customerInfo => {
            //     this.customerInfo = customerInfo;
            //     this.cards = [
            //         {title: 'ID', table: this.customerInfo.customerDetailsSnapshotDto.idData.values},
            //         {title: 'Business data', table: this.customerInfo.customerDetailsSnapshotDto.businessData.values},
            //         {title: 'Credit Scores', table: this.customerInfo.customerDetailsSnapshotDto.creditScoreData.values}
            //     ];
            // });
        }
    }

    onClickTab(e) {
        this.selectedTab = e.target.innerHTML;
        switch (this.selectedTab) {
            case 'Analysis':
                this.cards = [
                    {title: 'Aging Analysis', table: this.customerInfo.customerDetailsSnapshotDto.buckets.values}
                ];
                break;
            case 'Statistics':
                this.cards = [
                    {title: 'Statistics', table: this.customerInfo.customerDetailsStatisticsDto.statistics}
                ];
                break;
            case 'Provisions':
                this.customerInfo.customerDetailsIFRSDto.values.map(item => item.values.length = 4);
                /*this.provisionPD=this.customerInfo.customerDetailsIFRSDto.lifePd;
                this.provisions=this.customerInfo.customerDetailsIFRSDto.provisions;
                this.provisNew=this.customerInfo.customerDetailsIFRSDto.newProvisions;
                this.difference=this.customerInfo.customerDetailsIFRSDto.difference;*/
                this.cards = [
                    {title: 'Provisions', table: this.customerInfo.customerDetailsIFRSDto}
                ];
                break;
            default:
                this.cards = [
                    {title: 'ID', table: this.customerInfo.customerDetailsSnapshotDto.idData.values},
                    {title: 'Business data', table: this.customerInfo.customerDetailsSnapshotDto.businessData.values},
                    {title: 'Credit Scores', table: this.customerInfo.customerDetailsSnapshotDto.creditScoreData.values}
                ];
        }
    }

    /**
     * Callback to get information
     * @param $event
     */
    receiveInformation($event) {

        this.customerCreate = $event;

        this.fireData();

    }

    /**
     * Submit or Rollback
     */
    onSubmit() {

        this.newCustomer.customerId = this.customerCreate.customerId;
        this.newCustomer.customerName = this.customerCreate.customerName;

        //  PUSH NEW PROSPECT TO SCENARIO
        this.scenario.customers.push( this.newCustomer );

        //  REMOVE STATISTICS FROM ARRAY
        delete this.scenario['statistics'];

        //  REMOVE STATISTICS FROM PORTFOLIOS
        this.scenario['portfolios'].forEach((element, key) => {
            //  DELETE STATISTICS
            delete this.scenario['portfolios'][key]['statistics'];
        });

        //  REMOVE STATISTICS FROM PROSPECTS
        this.scenario['prospects'].forEach((element, key) => {
            //  DELETE STATISTICS
            delete this.scenario['prospects'][key]['statistics'];
        });

        //  LETS SEND THE REQUEST OVER
        this.urlParams = {
            'contextId': this.context,
            'snapshotDate': this.snapshotDateFrom,
            //  Only place Specific Scenario
            'scenarios': [ this.scenario ]
        };
        this.allow = false;
       // this.alertService.loadSpinner('loader');
        this.generalService.putScenario(this.urlParams).subscribe(data => {
            this.allow = true;
            this.alertService.info('Your changes have been saved. Please wait until scenario processing is finished. Thank you!');

            // Update Scenario on Service
            this.dataService.changeScenario( data );
            this.router.navigate(["/scenarios"]);

        }, (err) => {
            this.allow = true;
            const index = this.scenario.customers.indexOf(this.newCustomer);
            this.scenario.customers.splice(index, 1);     
            if(err.error && err.error.message) {
                this.alertService.error(err.error.message);
            } else {
                this.alertService.error('There was an error on update, your changes have been rolled back.');
            }
            this.scenario = this.scenarioSession;
           // this.alertService.error('There was an error on update, your changes have been rolled back.');
           // this.router.navigate(["/scenarios"]);
        });
    }

}
