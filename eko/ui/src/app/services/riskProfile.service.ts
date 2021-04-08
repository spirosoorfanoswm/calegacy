import {Injectable, isDevMode} from '@angular/core';
import { ICustomer} from '../_interface/customer';
import { HttpClient} from '@angular/common/http';
import { Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class RiskProfileService{

    private _url: string =  environment.url;
    private _clienteledistribution: string =  environment.clientdistribution;

    constructor(private _http: HttpClient){}

    /*
    e.g.  /repository/clienteledistribution?comparisonPeriod=1&contextId=4&portfolioId=1&snapshotDate=2018-05-31 
    comparisonPeriod: Check contextinfo json file comparisonPeriods > period 
    portfolioId: Check contextinfo json file portfolios > portfolioId (use -1 for all portfolios)
    portfolioId: Check contextinfo json file snapshotDates (these must be changed to dates with format YYYY-MM-DD)
    */
    clienteledistribution() : Observable<any[]> {
        return this._http.get<any[]>(this._url+'/'+this._clienteledistribution);
    }
    

}