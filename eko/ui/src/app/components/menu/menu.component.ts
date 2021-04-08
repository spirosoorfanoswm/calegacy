import {Component, OnChanges, OnInit} from '@angular/core';
import {DataService} from '../../services/data.service';
import {ActivatedRoute, Router} from '@angular/router';
import { Location } from '@angular/common';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, OnChanges {
    clientelePortfolios: any;
    menuItems: any;
    selectedParentItem: any;
    selectedChildItem: any;
    selectedChildItemInner: any;
    subPortfolio: any;
    isOpenMenu: boolean = false;

    constructor(
        private dataService: DataService,
        private location: Location ) {
    }

    ngOnInit() {

        //  GET MENU
        this.getMenu();

        this.dataService.currentContextAll.subscribe(contextInfo => {
            this.clientelePortfolios = contextInfo['portfolios'];
        });

    }

    ngOnChanges(){

    }

    changeTitle(title: string) {
        this.dataService.changeTitle(title);
    }

    getMenu(){

        this.dataService.getMenuItems().subscribe(menuItems => {
            this.menuItems = menuItems;

            let location = this.location.path().replace('/','');

            this.menuItems.firstLevelMenuItems.forEach( (menu, index) => {

                if ( location === menu.link) {
                    this.selectedParentItem = this.menuItems.firstLevelMenuItems[index];
                }

                menu.secondLevelMenuItems.forEach( secondLevel => {

                    if ( location === secondLevel.link) {
                        this.selectedParentItem = this.menuItems.firstLevelMenuItems[index];
                        this.selectedChildItem = secondLevel;
                    }
                });
            });

        });
    }

    /**
     * Parent Click
     * @param event
     * @param newValue
     */
    listParentClick(event, newValue) {
        //  TOGGLE
        (this.selectedParentItem === newValue) ? this.selectedParentItem = null : this.selectedParentItem = newValue;

    }

    /**
     * Child Click
     * @param event
     * @param newValue
     */
    listChildClick(event, newValue) {

        //  TOGGLE
        this.selectedChildItemInner = null;

        //  TOGGLE
        (this.selectedChildItem === newValue) ? this.selectedChildItem = null : this.selectedChildItem = newValue;
    }

    /**
     * Child Click
     * @param event
     * @param newValue
     */
    listChildClickInner(event, newValue) {

        //  TOGGLE
        this.selectedChildItemInner = newValue;
    }

}