import {Component, OnInit, Input, isDevMode} from '@angular/core';
import {NgForm, FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms';
import {GeneralService} from '../../../services/general.service';
import {Subscription} from 'rxjs';
import {DataService} from '../../../services/data.service';
import {ActivatedRoute, Router} from '@angular/router';
import { AlertService } from './../../../_alert';

@Component({
    selector: 'app-parameters-table',
    templateUrl: './parameters-table.component.html',
    styleUrls: ['./parameters-table.component.css']
})
export class ParametersTableComponent implements OnInit {

    model: any = {};
    contextAll: any;

    @Input() scenario: any;

    //  Rollback if Error On Submit
    scenarioSession: any;
    urlParams = {};

    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    allow:boolean;
    display = 'none';

    constructor(private router: Router,
                private route: ActivatedRoute,
                private formBuilder: FormBuilder,
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

        //  Copy the Old Object for any errors
        this.scenarioSession =JSON.parse(JSON.stringify( this.scenario ));

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
        this.scenario["portfolios"].forEach( (portfolio,key) => {
            delete this.scenario["portfolios"][key]['statistics'];
        });

        delete this.scenario["portfolios"]['statistics'];

        //  LETS SEND THE REQUEST OVER
        this.urlParams = {
            'contextId'     : this.context,
            'snapshotDate'  : this.snapshotDateFrom,
            //  Only place Specific Scenario
            'scenarios'     : [ this.scenario ]
        };
       // this.alertService.loadSpinner('loader');
        this.generalService.putScenario( this.urlParams).subscribe(data => {
            this.allow = true;
            this.alertService.info('Your changes have been saved. Please wait until scenario processing is finished. Thank you');
            this.dataService.changeScenario( data );
            this.router.navigate(["/scenarios"]);

        }, (err) => {
            if(err.error && err.error.message) {
                this.alertService.error(err.error.message);
            } else {
                this.alertService.error('There was an error on update, your changes have been rolled back.');
            }
            this.allow = true;
           
            this.scenario = this.scenarioSession;
            //this.router.navigate(["/scenarios"]);
        });
    }

    /**
     * Delete Scenario
     * @param {number} scenarioId
     */
    deleteCompleteScenario(scenarioId: number){
        this.allow = false;
        //  LETS SEND THE REQUEST OVER
        this.urlParams = {
            'contextId'     : this.context,
            //  Only place Specific Scenario
            'scenarioId'     : scenarioId
        };
       // this.alertService.loadSpinner('loader');
        this.generalService.deleteScenario( this.urlParams).subscribe(data => {
            this.allow = true;
            this.alertService.info('Your changes have been saved. Please wait until scenario processing is finished. Thank you');
            this.router.navigate(["/scenarios"]);

        }, (err) => {
            this.allow = true;
            this.alertService.error('There was an error on deleting your scenario with ID: ' + this.scenario.scenarioId);
            this.router.navigate(["/scenarios"]);
        });

    }

    openModal() {
        this.display = 'block';
    }

    onCloseHandled() {
        this.display = 'none';
    }

    isNumberOrPercentage(input: any) {
        if(isNaN(input)) {
            return false;
        } else {
            return true;
        }
    }
   
}
