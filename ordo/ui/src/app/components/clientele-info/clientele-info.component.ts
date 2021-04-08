import {Component, OnInit, OnDestroy, isDevMode, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DataService} from '../../services/data.service';
import {GeneralService} from '../../services/general.service';
import {Subscription} from 'rxjs';
import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';


@Component({
    selector: 'app-clientele-info',
    templateUrl: './clientele-info.component.html',
    styleUrls: ['./clientele-info.component.css']
})
export class ClienteleInfoComponent implements OnInit, OnDestroy {
   
    @Input() scenarioIdIn: any;
    @Input() customerIdIn: any;
    customerId: string;
    customerInfo: any;
    isOpenTab: boolean = false;
    tabsTitles = ['Highlights', 'Aging Analysis', 'Statistics', 'Provisions'];
    cards = [];
    selectedTab = 'Highlights';
    urlParams = {};
    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;
    specificCard: any;
    j: any;
    scenarioId: any;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    allow:boolean;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService) {
    }

    ngOnInit() {
        this.allow = true;
        //  TILTE CHANGE
        //this.changeTitle('Clientele Info');

        //  INITIALIZE SUBSCRIPTIONS
        this.initSubscriptions();

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
    delay(ms: number) {
        return new Promise( resolve => setTimeout(resolve, ms) );
      }

    fireData() {
        this.customerId = this.customerIdIn;//this.route.snapshot.params.customerId;
        this.scenarioId = this.scenarioIdIn;// this.route.snapshot.params.scenarioId;
        
        if ( this.customerIdIn && this.context && this.snapshotDateFrom && this.comparisonPeriod ) {
            if (this.scenarioIdIn && this.scenarioId!=-1) {
                this.urlParams = {
                    'contextId': this.context,
                    'customerId': this.customerId,
                    'snapshotDate': this.snapshotDateFrom,
                    'period': this.comparisonPeriod,
                    'scenarioId': this.scenarioId
                };
            } else {
                this.urlParams = {
                    'contextId': this.context,
                    'customerId': this.customerId,
                    'snapshotDate': this.snapshotDateFrom,
                    'period': this.comparisonPeriod
                };
            }
            this.allow = false;
            this.generalService.getCustomerInfo(this.urlParams, this.customerId).subscribe(customerInfo => {
                this.allow = true;
                this.customerInfo = customerInfo;
                this.cards = [
                    {title: 'ID', table: this.customerInfo.customerDetailsSnapshotDto.idData.values},
                    {title: 'Business data', table: this.customerInfo.customerDetailsSnapshotDto.businessData.values},
                    {title: 'Credit KPIs', table: this.customerInfo.customerDetailsSnapshotDto.creditScoreData.values}
                ];
            }, (err) => {
                this.allow = true;
            });
        }
    }

    changeTitle(title: string) {

        this.dataService.changeTitle(title);

    }
    home() {
        
    }

    onClickTab(e) {
        this.selectedTab = e.target.innerHTML;
        switch (this.selectedTab) {
            case 'Aging Analysis':
            this.cards = [
                {title: 'Aging Analysis', table: this.customerInfo.customerDetailsSnapshotDto.buckets.values}
            ];
            break;
            case 'Statistics':
              if (this.customerInfo.customerScenarioStatisticsDto.data.values.length > 0 ) {
                this.cards = [
                  {title: 'Statistics', table: this.customerInfo.customerDetailsStatisticsDto.statistics},
                  {title: 'Scenario Statistics', table: this.customerInfo.customerScenarioStatisticsDto.data.values}
                ];
              } else {
                this.cards = [
                  {title: 'Statistics', table: this.customerInfo.customerDetailsStatisticsDto.statistics}
                ];
              }

            break;
            case 'Provisions':
                this.customerInfo.customerDetailsIFRSDto.values.map(item => item.values.length = 4);
                this.cards = [
                    {title: 'Provisions', table: this.customerInfo.customerDetailsIFRSDto}
                ];
            break;
            default:
            this.cards = [
                {title: 'ID', table: this.customerInfo.customerDetailsSnapshotDto.idData.values},
                {title: 'Business data', table: this.customerInfo.customerDetailsSnapshotDto.businessData.values},
                {title: 'Credit KPIs', table: this.customerInfo.customerDetailsSnapshotDto.creditScoreData.values}
            ];
        }
    }
}
