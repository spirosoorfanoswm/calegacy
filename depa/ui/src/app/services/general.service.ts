import {Injectable, isDevMode} from '@angular/core';
import { ICustomer} from '../_interface/customer';
import { HttpClient, HttpRequest, HttpEvent, HttpResponse} from '@angular/common/http';
import { Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class GeneralService{

    private _url: string = environment.url;
    private _info        = environment.configinfo;
    private _clientdistr = environment.clientdistribution;
    private _clientstats = environment.clientelestatistics;
    private _clientgraph = environment.clientelestatisticsgraph;
    private _contextInfo = environment.contextinfo;
    private _customers   = environment.customers;
    private _exportcustomers   = environment.exportcustomers;
    private _exportcustomer   = environment.exportcustomer;
    private _exportclienteledistribution   = environment.exportclienteledistribution;
    private _exportstatistics   = environment.exportstatistics;
    private _download   = environment.download;
    private _customer   = environment.customer;
    private _customersDev   = environment.customersDev;
    private _searchcustomers = environment.searchcustomers;
    private _scenarios = environment.scenarios;
    //private _modelvalidation = environment.modelvalidation;
    private _scenarios_put_post_delete = environment.scenarios_put_post_delete;
    private _scenario_template = environment.scenario_template;
    private _export_reports   = environment.downloadreportsfile;

    constructor(private _http: HttpClient){}

    /**
     * GET CONFIG DATA
     * @param params
     * @returns {Observable<any>}
     */
    getConfigInfo(params): Observable<any> {
        return this._http.get<any>( this._url + this._info, {
            params: params,
        });
    }

    /**
     *
     * @param params
     * @returns {Observable<any>}
     */
    getClientDistribution(params): Observable<any> {
        return this._http.get<any>( this._url + this._clientdistr, {
            params: params,
        });
    }

    exportClientDistribution(params) : Observable<any>{
        return this._http.get<any>( this._url + this._exportclienteledistribution, {
            params: params,
        });
        

        //window.open(this._download+"?file="+params.file, '_blank', 'location=yes,height=570,width=520,scrollbars=yes,status=yes');
    }

    exportStatistics(params) : Observable<any> {
       return this._http.get<any>( this._url + this._exportstatistics, {
            params: params,
        });
    }

    /**
     * Statistics Data
     * @param params
     * @returns {Observable<any>}
     */
    getStatisticsData(params): Observable<any> {
        return this._http.get<any>( this._url + this._clientstats, {
            params: params,
        });
    }

    /**
     * Statistics Graph
     * @param params
     * @returns {Observable<any>}
     */
    getStatisticsGraphs(params): Observable<any> {
        return this._http.get<any>( this._url + this._clientgraph, {
            params: params,
        });
    }

    /**
     * GET CONTEXT INFO
     * @param params
     */
    getContextInfo(params): Observable<any> {
        return this._http.get<any>( this._url + this._contextInfo, {
            params: params,
        });
    }

    /**
     * Get Customers
     * @returns {Observable<ICustomer[]>}
     */
    getCustomers(params): Observable<any> {
        return this._http.get<any>( this._url + this._customers, {
            params: params,
        });
    }

    exportCustomers(params): Observable<any>{
        return this._http.get<any>( this._url + this._exportcustomers, {
            params: params,
        });
    }

    exportCustomer(params, customerId): Observable<any>{
        return this._http.get<any>( this._url + this._exportcustomer + '/' + customerId, {
            params: params,
        });
    }

    downloadReportFile(params) : Observable<any>{
        return this._http.get<any>( this._url + this._download, {
            params: params,
        });
    }

    downloadFile(params) {
        window.open(this._download+"?file="+params.file, '_blank', 'location=yes,height=570,width=520,scrollbars=yes,status=yes');
      }

    getCustomerInfo(params, customerId): Observable<any> {
        return this._http.get<any>( this._url  + this._customers + '/' + customerId, {
            params: params,
        });
      // return this._http.get<any>( this._url  + this._customer);
    }

    getCustomerInfoTemp(params, customerId): Observable<any> {
        return this._http.get<any>( this._url  + this._customer);
    }

    getCustomerInfoDev(params, customerId): Observable<any> {
        return this._http.get<any>( this._url  + this._customersDev, {
            params: params,
        });
    }

    getSearchCustomer(params, searchKey): Observable<any> {
        //return this._http.get<any>( this._url  + this._searchcustomers);
        return this._http.get<any>( this._url  + this._searchcustomers + '/' + searchKey , {
            params: params,
        });
    }

    getSearchCustomerTemp(params, searchKey): Observable<any> {
        return this._http.get<any>( this._url  + this._searchcustomers);
    }

    /**
     * Get the template for Scenario
     * @param params
     * @returns {Observable<any>}
     */
    getTemplateScenario(params): Observable<any> {
        return this._http.get<any>( this._url + this._scenario_template, {
            params: params,
        });
    }

    /**
     * Create a scenario
     * @param params
     * @returns {Observable<any>}
     */
    createScenario(params): Observable<any> {
        return this._http.post<any>( this._url + this._scenarios_put_post_delete, 
            params,
        );
    }

    /**
     * Scenarios
     * @param params
     * @param searchKey
     * @returns {Observable<any>}
     */
    getScenarios(params): Observable<any> {
        return this._http.get<any>( this._url  + this._scenarios, {
            params: params,
        });
    }

    /**
     * Scenarios
     * @param params
     * @param searchKey
     * @returns {Observable<any>}
     */
    /*getAvailableModels(params): Observable<any> {
        return this._http.get<any>( this._url  + this._modelvalidation, {
            params: params,
        });
    }*/

    /**
     * PUT SCENARIO
     * @param params
     * @returns {Observable<any>}
     */
    putScenario(params): Observable<any> {
        return this._http.put<any>( this._url  + this._scenarios_put_post_delete, params,
        );
    }

    deleteScenario(params): Observable<any> {
        return this._http.delete<any>( this._url  + this._scenarios_put_post_delete, {
            params: params,
        });
    }

    exportReports() {
        window.open(this._export_reports, '_blank', 'location=yes,height=570,width=520,scrollbars=yes,status=yes');
      }

}