import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DataService} from '../../../../services/data.service';
import {GeneralService} from '../../../../services/general.service';

@Component({
    selector: 'app-specific-portfolio',
    templateUrl: './specific-portfolio.component.html',
    styleUrls: ['./specific-portfolio.component.css']
})
export class SpecificPortfolioComponent implements OnInit {

    scenarioPortfolio;

    view = 'table';
    dataTabs = [];
    dataGraph = [];
    dataStats: any;

    //  CHART
    typeChart = 'horizontalBar';

    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService) {
    }

    ngOnInit() {

        //  Place Specific Scenario
        this.dataService.currentScenarioPortfolio.subscribe(scenario => {

            if ( scenario ) {

                this.scenarioPortfolio = scenario;

                //  TITLE CHANGE
                this.changeTitle('Specific Scenario Portfolio: ' + this.scenarioPortfolio.portfolioName);

                this.mouldGraph();

            } else {

                this.router.navigate(["scenarios"]);

            }
        });
    }

    /**
     * MOULD DATA TO GRAPH
     * AREA WHERE YOU NEED TO CHANGE
     */
    mouldGraph(){

        var _this = this;

        Object.keys( this.scenarioPortfolio['statistics'] ).forEach(function(key, value) {

            //  RUN WITHOUGHT DATA
            if( key !== 'portfolioStatistics' ){
                _this.dataTabs.push( key.toUpperCase() );
                _this.dataGraph.push( _this.scenarioPortfolio['statistics'][key] );
            }

            if( key == 'portfolioStatistics'){
                _this.dataStats = _this.scenarioPortfolio['statistics'][key];
            }

        });
    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
    }


}
