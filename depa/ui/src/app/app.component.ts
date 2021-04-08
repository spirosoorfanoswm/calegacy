import {Component, isDevMode, OnChanges, OnInit} from '@angular/core';
import {routerTransition} from './animations';
import {Location} from '@angular/common';
import {DataService} from './services/data.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    animations: [ routerTransition ],
})
export class AppComponent implements OnInit, OnChanges {

    title = 'creditark';
    urlOpen: string;

    constructor(
        private dataService: DataService,
        private location: Location,

    ) {
    }

    ngOnInit() {
        
    }

    ngOnChanges(){

    }
          
      handleMessage(message){
        console.log("message")
      }

    /**
     *
     * @param outlet
     * @returns {any}
     */
    getState(outlet) {
        return outlet.activatedRouteData.state;
    }

}


