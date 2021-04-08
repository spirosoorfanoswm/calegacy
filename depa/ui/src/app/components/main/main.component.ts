import {Component, Input, isDevMode, OnChanges, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DataService} from '../../services/data.service';
import {GeneralService} from '../../services/general.service';
import {DatePipe} from '@angular/common';

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

    title: string;
    businessUnits: string[];
    allow:boolean;
    dateFrom: any;
    comparisonPeriod: any;
    businessUnit: any;

    showHideBusinesUnit = false;
    showHideDateFrom = false;
    showHideComparisonPeriod = false;

    //Check contextinfo json > snapshotDates (to be translated from millis to date with yyyy-mm-dd)
    options: any[];
    periods: any[];
    //Check configinfo json > contexts
    businessUnitData: any = {
        26: 'Option A'
    };
    

    constructor(private router: Router,
                private route: ActivatedRoute,
                private dataService: DataService,
                private generalService: GeneralService,
                private datePipe: DatePipe) {
    }

    onBlurDateFromMethod() {
        this.showHideDateFrom = false;
    }

    onBlurBusinesUnitMethod() {
        this.showHideBusinesUnit = false;
    }
    onBlurPeriodMethod() {
        this.showHideComparisonPeriod = false;
    }


    ngOnInit() {
        
        this.allow = true;
        //  TITLE OF COMPONENT
        this.dataService.getTitle().subscribe(data => {
            this.title = data;
        });

        //  LOAD CONFIG AND CONTACT INFO
        let params = {};
        this.generalService.getConfigInfo(params)
            .subscribe(
                info => {

                    this.dataService.changeContexts(Object.keys(info.contexts)[0]);

                    this.businessUnitData = info.contexts;

                    this.businessUnit = this.businessUnitData[Object.keys(this.businessUnitData)[0]];

                    let params = {'contextId': Object.keys(this.businessUnitData)[0]};

                    this.generalService.getContextInfo(params)
                        .subscribe(contextInfo => {
                            this.options = contextInfo.snapshotDates;

                            this.periods = contextInfo.comparisonPeriods;

                            this.dateFrom = this.options[0];

                            this.comparisonPeriod = contextInfo.comparisonPeriods[0]['period'];

                            let dateTransform =  this.datePipe.transform(this.options[0], 'yyyy-MM-dd');

                            this.dataService.changeConfigAll(info);

                            this.dataService.changeContextAll(contextInfo);

                            this.dataService.changeSnapshotDateFrom(dateTransform);

                            this.dataService.changeComparisonPeriod(contextInfo.comparisonPeriods[0]['period']);
                        });
                }
            );

        this.dataService.currentTitle.subscribe(title => {
            this.title = title;
        });

    }

    /**
     * DATE FROM
     * @param {string} value
     */
    changeSnapshotDateFrom(value: string) {
        this.allow = false;
        this.showHideDateFrom = false;
        /*setTimeout(() => {
            this.allow = true;
        }, 2000);*/

       
        let dateTransform =  this.datePipe.transform(value, 'yyyy-MM-dd');
        this.dataService.changeSnapshotDateFrom(dateTransform);
        this.dateFrom = dateTransform;
    }

    /**
     * DATE TO
     * @param {string} value
     */
    changeComparisonPeriod(value: string) {
        this.showHideComparisonPeriod = false;
        this.dataService.changeComparisonPeriod(value);
        this.comparisonPeriod = value;
    }

    /**
     * BUSINESS UNIT
     * @param {string} value
     */
    changeBusinessUnitValue(obj: any) {
        this.showHideBusinesUnit = false;
        this.dataService.changeContexts(obj.key);
        this.businessUnit = obj.value;
    }

}