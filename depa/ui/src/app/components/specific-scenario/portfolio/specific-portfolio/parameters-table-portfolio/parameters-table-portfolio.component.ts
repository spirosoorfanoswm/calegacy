import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DataService} from '../../../../../services/data.service';
import {GeneralService} from '../../../../../services/general.service';
import {FormBuilder} from '@angular/forms';
import {Subscription} from 'rxjs';
import { AlertService } from './../../../../../_alert';

@Component({
  selector: 'app-parameters-table-portfolio',
  templateUrl: './parameters-table-portfolio.component.html',
  styleUrls: ['./parameters-table-portfolio.component.css']
})
export class ParametersTablePortfolioComponent implements OnInit {

    model: any = {};

    scenario: any;
    @Input() scenarioPortfolio: any;

    //  Rollback if Error On Submit
    scenarioSession: any;
    urlParams = {};

    contextAll: any;

    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    allow: boolean;

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

        });

        //  GET ContextINfo
        this.dataService.currentContextAll.subscribe(context => {

            this.contextAll = context;

        });

        //  Copy the Old Object for any errors
        this.scenarioSession = JSON.parse(JSON.stringify(this.scenarioPortfolio));

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
     * Submit or Rollback
     */
    onSubmit() {

        //  REMOVE STATISTICS FROM ARRAY
        delete this.scenario["statistics"];


        //  Change the parameters in current Porfolio
        this.scenario['portfolios'].forEach( (element, key) => {

            if (element.portfolioId == this.scenarioPortfolio.portfolioId){

                //  DELETE STATISTICS
                delete this.scenario["portfolios"][key]['statistics'];

                this.scenario['parameters'][key] = this.scenarioPortfolio.parameters;

            }
        });

        this.allow = false;
        //  LETS SEND THE REQUEST OVER
        this.urlParams = {
            'contextId'     : this.context,
            'snapshotDate'  : this.snapshotDateFrom,
            //  Only place Specific Scenario
            'scenarios'     : [ this.scenario ]
        };
       // this.alertService.loadSpinner('loader');
        this.generalService.putScenario( this.urlParams).subscribe(data => {

            this.alertService.info('Your changes have been saved. Please wait until scenario processing is finished. Thank you');
            this.allow = true;
            // Update Scenario on Service
            this.dataService.changeScenario( data );
            this.router.navigate(["/scenarios"]);

        }, (err) => {
            this.allow = true;
            if(err.error && err.error.message) {
                this.alertService.error(err.error.message);
            } else {
                this.alertService.error('There was an error on update, your changes have been rolled back.');
            }
            //this.alertService.error('There was an error on update, your changes have been rolled back.');
            //this.scenarioPortfolio = this.scenarioSession;
            //this.router.navigate(["/scenarios"]);
            
        });
    }

   
}
