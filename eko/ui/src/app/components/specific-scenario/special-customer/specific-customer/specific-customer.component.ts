import {Component, OnDestroy, OnInit, isDevMode } from '@angular/core';
import {DataService} from '../../../../services/data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {GeneralService} from '../../../../services/general.service';
import { AlertService } from './../../../../_alert';

@Component({
    selector: 'app-specific-customer',
    templateUrl: './specific-customer.component.html',
    styleUrls: ['./specific-customer.component.css']
})
export class SpecificCustomerComponent implements OnInit, OnDestroy {

    scenarioCustomer;

    scenario: any;
    scenarioSession:any;

    contextAll: any;

    customerId: string;
    customerInfo: any;
    isOpenTab: boolean = false;
    tabsTitles = ['Highlights', 'Analysis', 'Statistics', 'Provisions'];
    cards = [];
    selectedTab = 'Highlights';
    urlParams = {};
    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;
    specificCard: any;

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
        //  GET ContextINfo
        this.dataService.currentContextAll.subscribe(context => {

            this.contextAll = context;

        });

        //  GET current Scenario
        this.dataService.currentScenario.subscribe(scenario => {

            this.scenario = scenario;

        });

        //  Place Specific Scenario
        this.dataService.currentScenarioSpecialCustomer.subscribe(customer => {

            if (customer) {

                this.scenarioCustomer = customer;

                //  Assign Customer ID
                this.customerId = this.scenarioCustomer.customerId;

                //  Copy the Old Object for any errors
                this.scenarioSession = JSON.parse(JSON.stringify(this.scenarioCustomer));

                //  TITLE CHANGE
                this.changeTitle('Specific Scenario Customer: ' + this.scenarioCustomer.customerName);

                //  INITIALIZE SUBSCRIPTIONS
                this.initSubscriptions();

            } else {

                this.router.navigate(['scenarios']);

            }
        });
    }

    ngOnDestroy() {
        this.sub1.unsubscribe();
        this.sub2.unsubscribe();
        this.sub3.unsubscribe();
    }

    initSubscriptions() {

        this.sub1 = this.dataService.currentContext.subscribe(context => {

            this.context = context;

            this.fireData();
        });

        this.sub2 = this.dataService.currentSnapshotDateFrom.subscribe(snapshotDateFrom => {

            this.snapshotDateFrom = snapshotDateFrom;

            this.fireData();
        });

        this.sub3 = this.dataService.currentComparisonPeriod.subscribe(comparisonPeriod => {

            this.comparisonPeriod = comparisonPeriod;

            this.fireData();
        });
    }

    fireData() {

        if ( this.context && this.snapshotDateFrom && this.comparisonPeriod ) {

            this.urlParams = {
                'contextId': this.context,
                'customerId': this.customerId,
                'snapshotDate': this.snapshotDateFrom,
                'period': this.comparisonPeriod
            };

            this.generalService.getCustomerInfo(this.urlParams, this.customerId).subscribe(customerInfo => {
                this.customerInfo = customerInfo;
                this.cards = [
                    {title: 'ID', table: this.customerInfo.customerDetailsSnapshotDto.idData.values},
                    {title: 'Business data', table: this.customerInfo.customerDetailsSnapshotDto.businessData.values},
                    {title: 'Credit Scores', table: this.customerInfo.customerDetailsSnapshotDto.creditScoreData.values}
                ];
            });

            //  TESTING PURPOSES
            // this.generalService.getCustomerInfoDev(this.urlParams, this.customerId).subscribe(customerInfo => {
            //     this.customerInfo = customerInfo;
            //     this.cards = [
            //         {title: 'ID', table: this.customerInfo.customerDetailsSnapshotDto.idData.values},
            //         {title: 'Business data', table: this.customerInfo.customerDetailsSnapshotDto.businessData.values},
            //         {title: 'Credit Scores', table: this.customerInfo.customerDetailsSnapshotDto.creditScoreData.values}
            //     ];
            // });
        }
    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
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
     * Submit or Rollback
     */
    onSubmit() {
        this.allow = false;
        //  REMOVE STATISTICS FROM ARRAY
        delete this.scenario["statistics"];


        //  Change the parameters in current Porfolio
        this.scenario['portfolios'].forEach( (element, key) => {
            //  DELETE STATISTICS
            delete this.scenario["portfolios"][key]['statistics'];
        });

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
           // this.alertService.error('There was an error on update, your changes have been rolled back.');
           if(err.error && err.error.message) {
            this.alertService.error(err.error.message);
            } else {
                this.alertService.error('There was an error on update, your changes have been rolled back.');
            } 
            //this.scenarioCustomer = this.scenarioSession;
        });
    }

}
