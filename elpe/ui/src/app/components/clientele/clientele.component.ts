import {Component, OnInit,  OnDestroy, isDevMode , Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DataService} from '../../services/data.service';
import {GeneralService} from '../../services/general.service';
import { Subscription } from 'rxjs';
import {environment} from '../../../environments/environment';
import { AlertService } from './../../_alert';

@Component({
    selector: 'app-clientele',
    templateUrl: './clientele.component.html',
    styleUrls: ['./clientele.component.css']
})
export class ClienteleComponent implements OnInit, OnDestroy {
    @Input() scenarioId: any;
    customerId:any;
    data: any;
    title: string;
    searchParams;
    vatLabel : string;

    //  PAGINATION
    itemsPerPageLoad = 100;
    totalItemsLoad   = 200;

    customersCount: number;
    urlParams = {};
    exportParams = {};
    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;
    portfolioId: any;
    p: any;
    sortParams;
    order: boolean = true;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    sub4: Subscription;
    sub5: Subscription;
    sub6: Subscription;
    params: any;
    allow:boolean;
    showClientInfo:boolean;
    display = 'none';

    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.allow = true;
        this.showClientInfo = false;
        this.vatLabel = environment.vatLabel
        if (this.router.url === '/clientele') {

            //  TILTE CHANGE
            this.changeTitle('Clientele');

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

    /**
     * Fire Data
     * @param params
     */
    fireData(params) {

        this.sub1 = this.dataService.currentContext.subscribe(context => {

            this.context = context;

            this.sub2 = this.dataService.currentSnapshotDateFrom.subscribe(snapshotDateFrom => {

                this.snapshotDateFrom = snapshotDateFrom;

                this.sub3 = this.dataService.currentComparisonPeriod.subscribe(comparisonPeriod => {

                    this.comparisonPeriod = comparisonPeriod;

                    if ( this.context && this.snapshotDateFrom && this.comparisonPeriod ) {


                       
                        this.portfolioId = (this.router.url === '/clientele' || this.router.url === '/specific-scenario') ? -1 : params.get('portfolioId');
                        this.scenarioId = (this.scenarioId) ? this.scenarioId : -1 ;
                        this.urlParams = {
                            'contextId'     : this.context,
                            'snapshotDate'  : this.snapshotDateFrom,
                            'scenarioId'    : this.scenarioId,
                            'portfolioId'   : this.portfolioId,
                            'length'        : this.itemsPerPageLoad,
                            'offset'        : 0,
                            'sortDescending': true,
                            'sortColumn'    : 6
                        };
                        this.allow = false;
                        this.generalService.getCustomers(this.urlParams).subscribe(data => {
                            this.allow = true;
                            this.data = data.customers;

                            //  START LOADING DATA IN THE BG
                            //  this.lazyLoadData( data.customersCount );

                            this.customersCount = data.customersCount;

                        }, (err) => {
                            this.allow = true;
                        });
                    }

                    if ( this.sub1 && this.sub2 && this.sub3 ) {
                        this.sub1.unsubscribe();
                        this.sub2.unsubscribe();
                        this.sub3.unsubscribe();
                    }
                });
            });
        });
    }

    /**
     * Show Info
     * @param {string} customerId
     */
    showInfo(customerId: string) {

            this.customerId = customerId          
            this.showClientInfo = true;
            this.display='block';
            
  //      if (this.router.url === '/clientele') {
  //         this.router.navigate(['/clientele' + '/*/' + customerId]);
  //      }else if( this.router.url === '/specific-scenario'){
 //           this.router.navigate(['/clientele' + '/*/' + customerId + '/' + this.scenarioId]);
 //       }else {
 //           this.router.navigate([this.router.url + '/' + customerId]);
 //       }
    }

    closeInfo() {
        this.showClientInfo = false;
        this.display='none';
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

    changeTitle(title: string) {

        this.dataService.changeTitle(title);

    }

    /**
     * Server Side Pagination
     * @param p
     */
    currentPage(p){

        var currentPage = p - 1;

        //  LOAD BUCKET
        this.urlParams = {
            'contextId'     : this.context,
            'snapshotDate'  : this.snapshotDateFrom,
            'scenarioId'    : this.scenarioId,
            'portfolioId'   : this.portfolioId,
            'length'        : this.itemsPerPageLoad,
            'offset'        : currentPage * this.itemsPerPageLoad,
            'sortDescending': true,
            'sortColumn'    : 6
        };
        this.allow = false;

        this.generalService.getCustomers(this.urlParams).subscribe(data => {
            this.data = data.customers;
            this.allow = true;

        }, (err) => {
            this.allow = true;
        });


    }

    //  @todo Make Angular Service
    lazyLoadData( totalCustomers ){

        const requests = this.itemsPerPageLoad / this.totalItemsLoad;

        const requestsToMake = Math.ceil( requests );

        var i = 1;
        do {

            //  LOAD BUCKET
            this.urlParams = {
                'contextId'     : this.context,
                'snapshotDate'  : this.snapshotDateFrom,
                'scenarioId'    : this.scenarioId,
                'portfolioId'   : this.portfolioId,
                'length'        : this.itemsPerPageLoad,
                'offset'        : i * this.itemsPerPageLoad,
                'sortDescending': true,
                'sortColumn'    : 6
            };
            this.allow = false;
            this.generalService.getCustomers(this.urlParams).subscribe(data => {
                this.allow = true;
                //  PUSH LOADED DATA
                data.customers.forEach( newData => {
                    this.data.push( newData );
                });

            }, (err) => {
                this.allow = true;
            });

            i++;
        }
        while (i < requestsToMake);


    }

    sortResults(f, key) {

        let toSort = f.value;

        (this.order) ? this.order = false : this.order = true;

        toSort['key'] = key;
        toSort['order'] = this.order;

        this.sortParams = [ toSort ];

    }

    export() {
        this.urlParams = {
            'contextId'     : this.context,
            'snapshotDate'  : this.snapshotDateFrom,
            'scenarioId'    : this.scenarioId,
            'portfolioId'   : this.portfolioId
        };
        
           
        this.generalService.exportCustomers(this.urlParams).subscribe(resp => {
            if(resp.exists == true) {
                this.exportParams = {
                    'file'     : resp.fileName                  
        };
                this.generalService.downloadFile(this.exportParams);
            } else if(resp.penging == true) {
                this.alertService.warn(resp.message);
            } else {
                this.alertService.info(resp.message);
            }
        }, (err) => {
            //console.log(err);
        });
    }
        
    exportCustomer(customerId: string) {

        this.urlParams = {
            'contextId'     : this.context,
            'customerId': customerId,
            'snapshotDate'  : this.snapshotDateFrom,
            'scenarioId'    : this.scenarioId,
            'portfolioId'   : this.portfolioId,
            'period':this.comparisonPeriod
        };
        

        this.generalService.exportCustomer(this.urlParams, customerId).subscribe(resp => {
            if(resp.exists == true) {
                this.exportParams = {
                    'file'     : resp.fileName                  
                };
                this.generalService.downloadFile(this.exportParams);
            } else if(resp.penging == true) {
                this.alertService.warn(resp.message);
            } else {
                this.alertService.info(resp.message);
            }
        }, (err) => {
            //console.log(err);
        });
    }


}
