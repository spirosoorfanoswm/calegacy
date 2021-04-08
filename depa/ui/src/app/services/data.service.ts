import {Injectable, isDevMode} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, BehaviorSubject, of} from 'rxjs';
import * as properties from '../../../datafiles/properties.json';
import * as configInfo from '../../../datafiles/configInfo.json';
import * as contextInfo from '../../../datafiles/contextInfo.json';
import * as riskProfileData from '../../../datafiles/clienteledistribution.json';
import * as statisticsData from '../../../datafiles/clienteleStatistics.json';
import * as statisticsGraphs from '../../../datafiles/clientelestatisticsgraph.json';
import * as clienteleData from '../../../datafiles/customers.json';
import * as customerInfo from '../../../datafiles/customerInfo.json';
import * as customerSearch from '../../../sampleResponseAndSwagger/customerSearch.json';
import {GeneralService} from './general.service';
import {Router} from '@angular/router';

@Injectable()
export class DataService {

    constructor(
        private http: HttpClient,
        private router: Router,
        private generalService: GeneralService) {
    }

    //  ENV check
    menuItems: any = properties;
    configInfo: any = (!isDevMode()) ? configInfo : [];
    contextInfo: any = (!isDevMode()) ? contextInfo : [];
    riskProfileData: any;
    statisticsData: any;
    statisticsGraphs: any = (!isDevMode()) ? statisticsGraphs : [];
    clienteleData: any = (!isDevMode()) ? clienteleData : [];
    customerInfo: any = (!isDevMode()) ? customerInfo : [];
    riskProfilePortfolioData: any = [];
    statisticsPortfolioData: any = [];
    clientelePortfolioData: any = [];
    customerSearch: any;

    private context = new BehaviorSubject('');
    currentContext  = this.context.asObservable();

    private snapshotDateFrom = new BehaviorSubject('');
    currentSnapshotDateFrom = this.snapshotDateFrom.asObservable();

    private comparisonPeriod = new BehaviorSubject('');
    currentComparisonPeriod = this.comparisonPeriod.asObservable();

    private title = new BehaviorSubject('Credit Monitor');
    currentTitle = this.title.asObservable();

    private showInfoSection = new BehaviorSubject(false);
    currentShowInfoSection = this.showInfoSection.asObservable();

    private riskProfileViewData = new BehaviorSubject('');
    currentRiskProfileViewData = this.riskProfileViewData.asObservable();

    private portfolioId = new BehaviorSubject('');
    currentPortfolioId = this.portfolioId.asObservable();

    private contextInfoAll = new BehaviorSubject('');
    currentContextAll  = this.contextInfoAll.asObservable();

    private configInfoAll = new BehaviorSubject('');
    currentConfigInfoAll  = this.configInfoAll.asObservable();

    /**
     * Scenario from Credit Manager
     * @type {BehaviorSubject<string>}
     */
    private specificScenario = new BehaviorSubject('');
    currentScenario  = this.specificScenario.asObservable();

    /**
     * Specific Portfolio for scenario
     * @type {BehaviorSubject<string>}
     */
    private specificScenarioPortfolio = new BehaviorSubject('');
    currentScenarioPortfolio  = this.specificScenarioPortfolio.asObservable();

    /**
     * Specific Prospect for scenario
     * @type {BehaviorSubject<string>}
     */
    private specificScenarioProspect = new BehaviorSubject('');
    currentScenarioProspect  = this.specificScenarioProspect.asObservable();

    private specificScenarioSpecialCustomer = new BehaviorSubject('');
    currentScenarioSpecialCustomer  = this.specificScenarioSpecialCustomer.asObservable();

    changeScenarioSpecialCustomer(customer: any){
        this.specificScenarioSpecialCustomer.next( customer );
    }

    changeScenarioProspect(scenario: any){
        this.specificScenarioProspect.next(scenario);
    }

    changeScenarioPortfolio(scenario: any){
        this.specificScenarioPortfolio.next(scenario);
    }

    changeScenario(scenario: any){
        this.specificScenario.next(scenario);
    }

    changeConfigAll(config: string) {
        this.configInfoAll.next(config);
    }

    changeContextAll(context: string) {
        this.contextInfoAll.next(context);
    }

    changeContexts(context: string) {
        this.context.next(context);
    }

    changeSnapshotDateFrom(snapshotDateFrom: string) {
        this.snapshotDateFrom.next(snapshotDateFrom);
    }

    changeComparisonPeriod(comparisonPeriod: string) {
        this.comparisonPeriod.next(comparisonPeriod);
    }

    changeTitle(title: string) {
        this.title.next(title);
    }

    changeShowInfoSection(showInfoSection: boolean) {
        this.showInfoSection.next(showInfoSection);
    }

    changeRiskProfileViewData(riskProfileViewData: string) {
        this.riskProfileViewData.next(riskProfileViewData);
    }

    /**
     * Client Data
     * @param {Array} clientData
     */
    changeClienteleData(clientData: any) {
        this.customerInfo.next(clientData);
    }

    getMenuItems(): Observable<any> {
        return of(this.menuItems.default);
    }

    getConfigInfo(): Observable<any> {
        return of(this.configInfo.default);
    }

    getContextInfo(): Observable<any> {
        return of(this.contextInfo.default);
    }

    getRiskProfileData(): Observable<any> {
        return of(this.riskProfileData);
    }

    getStatisticsData(snapshotDateFrom: string, comparisonPeriod: string): Observable<any> {
        return of(this.statisticsData.default);
    }

    getStatisticsGraphs(snapshotDateFrom: string, comparisonPeriod: string): Observable<any> {
        return of(this.statisticsGraphs.default);
    }

    getStatisticsPortfolioData(snapshotDateFrom: string, comparisonPeriod: string, portfolioId: string): Observable<any> {
        return null;
    }

    getTitle(): Observable<any> {
        return of(this.title);
    }

    getClienteleData(): Observable<any> {
        return of(this.clienteleData.default);
    }

    getCustomerInfo(customerId: string): Observable<any> {
        return of(this.customerInfo.default);
    }

    getSearchCustomer(): Observable<any> {
        return of(this.customerSearch.default);
   }

}
