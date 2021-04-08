import {Component, Output, EventEmitter, Input, OnInit, OnChanges} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DataService} from '../../../services/data.service';
import {GeneralService} from '../../../services/general.service';

@Component({
    selector: 'app-portfolio',
    templateUrl: './portfolio.component.html',
    styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit, OnChanges {

    @Input() scenario: any;

    scenarioPortfolios: any;
    order: boolean = true;
    searchParams;
    sortParams;
    loadedPortfolio = false;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService) {
    }

    ngOnInit() {



        this.scenarioPortfolios = this.scenario.portfolios;
        this.changeTitle('Specific Scenario Portfolios');

    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
    }

    ngOnChanges() {}

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
     *
     * @param f
     * @param key
     */
    sortResults(f, key) {

        let toSort = f.value;

        (this.order) ? this.order = false : this.order = true;

        toSort['key'] = key;
        toSort['order'] = this.order;

        this.sortParams = [ toSort ];

    }

    /**
     * Show Info
     * @param {string} customerId
     */
    showInfo(portfolio: any) {


        this.dataService.changeScenarioPortfolio(portfolio);
        this.loadedPortfolio = true;

    }

    homePortfolio(){

        this.loadedPortfolio = false;

    }

}
