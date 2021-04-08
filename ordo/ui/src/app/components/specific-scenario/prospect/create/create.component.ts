import {Component, OnInit} from '@angular/core';
import {Prospect} from '../../../../models/newProspect.model';
import {Subscription} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {DataService} from '../../../../services/data.service';
import {GeneralService} from '../../../../services/general.service';
import {FormBuilder} from '@angular/forms';
import { AlertService } from './../../../../_alert';

@Component({
    selector: 'app-create',
    templateUrl: './create.component.html',
    styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

    newProspect: any;

    scenario: any;

    //  Rollback if Error On Submit
    scenarioSession: any;

    urlParams = {};
    contextAll:any;

    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;

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
            this.scenarioSession = JSON.parse(JSON.stringify( this.scenario ));

        });

        //  GET ContextINfo
        this.dataService.currentContextAll.subscribe(context => {

            this.contextAll = context;

        });

        //  Create a new Prospect Class
        var newProspect = new Prospect;
        


        this.newProspect = newProspect.newProspect;
     
        this.newProspect.parameters.mitigents = this.contextAll.mitigantDescriptions;

        this.contextAll.mitigantDescriptions.forEach( elem => {
            this.newProspect.parameters.mitigantsValues.push('0');
        });


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

        //  PUSH NEW PROSPECT TO SCENARIO
        this.scenario.prospects.push( this.newProspect );

       

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
        this.allow = false;
        //  LETS SEND THE REQUEST OVER
        this.urlParams = {
            'contextId': this.context,
            'snapshotDate': this.snapshotDateFrom,
            //  Only place Specific Scenario
            'scenarios': [this.scenario]
        };
       // this.alertService.loadSpinner('loader');
        this.generalService.putScenario(this.urlParams).subscribe(data => {
            this.allow = true;
        
            this.alertService.info('Your changes have been saved. Please wait until scenario processing is finished. Thank you');

            // Update Scenario on Service
            this.dataService.changeScenario( data );
            this.router.navigate(["/scenarios"]);

        }, (err) => {
            const index = this.scenario.prospects.indexOf(this.newProspect);
            this.scenario.prospects.splice(index, 1);     
            if(err.error && err.error.message) {
                this.alertService.error(err.error.message);
            } else {
                this.alertService.error('There was an error on update, your changes have been rolled back.');
            }

            this.allow = true;
           // this.alertService.error('There was an error on update, your changes have been rolled back.');
            this.scenario = this.scenarioSession;
            //this.router.navigate(["/scenarios"]);

        });
    }

}
