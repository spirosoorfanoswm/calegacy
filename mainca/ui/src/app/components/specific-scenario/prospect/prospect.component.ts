import {Component, Output, EventEmitter, Input, OnInit, OnChanges} from '@angular/core';
import {DataService} from '../../../services/data.service';
import {GeneralService} from '../../../services/general.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import { AlertService } from './../../../_alert';

@Component({
    selector: 'app-prospect',
    templateUrl: './prospect.component.html',
    styleUrls: ['./prospect.component.css']
})
export class ProspectComponent implements OnInit {

    @Input() scenario: any;

    scenarioProspects: any;
    searchParams;
    loadedProspect = false;
    createProspect = false;

    urlParams = {};

    context: any;
    comparisonPeriod: string;
    snapshotDateFrom: string;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;
    allow:boolean;
    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.scenarioProspects = this.scenario.prospects;
        this.allow = true;
        this.initSubscriptions();
        this.changeTitle('Specific Scenario Prospects');
    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
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
     * Filter Search: TEXT
     * @param f
     * @param key
     */
    filterResults(f, key) {

        let toSearch = f.value;

        toSearch['key'] = key;

        this.searchParams = toSearch;

    }

    /**
     * Show Info
     * @param {string} customerId
     */
    showInfo( prospect: any ) {

        this.dataService.changeScenarioProspect(prospect);
        this.loadedProspect = true;
        this.createProspect = false;

    }

    newProspect(){

        this.createProspect = true;

    }

    homeProspect(){

        this.loadedProspect = false;
        this.createProspect = false;

    }

    deleteScenario( i, scenario ){
       

        if( confirm("Are you sure you want to proceed to delete the propect: " + scenario.parameters.description)) {
            this.allow = false;
            var deletedProspectId = scenario.parameters.prospectId;

            this.scenarioProspects.splice(i, 1);

            //  Place Deleted in the Specific Scenario
            this.scenario['deletedProspects'] = [deletedProspectId];

            //  LETS SEND THE REQUEST OVER
            this.urlParams = {
                'contextId'     : this.context,
                'snapshotDate'  : this.snapshotDateFrom,
                //  Only place Specific Scenario
                'scenarios'     : [ this.scenario ]
            };
            //this.alertService.loadSpinner('loader');
            this.generalService.putScenario( this.urlParams).subscribe(data => {
                this.allow = true;
                this.alertService.info('Your changes have been saved. Please wait until scenario processing is finished. Thank you');

                // Update Scenario on Service
                this.dataService.changeScenario( this.scenario );
                this.router.navigate(["/scenarios"]);

            }, (err) => {
                this.allow = true;
                this.alertService.error('There was an error on update, your changes have been rolled back.');
                this.router.navigate(["/scenarios"]);
            });
        }
    }
}
