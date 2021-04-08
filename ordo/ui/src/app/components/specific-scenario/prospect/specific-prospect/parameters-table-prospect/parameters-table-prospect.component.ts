import {Component, Input, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {FormBuilder} from '@angular/forms';
import {DataService} from '../../../../../services/data.service';
import {GeneralService} from '../../../../../services/general.service';
import {ActivatedRoute, Router} from '@angular/router';
import { AlertService } from './../../../../../_alert';

@Component({
    selector: 'app-parameters-table-prospect',
    templateUrl: './parameters-table-prospect.component.html',
    styleUrls: ['./parameters-table-prospect.component.css']
})
export class ParametersTableProspectComponent implements OnInit {

    model: any = {};

    scenario: any;
    @Input() scenarioProspect: any;

    //  Rollback if Error On Submit
    scenarioSession: any;
    urlParams = {};

    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;

    contextAll: any;

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
        this.allow = false;
        //  REMOVE STATISTICS FROM ARRAY
        delete this.scenario["statistics"];

        //  REMOVE STATISTICS FROM PORTFOLIOS
        this.scenario['portfolios'].forEach( (element, key) => {
            //  DELETE STATISTICS
            delete this.scenario["portfolios"][key]['statistics'];
        });

        //  REMOVE STATISTICS FROM PROSPECTS
        this.scenario['prospects'].forEach( (element, key) => {
            //  DELETE STATISTICS
            delete this.scenario["prospects"][key]['statistics'];
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

            if(err.error && err.error.message) {
                this.alertService.error(err.error.message);
            } else {
                this.alertService.error('There was an error on update, your changes have been rolled back.');
            }

            //this.alertService.error('There was an error on update, your changes have been rolled back.');
            this.scenario = this.scenarioSession;
            //this.router.navigate(["/scenarios"]);
        });
    }

}
