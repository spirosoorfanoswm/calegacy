import {Component, OnInit, OnDestroy, isDevMode, ViewChild} from '@angular/core';
import {Router, ActivatedRoute, NavigationExtras} from '@angular/router';
import {DataService} from '../../services/data.service';
import {GeneralService} from '../../services/general.service';
import { Subscription } from 'rxjs';
import {NgForm} from '@angular/forms';
import { AlertService } from './../../_alert';

@Component({
  selector: 'app-scenarios',
  templateUrl: './scenarios.component.html',
  styleUrls: ['./scenarios.component.css']
})
export class ScenariosComponent implements OnInit, OnDestroy {

    @ViewChild('f', {static: false}) createScenarioForm: NgForm;
    data: any;
    title: string;
    order: boolean = true;
    searchParams;
    sortParams;

    //  When creating a new Scenario
    newScenarioTemplate: any;

    display = 'none';

    //  PAGINATION
    itemsPerPageLoad = 100;
    totalItemsLoad   = 200;

    scenariosCount: number;
    urlParams = {};
    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;
    portfolioId: any;
    p: any;
    allow:boolean;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    sub4: Subscription;
    sub5: Subscription;
    sub6: Subscription;
    params: any;
    displayDelete = 'none';
    deleteScenario:any;
    deleteScenarioIndex:any;
    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.allow = true;

        //  TILTE CHANGE
        this.changeTitle('Scenarios');

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

                        this.portfolioId = (this.router.url === '/scenarios') ? -1 : params.get('portfolioId');
                        this.urlParams = {
                            contextId     : this.context,
                            snapshotDate  : this.snapshotDateFrom
                        };
                       
                        this.generalService.getScenarios(this.urlParams).subscribe(data => {

                           

                            this.data = data.scenarios;

                            this.scenariosCount = data.scenarios.length;

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
    showInfo(scenario: any) {
        
        if(scenario.pending === true) {
            this.dataService.changeScenario(scenario);
            this.router.navigate(["/scenarios"]);
            this.alertService.warn('Scenario has pending actions. Please check again later');
            
        } else {
            this.dataService.changeScenario(scenario);
            this.router.navigate(['specific-scenario']);
        }
    }

    deleteCompleteScenario(){
        this.allow = false;
        //  LETS SEND THE REQUEST OVER
        this.urlParams = {
            'contextId'     : this.context,
            //  Only place Specific Scenario
            'scenarioId'     : this.deleteScenario.scenarioId
        };
        this.displayDelete = 'none';
        /*console.log(this.deleteScenarioId.scenarioId);
        (async () => { 
            // Do something before delay
            console.log('before delay')
    
            await this.delay(5000);
    
            // Do something after
            console.log('after delay')
            this.allow = true;
        })();*/
        this.generalService.deleteScenario( this.urlParams).subscribe(data => {
            this.allow = true;
            this.alertService.info('Your changes have been saved. Please wait until scenario processing is finished. Thank you');
            this.data.splice(this.deleteScenarioIndex, 1);

        }, (err) => {
            this.allow = true;
            this.alertService.error('There was an error on deleting your scenario with ID: ' +  this.deleteScenario.scenarioId);     
        });

    }

    sortResults(f, key) {
        let toSort = f.value;

        (this.order) ? this.order = false : this.order = true;

        toSort['key'] = key;
        toSort['order'] = this.order;

        this.sortParams = [ toSort ];

    }

    /**
     * Filter Search: TEXT
     * @param f
     * @param key
     */
    filterResults(f, key) {

        const toSearch = f.value;

        toSearch.key = key;

        this.searchParams = toSearch;

    }

    changeTitle(title: string) {

        this.dataService.changeTitle(title);

    }

    /**
     * Server Side Pagination
     * @param p
     */
    currentPage(p) {

        const currentPage = p - 1;

        //  LOAD BUCKET
        this.urlParams = {
            contextId     : this.context,
            snapshotDate  : this.snapshotDateFrom,
            scenarioId    : -1,
            portfolioId   : this.portfolioId,
            length        : this.itemsPerPageLoad,
            offset        : currentPage * this.itemsPerPageLoad,
            sortDescending: true,
            sortColumn    : 6
        };

        this.generalService.getCustomers(this.urlParams).subscribe(data => {

            this.data = data.customers;

        });

    }

    onCloseHandled() {
        this.allow = true;
        this.display = 'none';
        this.createScenarioForm.reset();
    }

    /**
     * Create New SCenario
     */
    newScenario() {

        //  LOAD BUCKET
        this.urlParams = {
            contextId     : this.context,
            snapshotDate  : this.snapshotDateFrom,
            comparisonPeriod: this.comparisonPeriod
        };

        this.generalService.getTemplateScenario(this.urlParams).subscribe(data => {
            this.newScenarioTemplate = data.scenarios[0];
            this.newScenarioTemplate.scenarioName = 'My Scenario';
            this.display = 'block';
           
        });
    }

    /**
     *  Create Scenario
     */

    delay(ms: number) {
        return new Promise( resolve => setTimeout(resolve, ms) );
    }

    createScenario(){
        this.allow = false;

        this.urlParams = {
            contextId       : this.context,
            snapshotDate    : this.snapshotDateFrom,
            comparisonPeriod: this.comparisonPeriod,
            scenarios       : [ this.newScenarioTemplate ]
        };
        this.generalService.createScenario(this.urlParams).subscribe(data => {
        
            this.alertService.info('Your changes have been saved. Please wait until scenario processing is finished. Thank you');
            this.onCloseHandled();
            this.dataService.changeScenario( data );
            this.allow = true;
            this.urlParams = {
                contextId     : this.context,
                snapshotDate  : this.snapshotDateFrom
            };   
            this.generalService.getScenarios(this.urlParams).subscribe(data => {  
                this.data = data.scenarios;
                this.scenariosCount = data.scenarios.length;
                this.allow = true;
                this.router.navigate(["/scenarios"]);
            });

        },(err) => {
            this.allow = true;
            this.alertService.error('There was an error on creating new scenario, your changes have been rolled back.');
            this.onCloseHandled();
            this.router.navigate(["/scenarios"]);
        });
    }


    
    onCloseHandledModal() {
        this.displayDelete = 'none';
        this.deleteScenario = null;
        this.deleteScenarioIndex = null;
    }
    openModal(scenario:any, i:any) {
        this.deleteScenarioIndex = i;
        this.deleteScenario = scenario;
        console.log(this.deleteScenario);
        this.displayDelete = 'block';
    }
}
