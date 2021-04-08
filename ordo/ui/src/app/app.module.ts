import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { DataService } from './services/data.service';

import { MainComponent } from './components/main/main.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RiskProfileComponent } from './components/risk-profile/risk-profile.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { ClienteleComponent } from './components/clientele/clientele.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MenuComponent } from './components/menu/menu.component';
import { HeaderComponent } from './components/header/header.component';
import { RiskProfileTableComponent } from './components/risk-profile-table/risk-profile-table.component';
import { RiskGraphComponent } from './components/risk-graph/risk-graph.component';
import { ClienteleInfoComponent } from './components/clientele-info/clientele-info.component';
import { StatisticsTableComponent } from './components/statistics-table/statistics-table.component';
import {ModelvalidationTableComponent} from './components/modelvalidation/modelvalidation-table/modelvalidation-table.component'
import { StatisticsGraphComponent } from './components/statistics-graph/statistics-graph.component';
import { ChartsModule } from 'ng2-charts';
import {SearchTextPipe} from './pipes/search-text.pipe';
import {SortPipe} from './pipes/sort.pipe';
import { NgxPaginationModule } from 'ngx-pagination';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Interceptor} from './_helpers/interceptor';
import {DatePipe} from '@angular/common';
import { ClienteleCardComponent } from './components/clientele-info/clientele-card/clientele-card.component';
import { ClienteleSearchComponent } from './components/clientele/clientele-search/clientele-search.component';
import { SpecificScenarioComponent } from './components/specific-scenario/specific-scenario.component';
import { TabGraphComponent } from './components/tab-graph/tab-graph.component';
import { ScenarioTableComponent } from './components/scenario-table/scenario-table.component';
import { ParametersTableComponent } from './components/specific-scenario/parameters-table/parameters-table.component';
import {ScenariosComponent} from './components/scenarios/scenarios.component';
import {ModelvalidationComponent} from './components/modelvalidation/modelvalidation.component'
import { PortfolioComponent } from './components/specific-scenario/portfolio/portfolio.component';
import { ProspectComponent } from './components/specific-scenario/prospect/prospect.component';
import { SpecificPortfolioComponent } from './components/specific-scenario/portfolio/specific-portfolio/specific-portfolio.component';
import { ParametersTablePortfolioComponent } from
        './components/specific-scenario/portfolio/specific-portfolio/parameters-table-portfolio/parameters-table-portfolio.component';
import { SpecialCustomerComponent } from './components/specific-scenario/special-customer/special-customer.component';
import { SpecificProspectComponent } from './components/specific-scenario/prospect/specific-prospect/specific-prospect.component';
import { ParametersTableProspectComponent } from './components/specific-scenario/prospect/specific-prospect/parameters-table-prospect/parameters-table-prospect.component';
import { SpecificCustomerComponent } from './components/specific-scenario/special-customer/specific-customer/specific-customer.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { CreateComponent } from './components/specific-scenario/prospect/create/create.component';
import {CreateCustomerComponent} from './components/specific-scenario/special-customer/create-customer/create-customer.component';
import {CustomerSearchComponent} from './components/specific-scenario/special-customer/customer-search/customer-search.component';

import { AlertModule } from './_alert';
import { MultiAlertsComponent } from './multi-alerts';
import { NotifierModule } from "angular-notifier";

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    RiskProfileComponent,
    StatisticsComponent,
    ClienteleComponent,
    NotFoundComponent,
    MenuComponent,
    HeaderComponent,
    DashboardComponent,
    RiskProfileTableComponent,
    RiskGraphComponent,
    ClienteleInfoComponent,
    StatisticsTableComponent,
    ModelvalidationTableComponent,
    StatisticsGraphComponent,
    SearchTextPipe,
    SortPipe,
    ClienteleCardComponent,
    ClienteleSearchComponent,
    ScenariosComponent,
    ModelvalidationComponent,
    SpecificScenarioComponent,
    TabGraphComponent,
    ParametersTableComponent,
    ScenarioTableComponent,
    PortfolioComponent,
    ProspectComponent,
    SpecificPortfolioComponent,
    ParametersTablePortfolioComponent,
    SpecialCustomerComponent,
    SpecificProspectComponent,
    ParametersTableProspectComponent,
    SpecificCustomerComponent,
    CreateComponent,
      CreateCustomerComponent,
      CustomerSearchComponent,      
      MultiAlertsComponent
  ],

  imports: [
  BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ChartsModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
      NgSelectModule,
      AlertModule,
      NotifierModule,
      NgbModule
  ],
  providers: [
      DataService,
      {
          provide: HTTP_INTERCEPTORS,
          useClass: Interceptor,
          multi: true
      },
      DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
