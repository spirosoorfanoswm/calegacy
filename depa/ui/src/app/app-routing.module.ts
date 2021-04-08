import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RiskProfileComponent } from './components/risk-profile/risk-profile.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { ClienteleComponent } from './components/clientele/clientele.component';
import { ClienteleInfoComponent } from './components/clientele-info/clientele-info.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ScenariosComponent } from './components/scenarios/scenarios.component';
import {ExporterTableComponent} from './components/exporter/exporter-table.component'
import {SpecificScenarioComponent} from './components/specific-scenario/specific-scenario.component';

const routes: Routes = [
  {path: '', component: DashboardComponent},
  {path: 'credit-monitor', component: DashboardComponent, data: { state: 'dashboard' }},
  {path: 'scenarios', component: ScenariosComponent, data: { state: 'scenarios' }},
  {path: 'exporter', component: ExporterTableComponent, data: { state: 'exporter' }},
  {path: 'specific-scenario', component: SpecificScenarioComponent, data: { state: 'specific-scenarios' }},
  {path: 'risk-profile', component: RiskProfileComponent, data: { state: 'riskProfile' }},
  {path: 'risk-profile/:portfolioId', component: RiskProfileComponent, data: { state: 'riskProfileId' }},
  {path: 'statistics', component: StatisticsComponent, data: { state: 'statistics' }},
  {path: 'statistics/:portfolioId', component: StatisticsComponent, data: { state: 'statisticsId' }},
  {path: 'clientele', component: ClienteleComponent, data: { state: 'Clientele' }},
  {path: 'clientele/:portfolioId', component: ClienteleComponent, data: { state: 'clienteleId' }},
  {path: 'clientele/:customedId', component: ClienteleInfoComponent},
  {path: 'clientele/:portfolioId/:customerId', component: ClienteleInfoComponent},
  {path: 'clientele/:portfolioId/:customerId/:scenarioId', component: ClienteleInfoComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  exports: [ RouterModule ],
  imports: [
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
