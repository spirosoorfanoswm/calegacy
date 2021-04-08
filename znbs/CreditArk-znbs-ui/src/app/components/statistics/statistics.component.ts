import {Component, isDevMode, OnDestroy, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DataService} from '../../services/data.service';
import {Subscription} from 'rxjs';
import {GeneralService} from '../../services/general.service';
import { AlertService } from './../../_alert';

@Component({
    selector: 'app-statistics',
    templateUrl: './statistics.component.html',
    styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit,OnDestroy {

    data: any;
    context: string;
    graphs: any;
    labelsGraph: any;
    snapshotDateFrom: string;
    comparisonPeriod: string;
    view = 'table';
    selectedDate;

    //  MOULD Graph Structure
    graph: any;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    sub4: Subscription;
    sub5: Subscription;
    sub6: Subscription;
    params: any;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService,
                private alertService: AlertService) {
    }

    ngOnInit() {

        if (this.router.url === '/statistics') {

            //  TITLE CHANGE
            this.changeTitle('Statistics');

        }

        //  INITIALIZE SUBSCRIPTIONS
        this.initSubscriptions();

        //  FIRE WHEN URL CHANGES
        this.urlChange();

    }

    ngOnDestroy() {
        this.sub1.unsubscribe();
        this.sub2.unsubscribe();
        this.sub3.unsubscribe();
        this.sub4.unsubscribe();
        this.sub5.unsubscribe();
        this.sub6.unsubscribe();
    }

    /**
     *  Init Subscriptions to wait for changes
     */
    initSubscriptions() {

        this.sub4 = this.dataService.currentContext.subscribe(context => {
            if (this.params) {
                this.fireData(this.params);
            }
        });

        this.sub5 = this.dataService.currentSnapshotDateFrom.subscribe(snapshotDateFrom => {
            if (this.params) {
                this.fireData(this.params);
            }
        });

        this.sub6 = this.dataService.currentComparisonPeriod.subscribe(comparisonPeriod => {
            if (this.params) {
                this.fireData(this.params);
            }
        });
    }

    /**
     *  Changes In URL
     */
    urlChange() {

        this.route.paramMap.subscribe(params => {

            this.params = params;

            this.fireData(params);

        });

    }

    /**
     *  GET DATA
     *  @param params
     */
    fireData(params) {

        this.sub1 = this.dataService.currentContext.subscribe(context => {

            this.context = context;

            this.sub2 = this.dataService.currentSnapshotDateFrom.subscribe(snapshotDateFrom => {

                this.snapshotDateFrom = snapshotDateFrom;

                this.sub3 = this.dataService.currentComparisonPeriod.subscribe(comparisonPeriod => {

                    this.comparisonPeriod = comparisonPeriod;

                    if (this.sub1 && this.sub2 && this.sub3) {
                        this.sub1.unsubscribe();
                        this.sub2.unsubscribe();
                        this.sub3.unsubscribe();
                    }

                    if (this.context && this.snapshotDateFrom && this.comparisonPeriod) {

                        let params_url = {
                            'contextId': this.context,
                            'snapshotDate': this.snapshotDateFrom,
                            'comparisonPeriod': this.comparisonPeriod,
                            'portfolioId': (params.get('portfolioId') != null) ? params.get('portfolioId') : '-1'
                        };
                        
                        this.generalService.getStatisticsData(params_url).subscribe(data => {
                           
                            this.data = data;

                            this.generalService.getStatisticsGraphs(params_url).subscribe(graphs => {

                                this.structureGraphArray();

                                //  LABELS
                                this.labelsGraph = graphs.lineDataPointCollection.labels;

                                this.graphs = graphs.lineDataPointCollection;

                                //  Sort Data for Graphs
                                this.graphs.values.forEach(elem => {

                                    switch (elem.description) {

                                        //  PERIOD & EXPOSURE
                                        case 'periodLimit':
                                            this.graph[0].data[0] = elem.values;
                                            break;

                                        case 'periodExposure':
                                            this.graph[0].data[1] = elem.values;
                                            break;

                                        case 'periodTurnover':
                                            this.graph[1].data[0] = elem.values;
                                            break;

                                        case 'periodPayments':
                                            this.graph[1].data[1] = elem.values;
                                            break;

                                        case 'periodPastdue':
                                            this.graph[2].data[0] = elem.values;
                                            break;

                                        //  default: @todo Check to see for All Data
                                    }

                                });
                            });
                        });
                    }
                });
            });
        });
    }

    //  STRUCTURE DATA FOR GRAPH
    structureGraphArray() {

        this.graph = [
            {
                name: 'Limit & Exposure',
                data: []
            },
            {
                name: 'Turnover & Payments',
                data: []
            },
            {
                name: 'Past-due amount',
                data: []
            },
        ];

    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
    }

}