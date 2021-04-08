import {Component, OnInit} from '@angular/core';
import {DataService} from '../../../../services/data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {GeneralService} from '../../../../services/general.service';

@Component({
    selector: 'app-specific-prospect',
    templateUrl: './specific-prospect.component.html',
    styleUrls: ['./specific-prospect.component.css']
})
export class SpecificProspectComponent implements OnInit {

    scenarioProspect;

    view = 'table';
    dataTabs = [];
    dataGraph = [];
    dataStats: any;

    //  CHART
    typeChart = 'horizontalBar';

    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService) {
    }

    ngOnInit() {

        //  Place Specific Scenario
        this.dataService.currentScenarioProspect.subscribe(prospect => {

            if ( prospect ) {

                this.scenarioProspect = prospect;

                //  TITLE CHANGE
                this.changeTitle('Specific Scenario Prospect: ' + this.scenarioProspect.parameters.description);

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

        Object.keys( this.scenarioProspect['statistics'] ).forEach(function(key, value) {

            //  RUN WITHOUGHT DATA
            if( key !== 'prospectStatistics' ){
                _this.dataTabs.push( key.toUpperCase() );
                _this.dataGraph.push( _this.scenarioProspect['statistics'][key] );
            }

            if( key == 'prospectStatistics'){
                _this.dataStats = _this.scenarioProspect['statistics'][key];
            }

        });
    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
    }

}
