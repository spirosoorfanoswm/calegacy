﻿import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs';

import { Alert, AlertType } from './alert.model';
import { AlertService } from './alert.service';

@Component({ 
    selector: 'alert', 
    templateUrl: 'alert.component.html', 
    styleUrls: ['./alert.component.css']})
export class AlertComponent implements OnInit, OnDestroy {
    @Input() id: string;

    alerts: Alert[] = [];
    subscription: Subscription;
    display = 'none';

    constructor(private alertService: AlertService) { }

    ngOnInit() {
        this.display = 'none';
        this.subscription = this.alertService.onAlert(this.id)
            .subscribe(alert => {
                if (!alert.message) {
                    this.alerts = [];
                    return;
                }

                this.alerts.push(alert);
            });
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

    removeAlert(alert: Alert) {
        this.alerts = this.alerts.filter(x => x !== alert);
    }

    onCloseHandled() {
        this.display = 'none';
      }

    cssClass(alert: Alert) {
        if (!alert) {
            return;
        }

       
        // return css class based on alert type
        switch (alert.type) {
          
            case AlertType.Success:
                return 'alert alert-success';
            case AlertType.Error:
                return 'alert alert-danger';
            case AlertType.Info:
                return 'alert alert-info';
            case AlertType.Warning:
                return 'alert alert-warning';
            default: return ''
            
        }
    }


}