import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {GeneralService} from '../../services/general.service';
import {Subscription} from 'rxjs';

@Component({
    selector: 'app-specific-scenario',
    templateUrl: './specific-scenario.component.html',
    styleUrls: ['./specific-scenario.component.css']
})
export class SpecificScenarioComponent implements OnInit {

    public scenario: any;
    view = 'table';
    dataTabs = [];
    dataGraph = [];
    dataStats: any;
    searchParams;

    context: string;
    snapshotDateFrom: string;
    comparisonPeriod: string;

    sub1: Subscription;
    sub2: Subscription;
    sub3: Subscription;

    firstLoad = true;
    loadComponent = 'home';

    //  CHART
    typeChart = 'horizontalBar';

    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService) {
    }

    ngOnInit() {
        //  Place Specific Scenario
        this.dataService.currentScenario.subscribe(scenario => {
            if ( scenario ) {
                
                this.scenario = scenario;
                this.changeTitle('Specific Scenario Overview : ' + this.scenario.scenarioName);
                this.mouldGraph();
                this.initSubscriptions();
            } else {
                this.router.navigate(["scenarios"]);
            }
        });
    }

    mouldGraph() {
        var _this = this;
        Object.keys( this.scenario['statistics'] ).forEach(function(key, value) {

            //  RUN WITHOUGHT DATA
            if( key !== 'scenarioStatistics'){
                _this.dataTabs.push( key.toUpperCase() );
                _this.dataGraph.push( _this.scenario['statistics'][key] );
            }

            if( key == 'scenarioStatistics'){
                _this.dataStats = _this.scenario['statistics'][key];
            }

        });

    }

    /**
     *  Init Subscriptions to wait for changes
     */
    initSubscriptions() {
        this.sub1 = this.dataService.currentContext.subscribe(context => {
            this.context = context;
            if(!this.firstLoad && this.router.url.startsWith("/scenario")){
                this.router.navigate(["scenarios"]);
            }
        });

        this.sub2 = this.dataService.currentSnapshotDateFrom.subscribe(snapshotDateFrom => {

            this.snapshotDateFrom = snapshotDateFrom;

            if(!this.firstLoad && this.router.url.startsWith("/scenario")){
                this.router.navigate(["scenarios"]);
            }
        });

        this.sub3 = this.dataService.currentComparisonPeriod.subscribe(comparisonPeriod => {
            this.comparisonPeriod = comparisonPeriod;
            if(!this.firstLoad && this.router.url.startsWith("/scenario")){
                this.router.navigate(["scenarios"]);
            }
            this.firstLoad = false;
        });
    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
    }

    loadMyChildComponent(component){
        if(this.scenario && this.scenario.scenarioName) {
            this.changeTitle('Specific Scenario Overview : ' + this.scenario.scenarioName);
        }        
        this.loadComponent = component;
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

}
