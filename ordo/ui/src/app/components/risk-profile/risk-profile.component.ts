import {Component, Output, EventEmitter, OnInit, OnChanges, isDevMode, OnDestroy} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DataService} from '../../services/data.service';
import {GeneralService} from '../../services/general.service';
import {Subscription} from 'rxjs';
import {takeUntil} from 'rxjs/operators';

@Component({
    selector: 'app-risk-profile',
    templateUrl: './risk-profile.component.html',
    styleUrls: ['./risk-profile.component.css']
})
export class RiskProfileComponent implements OnInit, OnChanges, OnDestroy {

    data: any;
    dataAging:any;
    dataDunningAging:any;
    dataDistributionStatistics:any[];
    selectedDate: string;
    context: string;
    snapshotDateFrom: string;
    comparisonPeriod: string;
    isSpecificPortfolioId: boolean;
    view: string = 'table';
    typeChange = 0;
    typeChart = 'horizontalBar';
    OpenTab: any;
    clickName: any;
    graphData: any;
    averages:any;
    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    sub4: Subscription;
    sub5: Subscription;
    sub6: Subscription;
    params: any;
    portfolioId:any;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService) {
    }

    ngOnInit() {
        this.typeChange=0;

        if (this.router.url === '/risk-profile') {
            this.changeTitle('Risk Profile');
            this.isSpecificPortfolioId = false;

        } else {
            this.isSpecificPortfolioId = true;
        }
        this.initSubscriptions();
        this.urlChange();

    }

    ngOnChanges() {
    }

    ngOnDestroy() {
        this.sub1.unsubscribe();
        this.sub2.unsubscribe();
        this.sub3.unsubscribe();
        this.sub4.unsubscribe();
        this.sub5.unsubscribe();
        this.sub6.unsubscribe();
    }

    initSubscriptions() {

        this.sub4 = this.dataService.currentContext.subscribe(context => {
            this.context = context;
            if (this.params) {
                this.fireData(this.params);
            }
        });

        this.sub5 = this.dataService.currentSnapshotDateFrom.subscribe(snapshotDateFrom => {
            this.snapshotDateFrom = snapshotDateFrom;
            if (this.params) {
                this.fireData(this.params);
            }
        });

        this.sub6 = this.dataService.currentComparisonPeriod.subscribe(comparisonPeriod => {
            this.comparisonPeriod = comparisonPeriod;
            if (this.params) {
                this.fireData(this.params);
            }
        });
    }

    urlChange() {
        this.route.paramMap.subscribe(params => {
            this.params = params;
            this.fireData(params);
        });
    }

    fireData(params) {

        this.typeChange=0;        
        this.sub1 = this.dataService.currentContext.subscribe(context => {
            this.context = context;
            this.sub2 = this.dataService.currentSnapshotDateFrom.subscribe(snapshotDateFrom => {
                this.snapshotDateFrom = snapshotDateFrom;
                this.sub3 = this.dataService.currentComparisonPeriod.subscribe(comparisonPeriod => {
                    this.comparisonPeriod = comparisonPeriod;
                    if (this.context && this.snapshotDateFrom && this.comparisonPeriod) {
                        let params_url = {
                            'contextId': this.context,
                            'snapshotDate': this.snapshotDateFrom,
                            'comparisonPeriod': this.comparisonPeriod,
                            'portfolioId': (params.get('portfolioId') != null) ? params.get('portfolioId') : '-1'
                        };
                        this.portfolioId =  (params.get('portfolioId') != null) ? params.get('portfolioId') : '-1';
                        this.generalService.getClientDistribution(params_url).subscribe(data => {
                            this.dataDistributionStatistics =  data.distributionStatistics;
                           // console.log(JSON.stringify(this.dataDistributionStatistics[0]));
                           // console.log(JSON.stringify(this.dataDistributionStatistics));
                            this.data = data.clienteleDistributionList;
                            
                            
                            this.dataAging=data.dataAging.values;
                            this.dataDunningAging=data.dataDunningAging.values;
                            this.averages = data.paymentAverages.values;
                            this.dataService.changeRiskProfileViewData('customers');
                            this.structureGraphArray();
                            this.runMouldData();
                        });
                    }

                    if (this.sub1 && this.sub2 && this.sub3) {
                        this.sub1.unsubscribe();
                        this.sub2.unsubscribe();
                        this.sub3.unsubscribe();
                    }

                });
            });
        });

    }

    structureGraphArray() {
        this.graphData = [
            {
                name: 'Customers',
                ChartLabels: [],
                xLabel:'Customers (%)',
                yLabel:'CAS',
                data: [
                    {
                        data: {
                            0: [],
                            1: []
                        },
                        label: []
                    }
                ]
            },
            {
                name: 'Exposure',
                ChartLabels: [],
                xLabel:'Exposure (%)',
                yLabel:'CAS',
                data: [
                    {
                        data: {
                            0: [],
                            1: []
                        },
                        label: []
                    }
                ]
            }, {
                name: 'Aging',
                ChartLabels: [],
                xLabel:'Amount',
                yLabel:'Bucket',
                data: [
                    {
                        data: {
                            0: []
                        },
                        label: []
                    }
                ]
            } , {
                name: 'DunningAging',
                ChartLabels: [],
                xLabel:'Amount',
                yLabel:'Bucket',
                data: [
                    {
                        data: {
                            0: []
                        },
                        label: []
                    }
                ]
            } 
        ];
    }

    /**
     * Run Mould Data
     */
    runMouldData() {
        let labels = [];
        let agingLAbels = [];
        let agingDunningLAbels = [];
        this.data.forEach((elem, index) => {
            //  PUSH LABELS
            labels.push(elem.date);
            //  DISTRIBUTION ITEMS
            elem.clienteleDistributionItems.forEach((elemDistr, i) => {
                let dataCustomers = [];
                let dataExposure = [];
                this.graphData[index].ChartLabels.push(elemDistr.cas);
                //  CUSTOMERS
                if (elemDistr.customersPercentage) {
                    dataCustomers.push(elemDistr.customersPercentage.replace('%', ''));
                }
                //  EXPOSURE
                if (elemDistr.exposurePercentage) {
                    dataExposure.push(elemDistr.exposurePercentage.replace('%', ''));
                }
                //  DATA
                this.graphData[0].data[0]['data'][index].push(dataCustomers);
                this.graphData[1].data[0]['data'][index].push(dataExposure);
            });
        });
        this.dataAging.forEach((elem, index) => {
            this.graphData[2].ChartLabels.push(elem.key);
            let dataAgingElems = [];
            if (elem.value) {
                dataAgingElems.push(elem.value);
               
            }
            this.graphData[2].data[0]['data'][0].push(dataAgingElems);
           
        });

        this.dataDunningAging.forEach((elem, index) => {
            this.graphData[3].ChartLabels.push(elem.key);
            let dataDunningAgingElems = [];
            if (elem.value) {
                dataDunningAgingElems.push(elem.value);
               
            }
            this.graphData[3].data[0]['data'][0].push(dataDunningAgingElems);
           
        });
        
        agingLAbels.push('Aging');
        agingDunningLAbels.push('Dunning Aging');
    
        this.graphData[0].data[0].label.push(labels);
        this.graphData[1].data[0].label.push(labels);
        this.graphData[2].data[0].label.push(agingLAbels);
        this.graphData[3].data[0].label.push(agingDunningLAbels);
    }

    changeViewData(viewData: string) {
        this.dataService.changeRiskProfileViewData(viewData);
        (viewData === 'customers') ? this.typeChange = 0 : 
        (viewData === 'exposure') ? this.typeChange = 1 : 
        (viewData === 'aging') ? this.typeChange = 2 :  this.typeChange = 3;
    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
    }

    export(){
        let params_url = {
            'contextId': this.context,
            'snapshotDate': this.snapshotDateFrom,
            'comparisonPeriod': this.comparisonPeriod,
            'portfolioId': this.portfolioId
        };
        
        this.generalService.exportClientDistribution(params_url).subscribe(resp => {
            if(resp.exists == true) {
                let exportParams = {
                    'file'     : resp.fileName                  
                };
                this.generalService.downloadFile(exportParams);
            } 
        }, (err) => {
            //console.log(err);
        });
    }

}