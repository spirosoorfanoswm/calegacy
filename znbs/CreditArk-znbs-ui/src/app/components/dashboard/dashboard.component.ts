import {Component, isDevMode, OnChanges, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {GeneralService} from '../../services/general.service';
import {ICustomer} from '../../_interface/customer';
import {Subscription} from 'rxjs';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy {
    dataRisk: any;
    dataStatistics: any;
    dataDistributionStatistics:any;
    context: string;
    snapshotDateFrom: string;
    comparisonPeriod: string;
    selectedDate: string;
    subcategories: any;
    //  All Customers
    customers: ICustomer[];

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    params: any;

    meanExposure: any;
    periodVat: any;

    constructor(private route: ActivatedRoute,
                private router: Router,
                private dataService: DataService,
                private generalService: GeneralService) {
    }

    ngOnInit() {

        if (this.router.url === '/credit-monitor') {
            this.dataService.getMenuItems().subscribe(menuItems => {
                menuItems.firstLevelMenuItems.forEach(firstLevelMenuItem => {
                    if (firstLevelMenuItem.link === 'credit-monitor') {
                        this.subcategories = firstLevelMenuItem.secondLevelMenuItems;
                    }
                });
            });
        }

        //  INITIALIZE SUBSCRIPTIONS
        this.initSubscriptions();

    }

    ngOnDestroy() {
        this.sub1.unsubscribe();
        this.sub2.unsubscribe();
        this.sub3.unsubscribe();
    }

    /**
     *  Init Subscriptions to wait for changes
     */
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

        if (this.context && this.snapshotDateFrom && this.comparisonPeriod) {

            let params_url = {
                'contextId': this.context,
                'snapshotDate': this.snapshotDateFrom,
                'comparisonPeriod': this.comparisonPeriod,
                'portfolioId': '-1'
            };

            //  GET RISK PROFILE DATA FROM API
            this.generalService.getClientDistribution(params_url).subscribe(data => {

                this.dataRisk = data.clienteleDistributionList;
                this.dataDistributionStatistics = data.distributionStatistics;
        

                this.dataService.changeRiskProfileViewData('customers');

                //  GET STATISTICS DATA
                this.generalService.getStatisticsData(params_url).subscribe(dataStats => {
                    this.dataStatistics = dataStats;

                    dataStats.clientStatisticItemDtos.forEach(stat => {
                        if (stat.statistic == 'meanExposure') {
                            this.meanExposure = stat;
                        }

                        if (stat.statistic == 'periodVat') {
                            this.periodVat = stat;
                        }
                    });

                });
            });
        }
    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
    }
}